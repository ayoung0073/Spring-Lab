package com.may.jpapractice.domain.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    @Column(updatable = false)
    private final LocalDateTime createdDate = LocalDateTime.now();

    private final LocalDateTime modifiedDate = LocalDateTime.now();

}
