package com.tienda.order_service.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class OrderRequest {

    @NotBlank(message = "orderId is required")
    private String orderId;

    @NotBlank(message = "customerId is required")
    private String customerId;

    @NotEmpty(message = "items must not be empty")
    @Size(min = 1, message = "items must contain at least 1 element")
    @Valid
    private List<OrderItem> items;

    @NotNull(message = "totalAmount is required")
    @DecimalMin(value = "0.01", message = "totalAmount must be >= 0.01")
    private BigDecimal totalAmount;

    public OrderRequest() { } // ctor por defecto requerido por Jackson

    public OrderRequest(String orderId, String customerId,
                        List<OrderItem> items, BigDecimal totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
}

