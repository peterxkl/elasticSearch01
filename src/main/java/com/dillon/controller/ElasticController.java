package com.dillon.controller;

import com.dillon.model.BookBean;
import com.dillon.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author DillonXie
 * @version 1.0
 * @date 11/21/2019 5:51 PM
 */
@RestController
@RequestMapping("book")
public class ElasticController {
    @Autowired
    private BookService bookService;

    @RequestMapping("{id}")
    public BookBean getBookById(@PathVariable String id){
        Optional<BookBean> opt =bookService.findById(id);
        BookBean book=opt.get();
        System.out.println(book);
        return book;
    }

    @RequestMapping("save")
    public void Save(){
        BookBean book=new BookBean("2","ES入门教程2","谢坤龙2","2019-11-21");
        System.out.println(book);
        bookService.save(book);
    }
}
