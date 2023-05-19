package com.jm.portfolio.domain.model;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"year", "month", "day"})
public class Birth {

    @NotEmpty
    @Column(name = "year", length = 50)
    private String year;

    @Column(name = "month", length = 50)
    private String month;

    @NotEmpty
    @Column(name = "day", length = 50)
    private String day;

    @Builder
    public Birth(final String year, final String month, final String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getBirth() {
        return String.format("%s - %s - %s", this.year, this.month, this.day);
    }
}
