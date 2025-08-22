param(
  [int]$Port = 8080
)

Write-Host "-> Levantando order-service en puerto $Port ..."
# Si en application.yml usas la variable SERVER_PORT, la seteamos por si acaso
$env:SERVER_PORT = "$Port"

# Forzamos también por argumento (manda sobre yml)
.\mvnw.cmd spring-boot:run -D"spring-boot.run.arguments=--server.port=$Port"
