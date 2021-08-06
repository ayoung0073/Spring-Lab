package com.may.springrestdocs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class BoardRequest {

    private final String title;

    private final String content;

}
