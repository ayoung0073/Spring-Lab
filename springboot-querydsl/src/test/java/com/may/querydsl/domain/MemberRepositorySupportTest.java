package com.may.querydsl.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberRepositorySupportTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

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

    @Test
    void join_테스트() {
        // given
        String name = "문애용";
        Member member = new Member(name);
        memberRepository.save(member);

        Post post1 = Post.builder()
                .writer(member)
                .name("테스트1")
                .build();
        Post post2 = Post.builder()
                .writer(member)
                .name("테스트2")
                .build();
        postRepository.saveAll(List.of(post1, post2));

        // when
        List<Post> postList = memberRepositorySupport.findPostListByName(name);

        // then
        assertThat(postList).hasSize(2);
        assertThat(postList.get(0).getName()).isEqualTo("테스트1");
        assertThat(postList.get(1).getName()).isEqualTo("테스트2");
    }

}