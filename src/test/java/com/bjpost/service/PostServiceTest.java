package com.bjpost.service;

import com.bjpost.dto.request.PostCreateRequestDto;
import com.bjpost.dto.response.PostResponseDto;
import com.bjpost.entity.Post;
import com.bjpost.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@Transactional
@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    private void createPosts(){

        List<Post> posts = IntStream.range(1,31).mapToObj(i -> {

            return Post.builder()
                    .title("병중이의 글 제목" + i)
                    .content("병중이의 글 내용"+ i)
                    .build();
        }).collect(Collectors.toList());

        postRepository.saveAll(posts);
    }


    @Test
    @DisplayName("글 생성 테스트")
    void test1(){

        PostCreateRequestDto request = PostCreateRequestDto.builder()
                .title("병중이의 글 제목")
                .content("병중이의 글 내용")
                .build();

        postService.createPost(request);
        // 만든 Post메서드 만큼 저장되었는지 확인
        // 글 제목, 내용 비교하기

        assertEquals("병중이의 글 제목", postRepository.findAll().get(0).getTitle());
        assertEquals("병중이의 글 내용", postRepository.findAll().get(0).getContent());
    }

    @Test
    @DisplayName("글 조회 테스트")
    void test2(){

        createPosts();

        // 해당 서비스 테스트하기
        PostResponseDto response = postService.getPost(2L);

        // 글 제목 글 내용 확인하기
        assertEquals(2L, response.getId());
        assertEquals("병중이의 글 제목2", response.getTitle());
        assertEquals("병중이의 글 내용2", response.getContent());
    }
}
