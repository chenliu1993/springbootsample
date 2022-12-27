package com.example.demo.domain;

import lombok.Data;

@Data
public class PostEntity {
    private Long id;
    private String author;
    private String content;
    private String theme;
}
