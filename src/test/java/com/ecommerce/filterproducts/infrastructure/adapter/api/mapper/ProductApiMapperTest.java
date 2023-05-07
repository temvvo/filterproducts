package com.ecommerce.filterproducts.infrastructure.adapter.api.mapper;

import com.ecommerce.filterproducts.domain.model.Product;
import com.ecommerce.filterproducts.infrastructure.adapter.api.model.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductApiMapperTest {
    private ProductApiMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new ProductApiMapperImpl();
    }

    @Test
    public void convertToEntityTest() {
        Product product = Product.builder().id(1L).sequence(1L).build();

        ProductDto productApi = mapper.convert(product);

        assertNotNull(productApi);
        assertEquals(product.getId(), productApi.getId());
        assertEquals(product.getSequence(), productApi.getSequence());
    }

    @Test
    public void convertToDtoTest() {
        ProductDto productDto =  new ProductDto( );
        productDto.setId(1L);
        productDto.sequence(1L);

        Product product = mapper.convert(productDto);

        assertNotNull(product);
        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getSequence(),productDto.getSequence());
    }
}