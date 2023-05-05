package com.ecommerce.filterproducts.infrastructure.adapter.api.controller;

import com.ecommerce.filterproducts.domain.model.Product;
import com.ecommerce.filterproducts.domain.port.service.ProductService;
import com.ecommerce.filterproducts.infrastructure.adapter.api.model.ProductDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductsApiControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductsApiController controller;


    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetProducts() throws Exception {
        Set<Product> products = new HashSet<>();
        Product product1 = Product.builder().id(10L).sequence(2L).build();
        Product product2 = Product.builder().id(12L).sequence(5L).build();
        products.add(product1);
        products.add(product2);
        Mockito.when(productService.getAll()).thenReturn(products);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/filter-products/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        Set<ProductDto> productDtos = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<Set<ProductDto>>() {
                });
        assertEquals(2, productDtos.size());
        assertTrue(productDtos.stream().anyMatch(p -> p.getId().equals(10L)));
        assertTrue(productDtos.stream().anyMatch(p -> p.getId().equals(12L)));
    }

    @Test
    public void testGetProductsId() throws Exception {
        List<Long> productIds = Arrays.asList(1L, 2L, 3L);
        Mockito.when(productService.getProductsId()).thenReturn(productIds);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/filter-products/api/v1/products/id")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();


        ObjectMapper objectMapper = new ObjectMapper();
        List<Long> responseProductIds = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<Long>>() {
                });
        assertEquals(3, responseProductIds.size());
        assertTrue(responseProductIds.contains(1L));
        assertTrue(responseProductIds.contains(2L));
        assertTrue(responseProductIds.contains(3L));
    }
}

