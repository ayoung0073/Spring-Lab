package com.may.blog.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthToken {
    // 카카오 토큰 요청후, 응답  Body
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;
//    access_token: "TJm85iUIE6IDP-z15gWGxWdnjo87bKqpAC3laQopyV4AAAF21X_tRw",
//    token_type: "bearer",
//    refresh_token: "cHEyhLDPkNQu6GWUe0_0SwmE6xRkOl_JbS882gopyV4AAAF21X_tRg",
//    expires_in: 21599,
//    scope: "account_email profile",
//    refresh_token_expires_in: 5183999
}
