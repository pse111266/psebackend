package com.example.firstproject3.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.firstproject3.entity.Article;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article,Long> {
    // Article: 엔티티 클래스 타입, Long: 대푯값 타입
    @Override
    ArrayList<Article> findAll();
}
