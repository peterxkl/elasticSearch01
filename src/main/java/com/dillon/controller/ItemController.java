package com.dillon.controller;

import com.dillon.model.Item;
import com.dillon.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    //
    @GetMapping("price")
    public List<Item> findByPriceBetween(Double price1, Double price2) {
        return itemService.findByPriceBetween(price1,price2);
    }
}
