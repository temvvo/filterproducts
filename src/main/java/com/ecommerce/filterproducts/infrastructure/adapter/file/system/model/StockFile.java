package com.ecommerce.filterproducts.infrastructure.adapter.file.system.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StockFile {
    String sizeId;
    String quantity;
}
