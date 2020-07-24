package com.company.handler;

import com.company.service.LoginService;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class LoginHandlerTest {

    @Test
    public void should_redirectTo404_when_sentGetRequestOnLoginPage() {
        // Arrange
        HttpExchange mockHttpExchange = Mockito.mock(HttpExchange.class);
        Mockito.when(mockHttpExchange.getRequestMethod()).thenReturn("GET");

        // Act


        // Assert
//        Mockito.verify(mockHttpExchange.getRequestMethod())
    }


    @Test
    public void should_redirectTo200_when_sentPostRequestOnLoginPage() {
        // Arrange
        HttpExchange mockHttpExchange = Mockito.mock(HttpExchange.class);
        Mockito.when(mockHttpExchange.getRequestMethod()).thenReturn("POST");
        OutputStream osMock = Mockito.mock(OutputStream.class);




        // Act


        // Assert
//        Mockito.verify(mockHttpExchange.getRequestMethod())
    }

}