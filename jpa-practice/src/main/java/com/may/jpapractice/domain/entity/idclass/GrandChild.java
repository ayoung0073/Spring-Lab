package com.may.jpapractice.domain.entity.idclass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
//@IdClass(GrandChildId.class)
@Table(name = "grand_child")
public class GrandChild {

//    @Id
//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name = "parent_id"),
//            @JoinColumn(name = "child_id")
//    })
//    private Child child;

    @Id @Column(name = "grandchild_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
