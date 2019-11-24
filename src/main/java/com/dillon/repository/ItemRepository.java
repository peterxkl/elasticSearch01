package com.dillon.repository;

import com.dillon.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 11/21/2019 9:39 PM
 */
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    List<Item> findByPriceBetween(Double price1, Double price2);
}
