package com.may.springrestdocs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class BoardResponse {

    private final Long id;

    private final String title;

    private final String content;

}
