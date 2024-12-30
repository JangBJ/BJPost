package com.bjpost.service;

import com.bjpost.dto.request.PostCreateRequestDto;
import com.bjpost.dto.request.PostUpdateRequestDto;
import com.bjpost.dto.response.PostResponseDto;
import com.bjpost.repository.PostRepository;
import com.bjpost.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시글 등록
    public void createPost(PostCreateRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
    }

    // 글 1개 조회
    public PostResponseDto getPost(Long postId){

        Post post = postRepository.findById(postId).orElseThrow();

        return PostResponseDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    // 글 전체 조회
    public List<Post> getAllPosts(){

        List<Post> posts = new ArrayList<>();

        for(Post post : postRepository.findAll()) {
            post = postRepository.findById(post.getId()).orElseThrow();
            posts.add(post);
        }
        return posts;
    }

    // 특정 글 수정
    public void updatePost(Long postId, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow();

        post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
    }

    // 특정 글 삭제
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
