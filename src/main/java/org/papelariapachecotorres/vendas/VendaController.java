package org.papelariapachecotorres.vendas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) UUID clienteId,
            @RequestParam(required = false) String nomeCliente,
            @RequestParam(required = false) java.math.BigDecimal valorMin,
            @RequestParam(required = false) java.math.BigDecimal valorMax,
            @RequestParam(defaultValue = "data") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        System.out.println("=== VendaController.getAll() ===");
        System.out.println("page: " + page);
        System.out.println("size: " + size);
        System.out.println("nomeCliente: '" + nomeCliente + "'");
        System.out.println("valorMin: " + valorMin);
        System.out.println("valorMax: " + valorMax);
        System.out.println("clienteId: " + clienteId);

        if (page == null || size == null) {
            if (clienteId != null) {
                return ResponseEntity.ok(service.getByClienteId(clienteId).stream()
                        .map(VendaDTO::fromDomain)
                        .toList());
            }
            List<VendaDTO> vendas = service.getAll()
                    .stream()
                    .map(VendaDTO::fromDomain)
                    .toList();
            return ResponseEntity.ok(vendas);
        }

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<VendaDTO> vendasPage;
        // Se qualquer filtro for fornecido, usa a busca avançada
        if ((nomeCliente != null && !nomeCliente.trim().isEmpty()) || valorMin != null || valorMax != null) {
            vendasPage = service.searchByFilters(nomeCliente, valorMin, valorMax, pageable)
                    .map(VendaDTO::fromDomain);
        } else if (clienteId != null) {
            vendasPage = service.getByClienteIdPaginated(clienteId, pageable)
                    .map(VendaDTO::fromDomain);
        } else {
            vendasPage = service.getAllPaginated(pageable)
                    .map(VendaDTO::fromDomain);
        }

        return ResponseEntity.ok(vendasPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getById(@PathVariable UUID id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Venda create(@RequestBody Venda venda) {
        // Define a data automaticamente se não foi fornecida
        if (venda.getData() == null) {
            venda.setData(Instant.now());
        }
        return service.create(venda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> update(@PathVariable UUID id, @RequestBody Venda venda) {
        Venda updated = service.update(id, venda);
        if (updated != null) {
            return ResponseEntity.ok(updated);
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
