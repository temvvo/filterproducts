package com.ecommerce.filterproducts.infrastructure.adapter.file.system.mapper;

import com.ecommerce.filterproducts.domain.model.Stock;
import com.ecommerce.filterproducts.infrastructure.adapter.file.system.model.StockFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StockFileMapperTest {

    private StockFileMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new StockFileMapperImpl();
    }

    @Test
    public void convertToEntityTest() {
        Stock stock = Stock.builder().sizeId(1L).quantity(1L).build();

        StockFile stockFile = mapper.convert(stock);

        assertNotNull(stockFile);
        assertEquals(stock.getSizeId(), Long.parseLong(stockFile.getSizeId()));
        assertEquals(stock.getQuantity(), Long.parseLong(stockFile.getQuantity()));
    }

    @Test
    public void convertToDtoTest() {
        StockFile stockFile =  StockFile.builder().sizeId("1").quantity("1").build();

        Stock stock = mapper.convert(stockFile);

        assertNotNull(stock);
        assertEquals(stock.getSizeId(), Long.parseLong(stockFile.getSizeId()));
        assertEquals(stock.getQuantity(),Long.parseLong(stockFile.getQuantity()));
    }
}