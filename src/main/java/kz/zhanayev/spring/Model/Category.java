package kz.zhanayev.spring.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor

public class Category {


    private Long id;


    private String name;

    private String description;


    private Set<Post> posts;

    // Конструкторы
    public Category() {}
}
