package com.bjpost.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSearchRequestDto {

    @NotBlank
    @Size(min = 1)
    private String searchText;

    public PostSearchRequestDto(String searchText) {
        this.searchText = searchText;
    }
}
