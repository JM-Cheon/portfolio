package com.jm.portfolio.users.entity;

import com.jm.portfolio.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Users")
@DynamicInsert
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private long idx;

    @Column(updatable = false, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Temporal(TemporalType.TIMESTAMP)
    private Date withdrawAt;

    private String withdrawIp;

    @ColumnDefault("'N'")
    private String isWithdraw;

    @ColumnDefault("'N'")
    private String isDisabled;

    @ColumnDefault("'N'")
    private String isExpired;
}
