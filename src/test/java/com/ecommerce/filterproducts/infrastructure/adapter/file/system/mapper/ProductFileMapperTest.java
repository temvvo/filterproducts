package com.ecommerce.filterproducts.infrastructure.adapter.file.system.mapper;

import com.ecommerce.filterproducts.domain.model.Product;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.model.ProductFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFileMapperTest {

    private ProductFileMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new ProductFileMapperImpl();
    }

    @Test
    public void convertToEntityTest() {
        Product product = Product.builder().id(1L).sequence(1L).build();

        ProductFile productFile = mapper.convert(product);

        assertNotNull(productFile);
        assertEquals(product.getId(), Long.parseLong(productFile.getId()));
        assertEquals(product.getSequence(), Long.parseLong(productFile.getSequence()));
    }

    @Test
    public void convertToDtoTest() {
        ProductFile productFile =  ProductFile.builder().id("1").sequence("1").build();

        Product product = mapper.convert(productFile);

        assertNotNull(product);
        assertEquals(product.getId(), Long.parseLong(productFile.getId()));
        assertEquals(product.getSequence(),Long.parseLong(productFile.getSequence()));
    }
}