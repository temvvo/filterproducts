package com.ecommerce.filterproducts.infrastructure.adapter.file.system.mapper;

import com.ecommerce.filterproducts.domain.model.Size;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.model.SizeFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SizeFileMapperTest {

    private SizeFileMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new SizeFileMapperImpl();
    }

    @Test
    public void convertToEntityTest() {
        Size size = Size.builder().id(1L).backSoon(true).productId(1L).quantity(1L).special(true).build();

        SizeFile sizeFile = mapper.convert(size);

        assertNotNull(sizeFile);
        assertEquals(size.getId(), Long.parseLong(sizeFile.getId()));
        assertEquals(size.getBackSoon(), Boolean.parseBoolean(sizeFile.getBackSoon()));
        assertEquals(size.getProductId(), Long.parseLong(sizeFile.getProductId()));
        assertEquals(size.getQuantity(), Long.parseLong(sizeFile.getQuantity()));
        assertEquals(size.getSpecial(), Boolean.parseBoolean(sizeFile.getSpecial()));
    }

    @Test
    public void convertToDtoTest() {
        SizeFile sizeFile =  SizeFile.builder().id("1").quantity("1").special("true").backSoon("true").productId("1").build();

        Size size = mapper.convert(sizeFile);

        assertNotNull(size);
        assertEquals(size.getId(), Long.parseLong(sizeFile.getId()));
        assertEquals(size.getQuantity(), Long.parseLong(sizeFile.getQuantity()));
        assertEquals(size.getSpecial(), Boolean.parseBoolean(sizeFile.getSpecial()));
        assertEquals(size.getBackSoon(), Boolean.parseBoolean(sizeFile.getBackSoon()));
        assertEquals(size.getProductId(), Long.parseLong(sizeFile.getProductId()));
    }
}