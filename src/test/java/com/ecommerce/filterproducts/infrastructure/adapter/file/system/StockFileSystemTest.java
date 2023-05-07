package com.ecommerce.filterproducts.infrastructure.adapter.file.system;

import com.ecommerce.filterproducts.domain.model.Stock;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.util.FileSystemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


public class StockFileSystemTest {

    @Mock
    private ResourceLoader resourceLoader;

    private StockFileSystem fileSystem;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        fileSystem = new StockFileSystem();
        fileSystem.setFileName("stock.csv");
    }

    @Test
    public void getAllTest() throws IOException {
        String csvData = "1,3\n2,5\n";
        Resource resource = new InputStreamResource(new ByteArrayInputStream(csvData.getBytes(StandardCharsets.UTF_8)));
        when(resourceLoader.getResource("classpath:" + fileSystem.getFileName())).thenReturn(resource);


        Set<Stock> expectedStocks = new HashSet<>();
        expectedStocks.add(Stock.builder().sizeId(11L).quantity(0L).build());

        Set<Stock> actualStocks = fileSystem.getAll();

        assertTrue(actualStocks.contains(expectedStocks.iterator().next()));
    }


    @Test
    public void cleanRecordTest() {
        String record = "  Test Record  ";
        String expectedCleanRecord = "Test Record";

        String actualCleanRecord = FileSystemUtils.cleanRecord(record);

        assertEquals(expectedCleanRecord, actualCleanRecord);
    }

    @Test
    public void cleanRecordNullTest() {
        String record = null;

        String actualCleanRecord = FileSystemUtils.cleanRecord(record);

        assertEquals(null, actualCleanRecord);
    }

    @Test
    public void cleanRecordEmptyTest() {
        String record = "";

        String actualCleanRecord = FileSystemUtils.cleanRecord(record);

        assertEquals(null, actualCleanRecord);
    }
}