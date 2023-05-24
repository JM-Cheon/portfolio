package com.jm.portfolio.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"birth"})
public class Birth {

    @NotEmpty
    @Column(name = "birth")
    private LocalDate value;

    private Birth(final String year, final String month, final String day) {
        String stringBirth = String.format("%s-%s-%s", year, month, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.value = LocalDate.parse(stringBirth, formatter);
    }

    public static Birth of(final String year, final String month, final String day) {
        return new Birth(year, month, day);
    }

    public String getStringBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return value.format(formatter);
    }
}
