package com.may.jpapractice.application;

import com.may.jpapractice.domain.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MemberUpdate {

    public static void main(String[] args) {

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-application");
        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 획득
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Member memberA = Member.builder().id(1L).username("회원1").build();

        em.persist(memberA);

        Member findMember = em.find(Member.class, 1L);
        findMember.setEmail("test@test.com");

        em.persist(findMember);
        System.out.println(findMember.getUsername());
        transaction.commit();

    }

}

/*
@DynamicUpdate 적용 X
update member set email=?, team=?, username=? where id=?

@DynamicUpdate 적용
update member set email=? where id=?
 */