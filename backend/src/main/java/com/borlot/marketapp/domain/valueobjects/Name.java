package com.borlot.marketapp.domain.valueobjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.borlot.marketapp.domain.exceptions.InvalidNameException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Name {
    private static final Pattern VALID_NAME_REGEX = Pattern.compile("^[A-Z]{3,60}$", Pattern.CASE_INSENSITIVE);
    
    private String value;

    public Name(String value){
        this.value = value != null ? value : "";
        Matcher matcher = VALID_NAME_REGEX.matcher(value);
        if(!matcher.find()){
            throw new InvalidNameException();
        }
    }
}
