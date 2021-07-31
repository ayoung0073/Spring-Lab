package com.may.jpapractice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DataJpaTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void  builder_테스트() {
        Member member = Member.builder().name("애용").build();
        memberRepository.save(member);
        System.out.println(member);
        // private List<Board> boardList = new ArrayList<>(); :  Member(id=2, name=애용, boardList=[], commentList=[])
        // private List<Board> boardList; Member(id=2, name=애용, boardList=null, commentList=null) // null 처리 된다.
    }

}
