package com.tienda.payment_processor;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobContainerClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PaymentProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentProcessorApplication.class, args);
	}

	// Smoke test: crea un blob mínimo al arrancar
	@Bean
	CommandLineRunner smoke(BlobContainerClient blobContainer) {
		return args -> {
			String name = "orders/_smoke_" + System.currentTimeMillis() + ".txt";
			blobContainer.getBlobClient(name)
					.upload(BinaryData.fromString("ok"), true);
			System.out.println("SMOKE: subido " + name);
		};
	}
}
