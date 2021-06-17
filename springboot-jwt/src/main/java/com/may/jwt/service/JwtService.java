package com.may.jwt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.may.jwt.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;

@RequiredArgsConstructor
@Service
public class JwtService {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final JwtIssueService jwtIssueService;

    @Value("${test.key}")
    private String SECRET_KEY;

    public Long getPayload(String token) throws JsonProcessingException {
        Claims claims = getAllClaimsFromToken(token);
        return objectMapper.readValue(claims.getSubject(), Long.class);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public TokenDto createTokenResponse(Long userId) throws JsonProcessingException {
        String accessToken = jwtIssueService.issueAccessToken(userId);
        String refreshToken = jwtIssueService.issueRefreshToken(userId);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
