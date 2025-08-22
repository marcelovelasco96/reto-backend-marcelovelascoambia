package com.tienda.order_service.controller;

import com.tienda.order_service.domain.OrderItem;
import com.tienda.order_service.domain.OrderRequest;
import com.tienda.order_service.service.OrderQueueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@WebFluxTest(controllers = OrderController.class)
class OrderControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private OrderQueueService queueService;

    @Test
    void create_ok_returns202() {
        OrderRequest body = new OrderRequest(
                "ORD1", "C1",
                List.of(new OrderItem("P1", 1)),
                new BigDecimal("10.00")
        );

        webTestClient.post().uri("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isAccepted();

        verify(queueService).enqueue(any(OrderRequest.class));
    }

    @Test
    void create_invalid_returns400() {
        OrderRequest bad = new OrderRequest(
                "", "C1",
                List.of(),                 // vacío → inválido
                new BigDecimal("0.00")     // < 0.01 → inválido
        );

        webTestClient.post().uri("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(bad)
                .exchange()
                .expectStatus().isBadRequest();

        verifyNoInteractions(queueService);
    }
}
