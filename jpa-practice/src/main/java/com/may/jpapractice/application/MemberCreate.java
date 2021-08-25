package com.may.jpapractice.application;

import com.may.jpapractice.domain.entity.Member;
import com.may.jpapractice.domain.entity.Team;

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

        Team team = Team.builder().name("team1").build();

        Member memberA = Member.builder().id(1L).username("회원1").team(team).build();
        Member memberB = Member.builder().id(2L).username("회원2").team(team).build();


        em.persist(team);

        em.persist(memberA);
        em.persist(memberB);
        // INSERT SQL 데이터베이스에 보내지 않음.

        System.out.println("modifiedDate 만 수정 테스트");
        team.setName("test"); // 단순히 엔티티를 조회해서 데이터만 변경하면 된다(변경 감지 기능). 수정 시간(modifiedDate)만 변경됨

        System.out.println("순수한 객체까지 고려한 양방향 연관관계");
        System.out.println("고려하기 전, team.memberList size = " + team.getMemberList().size()); // 0
        team.getMemberList().add(memberA);
        team.getMemberList().add(memberB);
        System.out.println("고려한 후, team.memberList size = " + team.getMemberList().size()); // 2

        System.out.println("연관관계 삭제 테스트");
        // Caused by: javax.persistence.PersistenceException: org.hibernate.exception.ConstraintViolationException: could not execute statement
        // em.remove(team);

        memberA.setTeam(null);  // 회원1 연관관계 제거
        memberB.setTeam(null);  // 회원2 연관관계 제거
        em.remove(team);        // 팀 삭제

        // 커밋하는 순간 데이터베이스에 INSERT SQL 을 보낸다.
        transaction.commit(); // 커밋

    }

}
