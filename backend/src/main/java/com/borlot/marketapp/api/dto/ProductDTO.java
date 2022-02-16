package com.borlot.marketapp.api.dto;

import com.borlot.marketapp.domain.entities.Product;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String category;
    @NotNull
    private Double price;
    @NotNull
    private Integer quantity;

    public ProductDTO(Product product){
        this(product.getId(), product.getName().getValue(), product.getCategory().getValue(), product.getPrice().getValue(), product.getQuantity().getValue());
    }

    public Product toProduct(){
        return new Product(id, name, category, price, quantity);
    }

}
