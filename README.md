# tareas

API REST CRUD de gestión de tareas desarrollada con **Java 21** y **Spring Boot** (Sesión 4: "Java con Spring Boot").

## ¿Qué es?

Es una API REST sencilla para crear, listar, obtener, actualizar y eliminar tareas. Cada tarea tiene un identificador (`id`), un `titulo` y un estado `completada`.

## Requisitos

- JDK 21
- Maven (se incluye el wrapper `./mvnw`)

## Cómo ejecutar

Desde la raíz del proyecto:

```powershell
.\mvnw.cmd spring-boot:run
```

La aplicación se iniciará en `http://localhost:8080`.

Para construir el JAR:

```powershell
.\mvnw.cmd clean package
```

## Endpoints

| Método | Ruta | Cuerpo (request) | Código de respuesta |
|--------|------|------------------|---------------------|
| GET | /tareas | - | 200 |
| GET | /tareas/{id} | - | 200 / 404 |
| POST | /tareas | `{"titulo":"...","completada":true/false}` | 201 |
| PUT | /tareas/{id} | `{"titulo":"...","completada":true/false}` | 200 / 404 |
| DELETE | /tareas/{id} | - | 204 / 404 |
| GET | /tareas/pendientes | - | 200 |
| PUT | /tareas/{id}/completar | - | 200 / 404 |
| GET | /tareas/buscar/{texto} | - | 200 |

### Notas importantes

- El `id` lo asigna siempre el servidor (`siguienteId++`); en POST el id enviado por el cliente se ignora.
- En PUT solo se actualizan `titulo` y `completada`; el `id` no se modifica.
- Los datos viven **únicamente en memoria** (un `ArrayList` en el controlador). Al reiniciar la aplicación se pierden y se cargan las 3 tareas de ejemplo iniciales.
- No hay base de datos, servicios, repositorios ni validaciones con anotaciones.

## Archivos de prueba

- `Tareas API.postman_collection.json`: colección de Postman (formato v2.1) con las 10 peticiones (secuencia de verificación más los 3 ejercicios autónomos), usando la URL base `http://localhost:8080` y el encabezado `Content-Type: application/json` en las que llevan cuerpo.
