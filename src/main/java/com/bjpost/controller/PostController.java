package com.bjpost.controller;

import com.bjpost.dto.request.PostCreateRequestDto;
import com.bjpost.dto.request.PostSearchRequestDto;
import com.bjpost.dto.request.PostUpdateRequestDto;
import com.bjpost.dto.response.PostResponseDto;
import com.bjpost.entity.Post;
import com.bjpost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    // 게시글 등록
    @PostMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public PostResponseDto createPost(@RequestBody @Validated PostCreateRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    // 게시글 1개 조회
    @GetMapping("/post/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }

    // 게시글 전체 조회
    @GetMapping("/post")
    public List<PostResponseDto> getPosts(){
        return postService.getAllPosts();
    }

    // 특정 게시글 수정 기능
    @PutMapping("/post/{postId}")
    public void updatePost(@PathVariable("postId") Long id, @RequestBody @Validated PostUpdateRequestDto requestDto){
        postService.updatePost(id, requestDto);
    }

    // 특정 게시글 삭제 기능
    @DeleteMapping("post/{postId}")
    public void deletePost(@PathVariable("postId") Long id){
        postService.deletePost(id);
    }

    // 게시글 검색 기능
    @PostMapping("post/search")
    public List<PostResponseDto> searchPost(@RequestParam @Validated PostSearchRequestDto requestDto){
        return postService.searchPost(requestDto);
    }

}
