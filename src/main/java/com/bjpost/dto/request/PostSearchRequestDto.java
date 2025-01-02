package com.bjpost.dto.request;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSearchRequestDto {

    private String searchText;

    public PostSearchRequestDto(String searchText) {
        this.searchText = searchText;
    }
}
