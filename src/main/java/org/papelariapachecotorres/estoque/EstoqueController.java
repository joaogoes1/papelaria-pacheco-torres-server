package org.papelariapachecotorres.estoque;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "produtoId", required = false) UUID produtoId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean lowStock,
            @RequestParam(defaultValue = "ultimaAtualizacao") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        if (produtoId != null) {
            return ResponseEntity.ok(service.getByProdutoId(produtoId)
                    .stream()
                    .map(EstoqueDTO::fromDomain)
                    .toList());
        }

        if (page == null || size == null) {
            List<EstoqueDTO> estoque = service
                    .getAll()
                    .stream()
                    .map(EstoqueDTO::fromDomain)
                    .toList();
            return ResponseEntity.ok(estoque);
        }

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<EstoqueDTO> estoquePage;
        if (Boolean.TRUE.equals(lowStock)) {
            estoquePage = service.findLowStock(pageable)
                    .map(EstoqueDTO::fromDomain);
        } else if (search != null && !search.trim().isEmpty()) {
            estoquePage = service.searchByProduct(search, pageable)
                    .map(EstoqueDTO::fromDomain);
        } else {
            estoquePage = service.getAllPaginated(pageable)
                    .map(EstoqueDTO::fromDomain);
        }

        return ResponseEntity.ok(estoquePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDTO> getById(@PathVariable UUID id) {
        return service.getById(id)
                .map((estoque) -> ResponseEntity.ok(EstoqueDTO.fromDomain(estoque)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EstoqueDTO> create(@RequestBody EstoqueDTO estoqueDto) {
        Estoque estoque = service.create(estoqueDto.toDomain());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(EstoqueDTO.fromDomain(estoque));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueDTO> update(@PathVariable UUID id, @RequestBody Estoque estoque) {
        Estoque updated = service.update(id, estoque);
        if (updated != null) {
            return ResponseEntity.ok(EstoqueDTO.fromDomain(updated));
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
} 