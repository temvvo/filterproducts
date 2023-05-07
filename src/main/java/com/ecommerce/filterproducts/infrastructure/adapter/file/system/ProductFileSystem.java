package com.ecommerce.filterproducts.infrastructure.adapter.file.system;

import com.ecommerce.filterproducts.domain.model.Product;
import com.ecommerce.filterproducts.domain.port.file.system.IProductFileSystem;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.mapper.ProductFileMapper;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.model.ProductFile;
import org.apache.commons.csv.CSVRecord;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ecommerce.filterproducts.infrastructure.adapter.file.system.util.FileSystemUtils.cleanRecord;
import static com.ecommerce.filterproducts.infrastructure.adapter.file.system.util.FileSystemUtils.getCsvRecords;

public class ProductFileSystem implements IProductFileSystem {
    private final ProductFileMapper mapper = Mappers.getMapper(ProductFileMapper.class);

    @Value( "${file.csv.product}" )
    String fileName;
    @Override
    public Set<Product> getAll() throws IOException {
        Set<ProductFile> products = new HashSet<>();
        Iterable<CSVRecord> csvRecords = getCsvRecords(fileName);
        csvRecords.forEach(record ->
            products.add(
                    ProductFile.builder()
                            .id(cleanRecord(record.get(0)))
                            .sequence(cleanRecord(record.get(1)))
                            .build()
                    )
        );
        return products.stream().map(mapper::convert)
                .collect(Collectors.toSet());
    }


    public void setFileName(String s) {
        this.fileName = s;
    }

    public String getFileName() {
        return fileName;
    }
}
