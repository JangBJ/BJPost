package com.bjpost.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostUpdateRequestDto {

    String title;

    String content;
}
