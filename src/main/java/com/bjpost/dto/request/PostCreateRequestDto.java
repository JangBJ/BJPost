package com.bjpost.dto.request;

import com.bjpost.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequestDto {

    private String title;

    private String content;

    private LocalDateTime creatAt;
}
