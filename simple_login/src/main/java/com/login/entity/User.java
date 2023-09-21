package com.login.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String surName;
    @Column(unique = true)
    private String nickName;
    private short age;
    private String password;
}
