package com.bjpost.dto.response;

import com.bjpost.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    // 엔티티를 Dto로 바꿔주는 메서드
    public static PostResponseDto fromPost(Post post) {
       return PostResponseDto.builder()
               .id(post.getId())
               .title(post.getTitle())
               .createdAt(post.getCreateAt())
               .content(post.getContent())
               .build();
   }


}
