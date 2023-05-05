package com.ecommerce.filterproducts.infrastructure.adapter.file.system.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SizeFile {
    String id;
    String productId;
    String backSoon;
    String special;
    String quantity;
}
