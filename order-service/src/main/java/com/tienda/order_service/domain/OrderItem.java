package com.tienda.order_service.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class OrderItem {

    @NotBlank(message = "productId is required")
    private String productId;

    @Min(value = 1, message = "quantity must be >= 1")
    private int quantity;

    public OrderItem() { } // ctor por defecto requerido por Jackson

    public OrderItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

