package com.ecommerce.filterproducts.infrastructure.adapter.api.exceptions.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class FPErrorHandlerTest {

    @Mock
    private HttpServletRequest request;

    private FPErrorHandler errorHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        errorHandler = new FPErrorHandler();
    }

    @Test
    public void defaultErrorHandlerTest() {
        Exception exception = new Exception("Test exception");
        ResponseEntity response = errorHandler.defaultErrorHandler(request, exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal Error: Test exception", response.getBody());
    }

    @Test
    public void IOErrorHandlerTest() {
        IOException exception = new IOException("Test IO exception");
        ResponseEntity response = errorHandler.IOErrorHandler(request, exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("File IO Error: Test IO exception", response.getBody());
    }


}