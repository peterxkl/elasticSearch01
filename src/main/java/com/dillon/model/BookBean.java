package com.dillon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author DillonXie
 * @version 1.0
 * @date 11/21/2019 8:41 AM
 */
@Document(indexName = "book", type = "_doc")
@Data
@AllArgsConstructor
public class BookBean {
    @Id
    private String id;
    private String title;
    private String author;
    private String postDate;
}
