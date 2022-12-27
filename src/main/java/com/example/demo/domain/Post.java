package com.example.demo.domain;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
@Data
public class Post {
    private Long id;
    private Long userID;
    private String theme;
    private String path;
}
