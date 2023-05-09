package com.jm.portfolio.domain.admin.domain;

import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.global.common.base.domain.BaseDomain;
import lombok.*;

import javax.persistence.*;

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

    @Column(name = "user_idx", updatable = false, nullable = false)
    private long userIdx;

    @Column(name = "title", updatable = false)
    private String title;

    @Lob
    @Column(name = "content", updatable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_idx", insertable = false, updatable = false)
    private Users user;

    @Builder
    public Notice(long userIdx, String title, String content) {
        this.userIdx = userIdx;
        this.title = title;
        this.content = content;
    }
}
