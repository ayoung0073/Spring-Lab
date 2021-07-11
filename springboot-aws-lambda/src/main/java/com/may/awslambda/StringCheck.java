package com.may.awslambda;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component // 하나의 함수
public class StringCheck implements Function<StringRequest, StringResponse> {
    @Override
    public StringResponse apply(StringRequest stringRequest) {
        String input = stringRequest.getInput();

        if (input.length() != stringRequest.getLength()) {
            return new StringResponse(false);
        }

        return new StringResponse(true);
    }
}
