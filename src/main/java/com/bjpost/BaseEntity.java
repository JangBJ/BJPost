package com.bjpost;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 상속할때만 쓰는거라고 표현해주는거
public abstract class BaseEntity {

    @Column(name= "create_at")
    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @PrePersist // 생성시 실행시켜주는 어노테이션
    void create(){
        LocalDateTime now = LocalDateTime.now();
        this.createAt = now;
        this.updateAt = now;
    }

}

