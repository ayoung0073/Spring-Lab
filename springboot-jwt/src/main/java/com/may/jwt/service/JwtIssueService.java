package com.may.jwt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtIssueService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${test.key}")
    private String SECRET_KEY;

    public String issueToken(Long userId, Long validTime) throws JsonProcessingException {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + validTime); // 30m
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);

        Map<String, Object> headerMap = new HashMap<>();

        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setHeader(headerMap)
                .setSubject(objectMapper.writeValueAsString(userId))
                .setExpiration(expireTime)
                .signWith(signingKey, signatureAlgorithm)
                .compact();
    }

    public String issueAccessToken(Long userId) throws JsonProcessingException {
        Long accessTokenValidTime = 30 * 60 * 1000L; // 30분
        return issueToken(userId, accessTokenValidTime);
    }

    public String issueRefreshToken(Long userId) throws JsonProcessingException { // TODO 추가 구현
        Long refreshTokenValidTime = 60 * 60 * 24 * 30 * 1000L; // 30일
        return issueToken(userId, refreshTokenValidTime);
    }

}
