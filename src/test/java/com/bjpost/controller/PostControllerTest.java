package com.bjpost.controller;

import com.bjpost.dto.request.PostCreateRequestDto;
import com.bjpost.dto.request.PostUpdateRequestDto;
import com.bjpost.entity.Post;
import com.bjpost.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;


    @BeforeEach
    void clean(){
        postRepository.deleteAll();

        Post post = Post.builder()
                .title("BJ Title")
                .content("BJ Content")
                .build();

        postRepository.save(post);
    }

    void createPostList(){
        List<Post> postList = IntStream.range(1,21).mapToObj(i -> {
            return Post.builder()
                    .title("BJ Title" + i)
                    .content("BJ Content" + i)
                    .build();
        }).collect(Collectors.toList());

        postRepository.saveAll(postList);
    }



    @Test
    @DisplayName("글 생성 컨트롤러 테스트")
    void createPost() throws Exception {

        PostCreateRequestDto request = PostCreateRequestDto.builder()
                .title("BJ Title")
                .content("BJ Content")
                .createAt(LocalDateTime.now())
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/post").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("BJ Title"))
                .andExpect(jsonPath("$.content").value("BJ Content"))
                .andDo(print());
    }

    @Test
    @DisplayName("글 1개 조회 컨트롤러 테스트")
    void getTest() throws Exception {

        Post post = postRepository.findAll().get(0);

        mockMvc.perform(get("/api/post/{postId}", post.getId()). contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("BJ Title"))
                .andExpect(jsonPath("$.content").value("BJ Content"))
                .andDo(print());
    }

    @Test
    @DisplayName("모든 글 조회")
    void PostListTest() throws Exception {

        createPostList();

        mockMvc.perform(get("/api/post").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(10L))
                .andExpect(jsonPath("$[0].title").value("BJ Title20"))
                .andDo(print());


    }

    @Test
    @DisplayName("글 변경 조회")
    void PostUpdateTest() throws Exception {

        Post post = postRepository.findAll().get(0);

        PostUpdateRequestDto request = PostUpdateRequestDto.builder()
                .title("수정 타이틀").content("수정 내용").build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(put("/api/post/{postId}", post.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        assertEquals("수정 타이틀", postRepository.findAll().get(0).getTitle());
        assertEquals("수정 내용", postRepository.findAll().get(0).getContent());
    }

}