package com.ecommerce.filterproducts.infrastructure.adapter.file.system.mapper;

import com.ecommerce.filterproducts.domain.model.Stock;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.model.StockFile;
import org.mapstruct.Mapper;

@Mapper
public interface StockFileMapper {
    StockFile convert(Stock model);
    Stock convert(StockFile entity);
}
