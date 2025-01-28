package com.bjpost.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostUpdateRequestDto {

    @NotBlank
    @Size(min = 1, max = 15)
    String title;

    @NotBlank
    @Size(min = 1, max = 255)
    String content;
}
