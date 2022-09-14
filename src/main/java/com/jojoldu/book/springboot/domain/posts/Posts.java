package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity // jpa 어노테이션
public class Posts extends BaseTimeEntity {

    @Id // 테이블의 pk필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk생성규칙, auto-increment
    private Long id;

    @Column(length = 500, nullable = false) //default 255
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content= content;
    }
}
