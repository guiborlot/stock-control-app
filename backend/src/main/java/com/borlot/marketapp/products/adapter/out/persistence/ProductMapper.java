package com.borlot.marketapp.products.adapter.out.persistence;

import com.borlot.marketapp.products.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    ProductEntity mapToJpaEntity(Product product){
        return new ProductEntity(
                product.getId(),
                product.getName().getValue(),
                product.getCategory().getValue(),
                product.getPrice().getValue(),
                product.getQuantity().getValue()
        );
    }

    Product toDomainEntity(ProductEntity product){
        return new Product(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    List<Product> toDomainEntityList(List<ProductEntity> products){
        List<Product> domainProductList = new ArrayList<>();
        for(ProductEntity product : products){
            domainProductList.add(toDomainEntity(product));
        }
        return domainProductList;
    }
}
