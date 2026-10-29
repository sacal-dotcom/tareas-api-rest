package com.ejemplo.tareas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    private List<Tarea> tareas = new ArrayList<>();
    private int siguienteId = 1;

    public TareaController() {
        tareas.add(new Tarea(siguienteId++, "Aprender Java", false));
        tareas.add(new Tarea(siguienteId++, "Practicar Spring Boot", false));
        tareas.add(new Tarea(siguienteId++, "Subir el proyecto a GitHub", true));
    }

    // Método auxiliar: busca una tarea por id (devuelve null si no existe)
    private Tarea buscarPorId(int id) {
        for (Tarea t : tareas) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    // GET /tareas → devuelve la lista completa de tareas (200)
    @GetMapping
    public List<Tarea> listar() {
        return tareas;
    }

    // GET /tareas/{id} → devuelve la tarea con el id indicado (200) o 404 si no existe
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtener(@PathVariable int id) {
        Tarea tarea = buscarPorId(id);
        if (tarea == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarea);
    }

    // POST /tareas → crea una nueva tarea. El servidor asigna el id (ignora el enviado por el cliente) y responde 201
    @PostMapping
    public ResponseEntity<Tarea> crear(@RequestBody Tarea nueva) {
        int maxId = 0;
        for (Tarea t : tareas) {
            if (t.getId() > maxId) {
                maxId = t.getId();
            }
        }
        int nuevoId = maxId + 1;
        nueva.setId(nuevoId);
        tareas.add(nueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // PUT /tareas/{id} → actualiza título y completada de la tarea existente (no el id), responde 200 o 404
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(@PathVariable int id, @RequestBody Tarea datos) {
        Tarea tarea = buscarPorId(id);
        if (tarea == null) {
            return ResponseEntity.notFound().build();
        }
        tarea.setTitulo(datos.getTitulo());
        tarea.setCompletada(datos.isCompletada());
        return ResponseEntity.ok(tarea);
    }

    // DELETE /tareas/{id} → elimina la tarea, responde 204 si se eliminó o 404 si no existía
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        Tarea tarea = buscarPorId(id);
        if (tarea == null) {
            return ResponseEntity.notFound().build();
        }
        tareas.remove(tarea);
        return ResponseEntity.noContent().build();
    }

    // GET /tareas/pendientes → devuelve solo las tareas no completadas (completada == false)
    @GetMapping("/pendientes")
    public List<Tarea> pendientes() {
        List<Tarea> resultado = new ArrayList<>();
        for (Tarea t : tareas) {
            if (!t.isCompletada()) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    // PUT /tareas/{id}/completar → marca la tarea como completada sin recibir cuerpo; 404 si no existe, 200 con la tarea
    @PutMapping("/{id}/completar")
    public ResponseEntity<Tarea> completar(@PathVariable int id) {
        Tarea tarea = buscarPorId(id);
        if (tarea == null) {
            return ResponseEntity.notFound().build();
        }
        tarea.setCompletada(true);
        return ResponseEntity.ok(tarea);
    }

    // GET /tareas/buscar/{texto} → tareas cuyo título contiene el texto, sin distinguir mayúsculas
    @GetMapping("/buscar/{texto}")
    public List<Tarea> buscar(@PathVariable String texto) {
        List<Tarea> resultado = new ArrayList<>();
        String textoMinus = texto.toLowerCase();
        for (Tarea t : tareas) {
            if (t.getTitulo().toLowerCase().contains(textoMinus)) {
                resultado.add(t);
            }
        }
        return resultado;
    }
}
