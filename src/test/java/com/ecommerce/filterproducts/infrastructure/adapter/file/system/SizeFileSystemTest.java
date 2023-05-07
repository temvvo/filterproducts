package com.ecommerce.filterproducts.infrastructure.adapter.file.system;

import com.ecommerce.filterproducts.domain.model.Size;
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


public class SizeFileSystemTest {

    @Mock
    private ResourceLoader resourceLoader;

    private SizeFileSystem fileSystem;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        fileSystem = new SizeFileSystem();
        fileSystem.setFileName("size-1.csv");
    }

    @Test
    public void getAllTest() throws IOException {
        String csvData = "1,3\n2,5\n";
        Resource resource = new InputStreamResource(new ByteArrayInputStream(csvData.getBytes(StandardCharsets.UTF_8)));
        when(resourceLoader.getResource("classpath:" + fileSystem.getFileName())).thenReturn(resource);


        Set<Size> expectedSizes = new HashSet<>();
        expectedSizes.add(Size.builder().id(12L).productId(1L).backSoon(false).special(false).build());

        Set<Size> actualSizes = fileSystem.getAll();

        assertTrue(actualSizes.contains(expectedSizes.iterator().next()));
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