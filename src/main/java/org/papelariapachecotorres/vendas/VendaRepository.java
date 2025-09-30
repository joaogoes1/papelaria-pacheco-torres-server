package org.papelariapachecotorres.vendas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface VendaRepository extends JpaRepository<Venda, UUID> {
    List<Venda> findByClienteId(UUID clienteId);

    Page<Venda> findByClienteId(UUID clienteId, Pageable pageable);

    @Query("SELECT v FROM Venda v WHERE v.data BETWEEN :startDate AND :endDate")
    Page<Venda> findByDateRange(@Param("startDate") Instant startDate,
                                @Param("endDate") Instant endDate,
                                Pageable pageable);

    @Query("SELECT v FROM Venda v WHERE " +
           "COALESCE(:clienteId, v.clienteId) = v.clienteId AND " +
           "v.data >= COALESCE(:startDate, CAST('1970-01-01T00:00:00Z' AS timestamp)) AND " +
           "v.data <= COALESCE(:endDate, CAST('2099-12-31T23:59:59Z' AS timestamp))")
    Page<Venda> findByFilters(@Param("clienteId") UUID clienteId,
                             @Param("startDate") Instant startDate,
                             @Param("endDate") Instant endDate,
                             Pageable pageable);

    @Query("SELECT v FROM Venda v JOIN org.papelariapachecotorres.clientes.Cliente c ON v.clienteId = c.id " +
           "WHERE (:nomeCliente IS NULL OR LOWER(c.nome) LIKE LOWER(CONCAT('%', :nomeCliente, '%'))) " +
           "AND (:valorMin IS NULL OR v.total >= :valorMin) " +
           "AND (:valorMax IS NULL OR v.total <= :valorMax)")
    Page<Venda> findByFiltersAdvanced(@Param("nomeCliente") String nomeCliente,
                                      @Param("valorMin") java.math.BigDecimal valorMin,
                                      @Param("valorMax") java.math.BigDecimal valorMax,
                                      Pageable pageable);
}
