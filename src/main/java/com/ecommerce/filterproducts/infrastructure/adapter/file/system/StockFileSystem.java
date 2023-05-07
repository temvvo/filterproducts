package com.ecommerce.filterproducts.infrastructure.adapter.file.system;

import com.ecommerce.filterproducts.domain.model.Stock;
import com.ecommerce.filterproducts.domain.port.file.system.IStockFileSystem;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.mapper.StockFileMapper;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.model.StockFile;
import org.apache.commons.csv.CSVRecord;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ecommerce.filterproducts.infrastructure.adapter.file.system.util.FileSystemUtils.cleanRecord;
import static com.ecommerce.filterproducts.infrastructure.adapter.file.system.util.FileSystemUtils.getCsvRecords;

public class StockFileSystem implements IStockFileSystem {
    private final StockFileMapper mapper = Mappers.getMapper(StockFileMapper.class);

    @Value( "${file.csv.stock}" )
    String fileName;
    @Override
    public Set<Stock> getAll() throws IOException {
        Set<StockFile> stocks = new HashSet<>();
        Iterable<CSVRecord> csvRecords = getCsvRecords(fileName);
        csvRecords.forEach(record ->
            stocks.add(
                    StockFile.builder()
                            .sizeId(cleanRecord(record.get(0)))
                            .quantity(cleanRecord(record.get(1)))
                            .build()
            )
        );
        return stocks.stream().map(mapper::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String,String> getAllMapped() throws IOException {
        Map<String, String> stocks = new HashMap<>();
        Iterable<CSVRecord> csvRecords = getCsvRecords(fileName);
        csvRecords.forEach(record -> stocks.put(cleanRecord(record.get(0)), cleanRecord(record.get(1))));
        return stocks;
    }

    public void setFileName(String s) {
        this.fileName = s;
    }

    public String getFileName() {
        return fileName;
    }
}
