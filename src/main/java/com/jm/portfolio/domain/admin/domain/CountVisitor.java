package com.jm.portfolio.domain.admin.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "count_visitor")
@EqualsAndHashCode(of = {"visitDate"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CountVisitor {

    @Id
    @Column(name = "visit_date", updatable = false)
    private LocalDate visitDate;

    @Column(name = "total_visit")
    private Long totalVisit;

    @Builder
    public CountVisitor(LocalDate visitDate, Long totalVisit) {
        this.visitDate = visitDate;
        this.totalVisit = totalVisit;
    }
}
