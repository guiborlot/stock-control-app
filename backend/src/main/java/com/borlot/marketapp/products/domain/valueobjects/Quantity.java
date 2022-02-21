package com.borlot.marketapp.products.domain.valueobjects;

import com.borlot.marketapp.products.domain.exceptions.InvalidQuantityException;
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
