package com.jm.portfolio.domain.model;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"value"})
public class Name {

    @NotEmpty
    @Column(name = "name", length = 50)
    private String value;

    private Name(final String name) {
        this.value = name;
    }

    public static Name of(final String name) {
        final String[] index = name.split(" ");
        if(index.length == 2) {
            return new Name(String.format("%s %s", index[0], index[1]));
        }
        return new Name(String.format("%s %s %s", index[0], index[1], index[2]));
    }

    public String getLastName() {
        final String[] index = value.split(" ");
        if(index.length == 2) {
            return index[0];
        }
        return index[2];
    }

    public String getMiddleName() {
        final String[] index = value.split(" ");
        if(index.length == 2) {
            return null;
        }
        return index[1];
    }

    public String getFirstName() {
        final String[] index = value.split(" ");
        return index[0];
    }
}
