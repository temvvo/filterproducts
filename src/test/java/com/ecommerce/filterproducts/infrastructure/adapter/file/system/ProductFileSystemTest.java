package com.ecommerce.filterproducts.infrastructure.adapter.file.system;

import com.ecommerce.filterproducts.domain.model.Product;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.util.FileSystemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
//@SpringBootTest
public class ProductFileSystemTest {

    @Mock
    private ResourceLoader resourceLoader;

    private ProductFileSystem fileSystem;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        fileSystem = new ProductFileSystem();
        fileSystem.setFileName("product.csv");
    }

    @Test
    public void getAllTest() throws IOException {
        String csvData = "1,Product 1,Description 1,100.0\n2,Product 2,Description 2,200.0\n";
        Resource resource = new InputStreamResource(new ByteArrayInputStream(csvData.getBytes(StandardCharsets.UTF_8)));
        when(resourceLoader.getResource("classpath:" + fileSystem.getFileName())).thenReturn(resource);


        Set<Product> expectedProducts = new HashSet<>();
        expectedProducts.add(Product.builder().id(1L).sequence(10L).build());
        expectedProducts.add(Product.builder().id(2L).sequence(7L).build());
        expectedProducts.add(Product.builder().id(3L).sequence(15L).build());
        expectedProducts.add(Product.builder().id(4L).sequence(13L).build());
        expectedProducts.add(Product.builder().id(5L).sequence(6L).build());

        Set<Product> actualProducts = fileSystem.getAll();

        assertEquals(expectedProducts, actualProducts);
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