package com.bjpost.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 1, max = 15)
    private String title;

    @NotNull
    @Size(min = 1, max = 1000)
    private String content;

    private LocalDateTime createAt;

}
