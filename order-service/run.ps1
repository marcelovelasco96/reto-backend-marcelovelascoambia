Param(
  [int]$Port = 8080
)

Write-Host ">> Levantando order-service en puerto $Port ..."
# Forzamos el puerto por argumento (esto manda sobre application.yml y env vars)
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=--server.port=$Port"
