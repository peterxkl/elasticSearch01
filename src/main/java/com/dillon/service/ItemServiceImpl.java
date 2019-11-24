package com.dillon.service;

import com.dillon.model.Item;
import com.dillon.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 11/21/2019 9:41 PM
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public Item insertItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> insertAllItem(List<Item> items) {
        return (List<Item>) itemRepository.saveAll(items);
    }

    @Override
    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> findByPriceBetween(Double price1, Double price2) {
        return itemRepository.findByPriceBetween(price1,price2);
    }
}
