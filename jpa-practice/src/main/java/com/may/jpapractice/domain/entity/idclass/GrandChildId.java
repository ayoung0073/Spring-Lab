package com.may.jpapractice.domain.entity.idclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

// 손자 ID
@AllArgsConstructor
@NoArgsConstructor
public class GrandChildId implements Serializable {

    //private Child child; // GrandChild.child 매핑 (필드명 같아야 한다!!)

    @Column
    private Long id; // GrandChild.id 매핑

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrandChildId that = (GrandChildId) o;
        return  Objects.equals(id, that.id);
//        return Objects.equals(child, that.child) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
