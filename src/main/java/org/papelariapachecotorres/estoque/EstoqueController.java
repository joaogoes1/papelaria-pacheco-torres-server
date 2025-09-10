package org.papelariapachecotorres.estoque;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @GetMapping
    public List<EstoqueDTO> getAll(@RequestParam(value = "produtoId", required = false) Integer produtoId) {
        if (produtoId != null) {
            return service.getByProdutoId(produtoId)
                    .stream()
                    .map(EstoqueDTO::fromDomain)
                    .toList();
        }
        return service
                .getAll()
                .stream()
                .map(EstoqueDTO::fromDomain)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDTO> getById(@PathVariable int id) {
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
    public ResponseEntity<EstoqueDTO> update(@PathVariable int id, @RequestBody Estoque estoque) {
        Estoque updated = service.update(id, estoque);
        if (updated != null) {
            return ResponseEntity.ok(EstoqueDTO.fromDomain(updated));
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
} 