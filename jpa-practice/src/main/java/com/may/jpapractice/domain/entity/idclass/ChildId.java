package com.may.jpapractice.domain.entity.idclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

// 자식 Id
@AllArgsConstructor
@NoArgsConstructor
public class ChildId implements Serializable {

    //private Parent parent; // Child.parent 매핑

    @Column // IdentifierGeneratorHelper$2 예외
    private Long childId; // Child.childId 매핑

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildId childId1 = (ChildId) o;
//        return Objects.equals(parent, childId1.parent) && Objects.equals(childId, childId1.childId);
        return Objects.equals(childId, childId1.childId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(childId);
    }

}
