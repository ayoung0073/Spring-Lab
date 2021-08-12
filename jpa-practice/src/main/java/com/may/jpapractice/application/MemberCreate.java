package com.may.jpapractice.application;

import com.may.jpapractice.domain.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MemberCreate {

    public static void main(String[] args) {

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-application");
        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 획득
        EntityTransaction transaction = em.getTransaction();
        transaction.begin(); // 트랜잭션 시작

        Member memberA = Member.builder().id(1L).username("회원1").build();
        Member memberB = Member.builder().id(2L).username("회원2").build();

        em.persist(memberA);
        em.persist(memberB);
        // INSERT SQL 데이터베이스에 보내지 않음.

        // 커밋하는 순간 데이터베이스에 INSERT SQL 을 보낸다.
        transaction.commit(); // 커밋

//
//        Member findMember = em.find(Member.class, 1L);
//
//        System.out.println(findMember.getUsername());
    }

}
