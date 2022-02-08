package com.borlot.marketapp.domain.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
@Table(name = "product")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Size(max = 60)
    private String category;

    @NotNull
    @Digits(integer = 9, fraction = 2)
    private Double price;

    @NotNull
    private Integer quantity;

}
