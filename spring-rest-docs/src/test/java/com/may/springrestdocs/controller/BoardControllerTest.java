package com.may.springrestdocs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.may.springrestdocs.dto.BoardResponse;
import com.may.springrestdocs.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.may.springrestdocs.config.ApiDocumentUtils.getDocumentRequest;
import static com.may.springrestdocs.config.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@WebMvcTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BoardService boardService;

    @Test
    void get_테스트() throws Exception {
        // given
        BoardResponse response = BoardResponse.builder()
                .id(1L)
                .title("test title")
                .content("test content")
                .build();

        given(boardService.get(any())).willReturn(response);

        // when
        ResultActions result = mockMvc.perform(
                get("/api/boards/{id}", 1L)
        );

        // then
        result
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(response)))
                .andDo(print())
                .andDo(document("boards/get",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("id").description("requested board id.")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("board id."),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("board title."),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("board content")
                        )
                ));
    }

}
