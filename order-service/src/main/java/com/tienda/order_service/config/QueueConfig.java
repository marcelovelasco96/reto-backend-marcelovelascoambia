package com.tienda.order_service.config;

import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
public class QueueConfig {
    @Bean
    public QueueClient queueClient(
            @Value("${azure.storage.connection-string}") String connectionString,
            @Value("${app.queue.name}") String queueName) {

        QueueClient client = new QueueClientBuilder()
                .connectionString(connectionString)
                .queueName(queueName)
                .buildClient();
        try { client.create(); } catch (Exception ignored) {}
        return client;
    }
}


