package org.papelariapachecotorres.clientes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        System.out.println("=== ClienteController.getAll() ===");
        System.out.println("page: " + page);
        System.out.println("size: " + size);
        System.out.println("search: '" + search + "'");
        System.out.println("search != null: " + (search != null));
        System.out.println("search.trim().isEmpty(): " + (search != null ? search.trim().isEmpty() : "N/A"));

        // Se page e size não forem fornecidos, retorna lista completa (para selects)
        if (page == null || size == null) {
            List<ClienteDTO> clientes = service
                    .getAll()
                    .stream()
                    .map(ClienteDTO::fromDomain)
                    .toList();
            return ResponseEntity.ok(clientes);
        }

        // Caso contrário, retorna paginado
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<ClienteDTO> clientesPage;
        if (search != null && !search.trim().isEmpty()) {
            System.out.println("✅ Usando searchPaginated com termo: '" + search + "'");
            clientesPage = service.searchPaginated(search, pageable)
                    .map(ClienteDTO::fromDomain);
        } else {
            System.out.println("⚠️ Usando getAllPaginated (sem filtro)");
            clientesPage = service.getAllPaginated(pageable)
                    .map(ClienteDTO::fromDomain);
        }

        System.out.println("Total elements retornados: " + clientesPage.getTotalElements());

        return ResponseEntity.ok(clientesPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable UUID id) {
        return service
                .getById(id)
                .map((cliente) -> ResponseEntity.ok(ClienteDTO.fromDomain(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = service.create(clienteDTO.toDomain());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ClienteDTO.fromDomain(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable UUID id, @RequestBody Cliente cliente) {
        Cliente updated = service.update(id, cliente);
        if (updated != null) {
            return ResponseEntity.ok(ClienteDTO.fromDomain(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
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