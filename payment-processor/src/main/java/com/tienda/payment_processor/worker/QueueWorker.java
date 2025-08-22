package com.tienda.payment_processor.worker;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.models.QueueMessageItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class QueueWorker {
    private static final Logger log = LoggerFactory.getLogger(QueueWorker.class);

    private final QueueClient queueClient;
    private final BlobContainerClient blobContainer;

    public QueueWorker(QueueClient queueClient, BlobContainerClient blobContainer) {
        this.queueClient = queueClient;
        this.blobContainer = blobContainer;
    }

    @Scheduled(fixedDelay = 1000) // corre cada segundo
    public void poll() {
        log.debug("poll tick");
        for (QueueMessageItem m : queueClient.receiveMessages(5)) {
            try {
                String payload = m.getBody().toString(); // Azurite ya te lo da como texto
                String json;
                try {
                    // si viniera en base64, decodifica; si no, usa tal cual
                    String maybe = new String(Base64.getDecoder().decode(payload), StandardCharsets.UTF_8);
                    json = maybe.trim().startsWith("{") ? maybe : payload;
                } catch (IllegalArgumentException ex) {
                    json = payload;
                }

                String name = "orders/" + System.currentTimeMillis() + ".json";
                blobContainer.getBlobClient(name).upload(BinaryData.fromString(json), true);
                log.info("Guardado blob {}", name);

                queueClient.deleteMessage(m.getMessageId(), m.getPopReceipt());
                log.info("Mensaje borrado {}", m.getMessageId());
            } catch (Exception e) {
                log.error("Fallo procesando", e);
            }
        }
    }
}
