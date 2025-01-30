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

        // 실제로 생성된 게시글의 ID를 가져옵니다.
        Post createdPost = postRepository.findAll().get(1);
        Long postId = createdPost.getId();

        PostResponseDto response = postService.getPost(postId);

        // 글 제목, 내용 확인
        assertEquals(postId, response.getId());
        assertEquals("병중이의 글 제목" + 2, response.getTitle());
        assertEquals("병중이의 글 내용" + 2, response.getContent());
    }

    @Test
    @DisplayName("글 수정 테스트")
    void test3() {
        createPosts();

        PostUpdateRequestDto request = PostUpdateRequestDto.builder()
                .title("변경된 제목")
                .content("변경된 내용")
                .build();

        Post createPost= postRepository.findAll().get(1);
        Long postId = createPost.getId();


        postService.updatePost(postId, request);
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("글 없어용ㅠ"));


        assertEquals("변경된 제목", post.getTitle());
        assertEquals("변경된 내용", post.getContent());
    }

    @Test
    @DisplayName("글 삭제 테스트입니다")
    void test4(){
        createPosts();

        Post createPost = postRepository.findAll().get(1);
        Long postId = createPost.getId();

        postService.deletePost(postId);

        assertEquals( 29L, postRepository.findAll().size());
    }
}
