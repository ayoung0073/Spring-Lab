package com.may.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto getInfo(String id) {
        return memberRepository.findById(id).orElseGet(
                () -> {throw new IllegalArgumentException("해당 유저가 존재하지 않습니다.");}
        );
    }

    public void saveMember(MemberDto member) {
        memberRepository.save(member);
    }

}
