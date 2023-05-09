package com.jm.portfolio.domain.admin.domain;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.global.common.base.domain.BaseDomain;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notice")
@EqualsAndHashCode(of = {"idx"}, callSuper = false)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", updatable = false)
    private Long idx;

    @Column(name = "nickname", updatable = false, nullable = false)
    private String nickname;

    @Column(name = "title", updatable = false)
    private String title;

    @Lob
    @Column(name = "content", updatable = false)
    private String content;

    @Column(name = "img", updatable = false)
    private String img;

    @ManyToOne
    @JoinColumn(name = "user_idx", insertable = false, updatable = false)
    private Users user;

    @Builder
    public Notice(String createdIp, String lastUpdatedIp, String nickname, String title, String content, String img) {
        super(createdIp, lastUpdatedIp);
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.img = img;
    }
}
