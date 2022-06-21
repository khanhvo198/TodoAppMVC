package com.mystic.TodoAppMVC.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
public class Task implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    private String taskName;
    private boolean checked;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
