package com.dillon.service;

import com.dillon.model.Item;

import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 11/21/2019 9:41 PM
 */
public interface ItemService {
   Item insertItem(Item item);
   List<Item> insertAllItem(List<Item> items);
   Item updateItem(Item item);
   List<Item> findByPriceBetween(Double price1, Double price2);

}
