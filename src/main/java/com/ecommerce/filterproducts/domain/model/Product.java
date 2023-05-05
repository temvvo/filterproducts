package com.ecommerce.filterproducts.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Product {
    Long id;
    Long sequence;
}
