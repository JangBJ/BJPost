package com.bjpost.controller;

import com.bjpost.dto.request.PostCreateRequestDto;
import com.bjpost.dto.request.PostUpdateRequestDto;
import com.bjpost.dto.response.PostResponseDto;
import com.bjpost.entity.Post;
import com.bjpost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    // 게시글 등록
    @PostMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public Post createPost(@RequestBody PostCreateRequestDto requestDto) {
        Post post = postService.createPost(requestDto);
        // Post id를 얻기위해 PostResponse에서 Post로 반환값 바꿈
        return post;
    }

    // 게시글 1개 조회
    @GetMapping("/post/{postId}")
    public Post getPost(@PathVariable("postId") Long postId){
        return postService.getPost(postId);
    }

    // 게시글 전체 조회
    @GetMapping("/post")
    public List<Post> getPosts(){
        List<Post> posts = postService.getAllPosts();
        return posts;
    }

    // 특정 게시글 수정 기능
    @PutMapping("/post/{postId}")
    public void updatePost(@PathVariable("postId") Long id, @RequestBody PostUpdateRequestDto requestDto){
        postService.updatePost(id, requestDto);
    }

    // 특정 게시글 삭제 기능
    @DeleteMapping("post/{postId}")
    public void deletePost(@PathVariable("postId") Long id){
        postService.deletePost(id);
    }
}
