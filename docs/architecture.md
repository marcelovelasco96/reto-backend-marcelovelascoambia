# Arquitectura (alto nivel)

```mermaid
flowchart LR
  C[Cliente / Postman] -->|POST /orders| OS[order-service (Spring Boot)]
  subgraph Azurite [Azurite (Storage Emulator)]
    Q[(Queue: orders)]
    B[(Blob: audit/orders/)]
  end
  OS -->|Enqueue JSON| Q
  W[Worker: payment-processor] -->|Dequeue| Q
  W -->|Escribe .json| B
