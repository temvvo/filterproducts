package com.ecommerce.filterproducts.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    Long id;
    Long productId;
    Boolean backSoon;
    Boolean special;
    Long quantity;
}
