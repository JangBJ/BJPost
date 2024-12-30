package com.bjpost.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
public class PostCreateRequestDto {

    String title;

    String content;
}
