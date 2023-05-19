package com.jm.portfolio.domain.notice.domain;

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

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "img")
    private String img;

    @ManyToOne
    @JoinColumn(name = "user_idx", insertable = false, updatable = false)
    private Users user;

    @Builder
    public Notice(String createdIp, String lastUpdatedIp, String title, String content, String img) {
        super(createdIp, lastUpdatedIp);
        this.title = title;
        this.content = content;
        this.img = img;
    }
}
