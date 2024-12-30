package com.bjpost.service;

import com.bjpost.dto.request.PostCreateRequestDto;
import com.bjpost.dto.request.PostUpdateRequestDto;
import com.bjpost.dto.response.PostResponseDto;
import com.bjpost.entity.Post;
import com.bjpost.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
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

    @Test
    @DisplayName("게시판 글 등록")
    void test() {
        // given(데이터 준비)
        PostCreateRequestDto requestDto = PostCreateRequestDto.builder()
                .title("병중이의 글")
                .content("병중이의 글내용")
                .build();

        // when(함수 실행)
        postService.createPost(requestDto);
        // then(결과)

        Post post = postRepository.findAll().get(0);

        log.info("Requset: { }",requestDto);
        log.info("Post: { }", post);

        assertEquals(post.getTitle(), requestDto.getTitle());
        assertEquals(post.getContent(), requestDto.getContent());
    }

    private void createPosts(){
        for(int i=0; i<2; i++){
            Post post = Post.builder()
                    .title("병중이의 글" + i)
                    .content("병중이의 글내용" + i)
                    .build();
            postRepository.save(post);
        }
    }

    private void createPost(){
        Post post = Post.builder()
                .title("병중이의 글")
                .content("병중이의 글내용")
                .build();
        postRepository.save(post);
    }

    @Test
    @DisplayName("글 전체 조회")
    void test2(){
        // given(데이터 준비)
        createPosts();

        // when(함수 사용)
        List<Post> postList = postService.getAllPosts();

        // 왜 안나오지?
        log.info(postList.toString());

        // then(결과)
        assertEquals("병중이의 글0", postList.get(0).getTitle());
        assertEquals("병중이의 글1", postList.get(1).getTitle());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test3(){

        // given(데이터 준비)
        createPosts();

        // when(함수 실행)
        PostResponseDto responseDto = postService.getPost(1L);

        // then(결과)
        assertEquals("병중이의 글0", responseDto.getTitle());
        assertEquals("병중이의 글내용0", responseDto.getContent());
    }

    @Test
    @DisplayName("특정 글 수정")
    void test4(){

        // given(데이터 준비)
        createPost();
        Post post = postRepository.findById(1L).orElseThrow();

        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
                .title("수정된 병중이의 글")
                .content("수정된 병중이의 글내용")
                .build();

        // when(함수 사용)
        postService.updatePost(post.getId(), requestDto);

        // then(결과)
        assertEquals(post.getTitle(), requestDto.getTitle());
        assertEquals(post.getContent(), requestDto.getContent());
    }

    @Test
    @DisplayName("특정 글 삭제")
    void test5(){

        // given(데이터 준비)
        createPosts();
        List<Post> posts = postRepository.findAll();
        Long postId = posts.get(0).getId();
        log.info(" ",postId);

        // when(함수 실행)
        PostResponseDto responseDto = postService.getPost(postId);

        // then(결과)
        Optional<Post> post = postRepository.findById(1L);
        assertFalse(post.isPresent(), "post delete");
    }
}