package com.dillon.repository;

import com.dillon.model.BookBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author DillonXie
 * @version 1.0
 * @date 11/21/2019 8:44 AM
 */
public interface BookRepository extends ElasticsearchRepository<BookBean, String> {

    Page<BookBean> findByAuthor(String author, Pageable pageable);

    Page<BookBean> findByTitle(String title, Pageable pageable);
}
