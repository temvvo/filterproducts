package com.ecommerce.filterproducts.infrastructure.adapter.api.mapper;

import com.ecommerce.filterproducts.domain.model.Product;
import com.ecommerce.filterproducts.infrastructure.adapter.api.model.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Collectors;

@Mapper( imports = {Collectors.class})
public interface ProductApiMapper {

    Product convert(ProductDto dto);

    ProductDto convert(Product entity);
}
