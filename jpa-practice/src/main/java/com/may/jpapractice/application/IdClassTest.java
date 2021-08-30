package com.may.jpapractice.application;

import com.may.jpapractice.domain.entity.idclass.Child;
import com.may.jpapractice.domain.entity.idclass.GrandChild;
import com.may.jpapractice.domain.entity.idclass.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class IdClassTest {

    public static void main(String[] args) {

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-application");
        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 획득
        EntityTransaction transaction = em.getTransaction();
        transaction.begin(); // 트랜잭션 시작

        Parent parent = Parent.builder().name("부모").build();
        Child child = Child.builder().name("자식").build();
        GrandChild grandChild = GrandChild.builder().name("자손").build();

        em.persist(parent);
        em.persist(child);
        em.persist(grandChild);

        transaction.commit();
    }

}
