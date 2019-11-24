package com.dillon.controller;

import com.dillon.model.Item;
import com.dillon.repository.ItemRepository;
import com.dillon.service.ItemServiceImpl;
import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.dillon.constant.QueryType.*;

/**
 * @author DillonXie
 * @version 1.0
 * @date 11/21/2019 9:06 PM
 */
@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("index")
    public void createIndex() {
        elasticsearchTemplate.createIndex(Item.class);
        elasticsearchTemplate.putMapping(Item.class);
    }

    @DeleteMapping("index")
    public void deleteIndex() {
        elasticsearchTemplate.deleteIndex(Item.class);
    }

    @PostMapping("save")
    public Item insertItem(@RequestBody Item item) {
        return itemService.insertItem(item);
    }

    @PostMapping("saveAll")
    public List<Item> insertAll(@RequestBody List<Item> items) {
        return itemService.insertAllItem(items);
    }

    @PutMapping("update")
    public Item update(@RequestBody Item item) {
        return itemService.updateItem(item);
    }

    //自定义方法
    @GetMapping("price")
    public List<Item> findByPriceBetween(Double price1, Double price2) {
        return itemService.findByPriceBetween(price1,price2);
    }

    //自定义查询

    /**
     * matchQuery
     * 注意：如果查询的是非分词字段，那么就是精确匹配；如果查询的是分词字段，那么就是按照分词器去匹配
     * @param item
     * @return
     */
    @GetMapping("items1")
    public List<Item> findByItemOnMatchQuery(Item item) {
        List<Item> result = executeQuery(item, MATCH_QUERY);
        return result;
    }

    /**
     * termQuery:功能更强大，除了匹配字符串以外，还可以匹配int、double型等
     * @param item
     * @return
     */
    @GetMapping("items2")
    public List<Item> findByItemOnTermQuery(Item item) {
        List<Item> result = executeQuery(item, TERM_QUERY);
        return result;
    }

    /**
     * 布尔查询:多条件查询
     * @param item
     * @return
     */
    @GetMapping("items3")
    public List<Item> findByItemOnBoolQuery(Item item) {
        List<Item> result = executeQuery(item, BOOL_QUERY);
        return result;
    }

    /**
     * 模糊查询
     * 注意：这里的模糊匹配与mysql、mongodb里面的不一样，这里是利用分词效果去匹配的。所以有可能查询title为”小“查不到、而”小米“可以查到
     * @param item
     * @return
     */
    @GetMapping("items4")
    public List<Item> findByItemOnFuzzyQuery(Item item) {
        List<Item> result = executeQuery(item, FUZZY_QUERY);
        return result;
    }

    private List<Item> executeQuery(Item item, String queryType) {
        List<Item> data = new ArrayList<>();
        if (StringUtils.isEmpty(queryType)) {
            return data;
        }
        //1.定义“查询条件构建器”
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //2.利用QueryBuilders来生成具体查询条件
        AbstractQueryBuilder query = null;
        if (MATCH_QUERY.equals(queryType)) {
            query = QueryBuilders.matchQuery("brand", item.getBrand());
        }
        if (TERM_QUERY.equals(queryType)) {
            query = QueryBuilders.termQuery("price", item.getPrice());
        }
        if (BOOL_QUERY.equals(queryType)) {
            query = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("title", item.getTitle()))  //多条件查询
                    .must(QueryBuilders.matchQuery("brand", item.getBrand()));   //must类似于sql里面的and，而should类似or
        }
        if (FUZZY_QUERY.equals(queryType)) {
            query = QueryBuilders.fuzzyQuery("title", item.getTitle());
        }

        //3.将条件添加到构建器中
        queryBuilder.withQuery(query);

        //4.调用search方法执行查询，返回结果默认为分页形式
        Page<Item> results = itemRepository.search(queryBuilder.build());

        //5.处理查询结果
        for (Item result : results) {
            data.add(result);
        }

        return data;
    }
}
