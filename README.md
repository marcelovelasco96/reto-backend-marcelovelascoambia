# Reto Técnico – Backend (Order + Worker con Azurite)

Solución end-to-end:
- `order-service` expone `POST /orders` y publica mensajes en la cola `orders`.
- `payment-processor` (worker, sin servidor web) consume la cola y guarda `.json` en Blob Storage `audit/orders/` usando Azurite.

## Requisitos
- Java 17 (`java -version`)
- Docker Desktop
- Azure Storage Explorer
- PowerShell (Windows)
- (Opcional) Postman
- Maven Wrapper incluido (`mvnw.cmd`)

## Arranque rápido

### 1) Azurite (emulador de Storage)
```bash
docker start reto-backend-azurite-1 2>/dev/null || ^
docker run -d --name reto-backend-azurite-1 ^
  -p 10000:10000 -p 10001:10001 -p 10002:10002 ^
  mcr.microsoft.com/azure-storage/azurite

## Evidencias
![Postman 202](docs/screens/postman-202.png)
![Blob audit/orders](docs/screens/storage-explorer-orders.png)

## Entregables

- **Código fuente**: `order-service`, `payment-processor`
- **Arquitectura**: [docs/architecture.md](docs/architecture.md)
- **Scrum (markdown)**: [docs/scrum.md](docs/scrum.md)
- **Scrum (Excel)**: [docs/Formto%20scrum.xlsx](docs/Formto%20scrum.xlsx)
- **Postman collection**: [postman/Order-API.postman_collection.json](postman/Order-API.postman_collection.json)
