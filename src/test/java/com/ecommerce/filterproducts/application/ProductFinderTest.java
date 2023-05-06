package com.ecommerce.filterproducts.application;

import com.ecommerce.filterproducts.domain.port.file.system.IProductFileSystem;
import com.ecommerce.filterproducts.domain.port.file.system.ISizeFileSystem;
import com.ecommerce.filterproducts.domain.port.file.system.IStockFileSystem;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
class ProductFinderTest {

    @InjectMocks
    private ProductFinder productFinder;
    @Mock
    private IProductFileSystem productFileSystem;

    @Mock
    private ISizeFileSystem sizeFileSystem;

    @Mock
    private IStockFileSystem stockFileSystem;


    @Test
    public void shouldGetAll() throws IOException {
        productFinder.getAll();
        verify(productFileSystem).getAll();
    }

    @Test
    public void shouldGetAllVisibleIds() throws IOException {
        productFinder.getVisibleProducts();
        verify(productFileSystem).getAll();
        verify(sizeFileSystem).getAllAndJoin(any());
        verify(stockFileSystem).getAllMapped();
    }
}