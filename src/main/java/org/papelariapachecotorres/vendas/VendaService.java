package org.papelariapachecotorres.vendas;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.papelariapachecotorres.estoque.Estoque;
import org.papelariapachecotorres.estoque.EstoqueRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VendaService {
    private final VendaRepository repository;
    private final EstoqueRepository estoqueRepository;

    public VendaService(VendaRepository repository, EstoqueRepository estoqueRepository) {
        this.repository = repository;
        this.estoqueRepository = estoqueRepository;
    }

    public List<Venda> getAll() {
        return repository.findAll();
    }

    public Page<Venda> getAllPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Venda> searchPaginated(UUID clienteId, Instant startDate, Instant endDate, Pageable pageable) {
        if (clienteId != null || startDate != null || endDate != null) {
            return repository.findByFilters(clienteId, startDate, endDate, pageable);
        }
        return repository.findAll(pageable);
    }

    public List<Venda> getByClienteId(UUID clienteId) {
        return repository.findByClienteId(clienteId);
    }

    public Page<Venda> getByClienteIdPaginated(UUID clienteId, Pageable pageable) {
        return repository.findByClienteId(clienteId, pageable);
    }

    public Page<Venda> searchByFilters(String nomeCliente, java.math.BigDecimal valorMin, java.math.BigDecimal valorMax, Pageable pageable) {
        return repository.findByFiltersAdvanced(nomeCliente, valorMin, valorMax, pageable);
    }

    public Optional<Venda> getById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Venda create(Venda venda) {
        // Estabelece a relação bidirecional entre venda e seus itens
        if (venda.getItens() != null) {
            venda.getItens().forEach(item -> item.setVenda(venda));
        }

        // Atualiza o estoque para cada item vendido
        if (venda.getItens() != null) {
            for (Venda.ItemVenda item : venda.getItens()) {
                List<Estoque> estoqueList = estoqueRepository.findByProdutoId(item.getProdutoId());
                if (!estoqueList.isEmpty()) {
                    Estoque estoque = estoqueList.get(0); // Pega o primeiro registro
                    int novaQuantidade = estoque.getQuantidade() - item.getQuantidade();

                    if (novaQuantidade < 0) {
                        throw new RuntimeException("Estoque insuficiente para o produto: " + item.getProdutoId());
                    }

                    estoque.setQuantidade(novaQuantidade);
                    estoque.setUltimaAtualizacao(Instant.now());
                    estoqueRepository.save(estoque);
                } else {
                    throw new RuntimeException("Produto não encontrado no estoque: " + item.getProdutoId());
                }
            }
        }

        return repository.save(venda);
    }

    public Venda update(UUID id, Venda venda) {
        Optional<Venda> existing = repository.findById(id);
        if (existing.isPresent()) {
            Venda v = existing.get();
            v.setClienteId(venda.getClienteId());
            v.setItens(venda.getItens());
            v.setTotal(venda.getTotal());
            v.setData(venda.getData());
            return repository.save(v);
        }
        return null;
    }

    public boolean delete(UUID id) {
        Optional<Venda> venda = repository.findById(id);
        if (venda.isPresent()) {
            repository.delete(venda.get());
            return true;
        }
        return false;
    }
} 