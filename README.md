# ProyectoBd — LigaProApp (JavaFX + Microservicios)

LigaProApp es un sistema modular diseñado para la gestión de una liga de fútbol. Este monorepo incluye:
- **desktop-app**: Aplicación de escritorio JavaFX (Java 17) que consume los microservicios por HTTP.
- **services/teams-service**: Microservicio Spring Boot con API REST para Equipos (PostgreSQL/Hikari).
- **services/players-service**: Microservicio Spring Boot con API REST para Jugadores.
- **services/matches-service**: Microservicio Spring Boot para Partidos y Goles.

## Requisitos
- Java 17 (JDK 17)
- Gradle 8.x o wrapper (puedes ejecutar con `gradle` si lo tienes instalado)
- Docker y Docker Compose (opcional, para levantar la infraestructura completa)
- IDE recomendado: IntelliJ IDEA o VS Code con extensiones Java

Los servicios corren en los puertos publicados via Docker:
- teams-service: `http://localhost:8181`
- players-service: `http://localhost:8182`
- matches-service: `http://localhost:8183`

## Cómo ejecutar
1. **Levantar microservicios (terminales separadas)**:
   ```bash
   cd services/teams-service
   gradle bootRun
   # en otra terminal
   cd services/players-service
   gradle bootRun
   # en otra terminal
   cd services/matches-service
   gradle bootRun
   ```

2. **Levantar la app de escritorio**:
   ```bash
   cd carpeta padre
   gradle run
   ```

3. **Opción con Docker Compose**:
   ```bash
   docker-compose up --build
   ```

> La app cargará la lista de equipos desde `teams-service` y podrás crear equipos, jugadores, partidos y goles.  
> Los jugadores se registran contra `players-service` seleccionando un equipo y puedes administrar correos adicionales.  
> Los presidentes y sus correos se gestionan desde `teams-service`, mientras que los goles se registran en `matches-service`.
