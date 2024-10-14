package kz.zhanayev.spring.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor


public class Post {


    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;


    private User author;


    private List<Category> categories;

    public Post() {

    }
}
