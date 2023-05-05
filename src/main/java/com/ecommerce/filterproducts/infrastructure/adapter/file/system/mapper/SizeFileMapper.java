package com.ecommerce.filterproducts.infrastructure.adapter.file.system.mapper;

import com.ecommerce.filterproducts.domain.model.Size;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.model.SizeFile;
import org.mapstruct.Mapper;

@Mapper
public interface SizeFileMapper {
    SizeFile convert(Size model);
    Size convert(SizeFile entity);
}
