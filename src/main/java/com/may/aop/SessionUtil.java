package com.may.aop;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    private static final String LOGIN_MEMBER_ID = "LOGIN_MEMBER_ID";

    // 인스턴스화 방지
    private SessionUtil() {}

    /**
     * 로그인한 고객의 아이디를 세션에서 꺼냄
     * @param session 사용자의 세션
     * @return 로그인한 사용자의 id 또는 null
     */
    public static String getLoginMemberId(HttpSession session) {
        return (String) session.getAttribute(LOGIN_MEMBER_ID);
    }

    /**
     * 로그인 한 고객의 id를 세션에 저장
     * @param session 사용자의 session
     * @param id 로그인한 사용자의 id
     */
    public static void setLoginMemberId(HttpSession session, String id) {
        session.setAttribute(LOGIN_MEMBER_ID, id);
    }
}