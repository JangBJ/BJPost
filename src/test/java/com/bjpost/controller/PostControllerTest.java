package com.bjpost.controller;

import com.bjpost.dto.request.PostCreateRequestDto;
import com.bjpost.repository.PostRepository;
import com.bjpost.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostControllerTest {

    private PostRepository postRepository;
    private PostService postService;

    @Test
    @DisplayName("게시판 글 등록")
    void test1(){
        // given(데이터 준비)
        PostCreateRequestDto requestDto =  PostCreateRequestDto.builder()
                .title("병중이의 글")
                .content("병중이의 글내용")
                .build();

        // when(함수 사용)
        postService.createPost(requestDto);

        // then(결과)

    }
}