package com.tienda.order_service.controller;

import com.tienda.order_service.domain.OrderRequest;
import com.tienda.order_service.service.OrderQueueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@RestController
public class OrderController {

    private final OrderQueueService service;

    public OrderController(OrderQueueService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public Mono<String> health() {
        return Mono.just("ok");
    }

    @PostMapping("/orders")
    public Mono<ResponseEntity<Void>> create(@Valid @RequestBody OrderRequest req) {
        return Mono.fromRunnable(() -> service.enqueue(req))
                .subscribeOn(Schedulers.boundedElastic())
                .then(Mono.just(ResponseEntity.accepted().build()));
    }

}

