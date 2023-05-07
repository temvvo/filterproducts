package com.ecommerce.filterproducts.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Size {
    Long id;
    Long productId;
    Boolean backSoon;
    Boolean special;
    Long quantity;
}
