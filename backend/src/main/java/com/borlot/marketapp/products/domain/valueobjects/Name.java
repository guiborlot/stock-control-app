package com.borlot.marketapp.products.domain.valueobjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.borlot.marketapp.products.domain.exceptions.InvalidNameException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Name {
    private static final Pattern VALID_NAME_REGEX = Pattern.compile("^[A-Za-z ]{2,60}$");
    
    private String value;

    public Name(String value){
        this.value = value != null ? value : "";
        Matcher matcher = VALID_NAME_REGEX.matcher(this.value);
        if(!matcher.find()){
            throw new InvalidNameException();
        }
    }
}
