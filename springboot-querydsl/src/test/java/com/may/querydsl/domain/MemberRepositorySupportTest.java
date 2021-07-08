package com.may.querydsl.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositorySupportTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRepositorySupport memberRepositorySupport;

    @Test
    void findByName_테스트() {
        // given
        String name = "문애용";
        memberRepository.save(new Member(name));
        memberRepository.save(new Member(name));

        // when
        List<Member> memberList = memberRepositorySupport.findByName(name);

        // then
        assertThat(memberList).hasSize(2);
    }

}