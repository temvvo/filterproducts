package com.ecommerce.filterproducts.infrastructure.adapter.file.system.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;

public class FileSystemUtils {
    public static String cleanRecord(String record) {
        return StringUtils.isEmpty(record) ? null : record.trim();
    }

    public static Iterable<CSVRecord> getCsvRecords(String fileName) throws IOException {
        String path = FileSystemUtils.class.getClassLoader().getResource(fileName) == null ? fileName: FileSystemUtils.class.getClassLoader().getResource(fileName).getPath();
        FileReader csvFile = new FileReader(path);
        Iterable<CSVRecord> csvRecords = CSVFormat.EXCEL.parse(csvFile);
        return csvRecords;
    }

}
