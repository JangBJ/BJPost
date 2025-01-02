package com.bjpost.service;

import com.bjpost.dto.request.PostCreateRequestDto;
import com.bjpost.dto.request.PostUpdateRequestDto;
import com.bjpost.repository.PostRepository;
import com.bjpost.entity.Post;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시글 등록(성공)
    public Post createPost(PostCreateRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        // id를 얻기위한 반환
        return post;
    }

    // 글 1개 조회
    public Post getPost(Long postId){

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException("Post not found")
        );

        return post;
    }

    // 글 전체 조회
    public List<Post> getAllPosts(){

        return postRepository.findAllOrderByIdDesc();
    }

    // 특정 글 수정
    public void updatePost(Long postId, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow();
        post.update(requestDto);
    }

    // 특정 글 삭제
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    // 검색
    public List<Post> searchPost(String searchText){
        return postRepository.findByTitleLike(searchText);
    }
}
