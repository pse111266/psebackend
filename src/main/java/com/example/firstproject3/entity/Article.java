package com.example.firstproject3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //-> 엔티티 선언

@AllArgsConstructor
@ToString
@NoArgsConstructor //기본 생성자 추가

@Getter

public class Article {
    @Id //-> 엔티티 대푯값 지정
    @GeneratedValue //->자동 생성 기능 (숫자가 자동으로 매겨짐)

    private Long id;
    @Column //-> title 필드 선언, DB 테이블의 title 열
    private String title;
    @Column // -> content 필드 선언, DB 테이블의 content열
    private String content;

    public Long getId() {
        return id;
    }
}
