package com.codewithmithun.to_do_list.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "todo_details")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean completed;
}
