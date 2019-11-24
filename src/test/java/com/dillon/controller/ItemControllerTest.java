package com.dillon.controller;

import com.dillon.model.Item;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import javax.annotation.Resource;

/**
 * @author DillonXie
 * @version 1.0
 * @date 11/21/2019 9:27 PM
 */
public class ItemControllerTest {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void should_create_index() {
       elasticsearchTemplate.createIndex(Item.class);
    }
}
