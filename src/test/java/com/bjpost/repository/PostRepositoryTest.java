/*
package com.bjpost.repository;

import com.bjpost.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("")
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    void findByTitleLike() {
        String title = "병중";
        List<Post> post = postRepository.findByTitleLike(title);
        post.stream().forEach(System.out::println);
    }
}*/
