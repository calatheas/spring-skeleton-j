package com.calatheas.skeletonj.common.jpa;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class CreatedAndLastModifiedDate {
    @CreatedDate
    @NonNull
    private LocalDateTime createdDate;

    @LastModifiedDate
    @NonNull
    private LocalDateTime lastModifiedDate;
}
