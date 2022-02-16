package com.borlot.marketapp.domain.valueobjects;

import com.borlot.marketapp.domain.exceptions.InvalidCategoryException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Category {
    private static final Pattern VALID_CATEGORY_REGEX = Pattern.compile("^[A-Z]{1,60}$", Pattern.CASE_INSENSITIVE);
    
    private String value;

    public Category(String value){
        this.value = value != null ? value : "";
        Matcher matcher = VALID_CATEGORY_REGEX.matcher(value);
        if(!matcher.find()){
            throw new InvalidCategoryException();
        }
    }
}