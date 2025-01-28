package com.bjpost.service;

import com.bjpost.dto.request.PostCreateRequestDto;
import com.bjpost.dto.request.PostSearchRequestDto;
import com.bjpost.dto.request.PostUpdateRequestDto;
import com.bjpost.dto.response.PostResponseDto;
import com.bjpost.repository.PostRepository;
import com.bjpost.entity.Post;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시글 등록
    public PostResponseDto createPost(PostCreateRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);

        return PostResponseDto.fromPost(post);
    }

    // 글 1개 조회
    public PostResponseDto getPost(Long postId){

        Post post = postRepository.findById(postId).orElseThrow( () -> new IllegalArgumentException("Post not found"));

        return PostResponseDto.fromPost(post);
    }

    // 글 전체 조회
    public List<PostResponseDto> getAllPosts(){
        List<Post> posts = postRepository.findTop10ByOrderByIdDesc();

        return posts.stream()
                .map(PostResponseDto::fromPost)
                .collect(Collectors.toList());
    }

    // 특정 글 수정
    public void updatePost(Long postId, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow();
        post.update(requestDto);
    }

    // 특정 글 삭제
    public void deletePost(Long postId) {
        postRepository.findById(postId).orElseThrow( () -> new IllegalStateException("Post not found"));
        postRepository.deleteById(postId);
    }

    // 검색
    public List<PostResponseDto> searchPost(PostSearchRequestDto requestDto) {

        List<Post> posts = postRepository.findTop100ByTitleLike(requestDto.getSearchText());

        List<PostResponseDto> responseList = posts.stream().map(PostResponseDto::fromPost).collect(Collectors.toList());

        return responseList;
    }
}
