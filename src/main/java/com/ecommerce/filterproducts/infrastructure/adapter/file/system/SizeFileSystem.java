package com.ecommerce.filterproducts.infrastructure.adapter.file.system;

import com.ecommerce.filterproducts.domain.model.Size;
import com.ecommerce.filterproducts.domain.port.file.system.ISizeFileSystem;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.mapper.SizeFileMapper;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.model.SizeFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ecommerce.filterproducts.infrastructure.adapter.file.system.util.FileSystemUtils.cleanRecord;

public class SizeFileSystem implements ISizeFileSystem {
    private final SizeFileMapper mapper = Mappers.getMapper(SizeFileMapper.class);

    @Value( "${file.csv.size}" )
    String fileName;
    @Override
    public Set<Size> getAll() throws IOException {
        Set<SizeFile> sizes = new HashSet<>();
        FileReader csvFile = new FileReader(getClass().getClassLoader().getResource(fileName).getPath());
        Iterable<CSVRecord> csvRecords = CSVFormat.EXCEL.parse(csvFile);
        csvRecords.forEach(record -> {
            sizes.add(
                    SizeFile.builder()
                            .id(cleanRecord(record.get(0)))
                            .productId(cleanRecord(record.get(1)))
                            .backSoon(cleanRecord(record.get(2)))
                            .special(cleanRecord(record.get(3)))
                            .build()
            );
        });
        return sizes.stream().map(mapper::convert)
                .collect(Collectors.toSet());
    }
}
