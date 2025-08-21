# Reto Backend — Order Service + Payment Processor

Dos servicios:
- **order-service**: API que recibe órdenes y las encola en Azure Storage Queue (`orders`).
- **payment-processor**: worker que lee la cola y sube una auditoría `.json` a Azure Blob (`audit/orders/`).

## Prerrequisitos
- **Java 17**
- **Docker Desktop** (para Azurite)
- **Postman** (o `curl`)
- **Microsoft Azure Storage Explorer** (para ver blobs/colas)

---

## 1) Arrancar Azurite (emulador de Storage)
> Si ya está corriendo, puede omitirse.

```bash
docker run -d --name azurite ^
  -p 10000:10000 -p 10001:10001 -p 10002:10002 ^
  mcr.microsoft.com/azure-storage/azurite
