package io.ratel.stocktrader.common.entity;

/**
 * packageName  : io.ratel.stocktrader.common.entity
 * fileName    : AutitableFields
 * author      : dorris
 * date        : 2025. 2. 20.
 * description :
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 2. 20.          dorris             최초생성
 */
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@ToString
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass()
public class BaseEntiry {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate()
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @CreatedBy()
    @Column(nullable = false, length=255)
    private String createdBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate()
    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @LastModifiedBy()
    @Column(nullable = false, length=255)
    private String modifiedBy;
}
