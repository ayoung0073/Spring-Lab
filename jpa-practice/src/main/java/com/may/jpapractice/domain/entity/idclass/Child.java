package com.may.jpapractice.domain.entity.idclass;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
//@IdClass(ChildId.class)
@Table(name = "child")
public class Child {

    // 식별 관계는 기본 키와 외래 키를 같이 매핑해야 한다. 식별자 매핑인 @Id와 연관관계 매핑인 @ManyToOne을 같이 사용하면 된다.
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "parent_id")
//    private Parent parent;

    @Id @Column(name = "child_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childId;

    private String name;

}
