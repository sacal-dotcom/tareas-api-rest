# Tareas API REST CRUD

## ¿Qué es el proyecto?

API REST CRUD para gestión de tareas desarrollada con Spring Boot 3.5.4 y Java 21. Permite crear, listar, obtener, actualizar y eliminar tareas, además de operaciones adicionales: listar pendientes, marcar como completada y buscar por texto.

## Requisitos

- JDK 21

## Cómo ejecutarlo

```bash
.\mvnw.cmd spring-boot:run
```

Por defecto la aplicación se ejecuta en el puerto `8080`.

## Endpoints

| Método | Ruta | Cuerpo de ejemplo | Códigos de respuesta | Descripción |
|---|---|---|---|---|
| GET | /tareas | - | 200 OK | Listar todas las tareas |
| POST | /tareas | `{"titulo": "Estudiar REST", "completada": false}` | 201 Created | Crear tarea (el servidor asigna el id) |
| GET | /tareas/{id} | - | 200 OK, 404 Not Found | Obtener tarea por id |
| PUT | /tareas/{id} | `{"titulo": "Estudiar REST", "completada": true}` | 200 OK, 404 Not Found | Actualizar título y completada (no el id) |
| DELETE | /tareas/{id} | - | 204 No Content, 404 Not Found | Eliminar tarea |
| GET | /tareas/pendientes | - | 200 OK | Listar tareas no completadas |
| PUT | /tareas/{id}/completar | - | 200 OK, 404 Not Found | Marcar tarea como completada (sin cuerpo) |
| GET | /tareas/buscar/{texto} | - | 200 OK | Buscar tareas cuyo título contiene el texto (sin distinguir mayúsculas/minúsculas) |

## Cómo importar la colección de Postman

1. Abrir Postman
2. Importar `Tareas API.postman_collection.json`
3. La colección usa la variable `{{baseUrl}}` con valor `http://localhost:8080` (puede modificarse según el entorno)

## Nota

Los datos viven solo en memoria. Al reiniciar la aplicación se restauran las tareas iniciales.
