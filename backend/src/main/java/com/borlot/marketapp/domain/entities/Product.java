package com.borlot.marketapp.domain.entities;

import com.borlot.marketapp.domain.valueobjects.Category;
import com.borlot.marketapp.domain.valueobjects.Name;
import com.borlot.marketapp.domain.valueobjects.Price;
import com.borlot.marketapp.domain.valueobjects.Quantity;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Setter
    private Long id;
    private Name name;
    private Category category;
    private Price price;
    private Quantity quantity;

    public Product(Long id, String name, String category, Double price, Integer quantity){
        this(id, new Name(name), new Category(category), new Price(price), new Quantity(quantity));
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
