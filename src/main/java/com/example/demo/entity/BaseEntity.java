// BaseEntity.java

package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTime; // 필드명 통일 (getCreatedTime())

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedTime; // 필드명 통일 (getUpdatedTime())

}