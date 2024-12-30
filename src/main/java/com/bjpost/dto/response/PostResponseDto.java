package com.bjpost.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostResponseDto {

    private String title;

    private String content;

}
