package com.bjpost.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class User {

    @Id
    Long id;

    String name;

    String email;

    String password;

    String nickName;

    @Builder
    public User(String name, String email, String password, String nickName) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }
}
