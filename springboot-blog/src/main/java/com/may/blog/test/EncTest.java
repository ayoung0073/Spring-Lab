package com.may.blog.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

    @Test
    public void hash_encrypt() {
        String password = new BCryptPasswordEncoder().encode("1234"); // 고정 길이의 문자열로 바꿔줌
        System.out.println("password : " + password);
    }
}
