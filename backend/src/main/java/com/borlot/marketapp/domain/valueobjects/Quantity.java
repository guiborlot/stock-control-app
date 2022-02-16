package com.borlot.marketapp.domain.valueobjects;

import com.borlot.marketapp.domain.exceptions.InvalidQuantityException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Quantity {
    
    private Integer value;

    public Quantity(Integer value){
        if(value.toString().length() > 5){
            throw new InvalidQuantityException();
        }
        this.value = value;
    }
}
