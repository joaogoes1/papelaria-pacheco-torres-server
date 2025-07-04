package org.papelariapachecotorres.clientes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable int id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) {
        return service.create(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable int id, @RequestBody Cliente cliente) {
        Cliente updated = service.update(id, cliente);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/importar")
    public ResponseEntity<String> importClientes(@RequestBody Map<String, String> payload) {
        String path = payload.get("path");
        if (path == null || path.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("O caminho do arquivo ('path') é obrigatório.");
        }

        try {
            int count = service.importClientesCsv(path);
            return ResponseEntity.ok(count + " clientes importados com sucesso de: " + path);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erro ao importar clientes: " + e.getMessage());
        }
    }
} 