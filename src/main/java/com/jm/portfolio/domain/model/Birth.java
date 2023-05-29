package com.jm.portfolio.domain.model;

import com.jm.portfolio.global.error.exception.InvalidValueException;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"value"})
public class Birth {

    @NotEmpty
    @Column(name = "birth")
    private LocalDate value;

    private Birth(final String stringBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.value = LocalDate.parse(stringBirth, formatter);
    }

    public static Birth of(final String year, final String month, final String day) {
        String stringBirth = String.format("%s-%s-%s", year, month, day);
        if(!validationDate(stringBirth)) {
            throw new InvalidValueException("Not date");
        }
        return new Birth(stringBirth);
    }

    public String getYear() {
        return value.getYear() + "";
    }

    public String getMonth() {
        return value.getMonthValue() + "";
    }

    public String getDay() {
        return value.getDayOfMonth() + "";
    }

    private static boolean validationDate(String checkDate){

        try{
            SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyy-MM-dd");

            dateFormat.setLenient(false);
            dateFormat.parse(checkDate);
            return  true;

        }catch (ParseException e){
            return  false;
        }

    }

}
