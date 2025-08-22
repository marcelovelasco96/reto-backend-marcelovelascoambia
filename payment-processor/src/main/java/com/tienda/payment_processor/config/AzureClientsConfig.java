package com.tienda.payment_processor.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureClientsConfig {

    @Bean
    public QueueClient queueClient(
            @Value("${azure.storage.connection-string}") String connectionString,
            @Value("${app.queue.name}") String queueName) {

        // OJO: no llamamos create() aquí
        return new QueueClientBuilder()
                .connectionString(connectionString)
                .queueName(queueName)
                .buildClient();
    }

    @Bean
    public BlobContainerClient blobContainerClient(
            @Value("${azure.storage.connection-string}") String connectionString,
            @Value("${app.blob.container}") String container) {

        // OJO: no llamamos create() aquí
        return new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient()
                .getBlobContainerClient(container);
    }
}
