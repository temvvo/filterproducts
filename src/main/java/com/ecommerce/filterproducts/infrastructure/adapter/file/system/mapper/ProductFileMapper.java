package com.ecommerce.filterproducts.infrastructure.adapter.file.system.mapper;

import com.ecommerce.filterproducts.domain.model.Product;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.model.ProductFile;
import org.mapstruct.Mapper;

@Mapper
public interface ProductFileMapper {
    ProductFile convert(Product model);
    Product convert(ProductFile entity);
}
