package com.tienda.order_service.service;

import com.azure.storage.queue.QueueClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienda.order_service.domain.OrderRequest;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class OrderQueueService {
    private final QueueClient queueClient;
    private final ObjectMapper mapper = new ObjectMapper();

    // Constructor explícito: Spring inyecta el QueueClient aquí
    public OrderQueueService(QueueClient queueClient) {
        this.queueClient = queueClient;
    }

    public void enqueue(OrderRequest order) {
        try {
            String json = mapper.writeValueAsString(order);
            String b64  = Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));
            queueClient.sendMessage(b64);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
