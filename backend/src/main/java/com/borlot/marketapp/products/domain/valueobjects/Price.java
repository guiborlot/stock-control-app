package com.borlot.marketapp.products.domain.valueobjects;

import com.borlot.marketapp.products.domain.exceptions.InvalidPriceException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Price {
    
    private Double value;

    public Price(Double value){
        String[] splitter = value.toString().split("\\.");
        if(splitter[0].length() > 15 || splitter[1].length() > 2){
            throw new InvalidPriceException();
        }

        this.value = value;
    }
}
