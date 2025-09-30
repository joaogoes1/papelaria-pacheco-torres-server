package org.papelariapachecotorres.estoque;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, UUID> {
    List<Estoque> findByProdutoId(UUID produtoId);

    @Query("SELECT e FROM Estoque e WHERE e.quantidade <= e.quantidadeMinima")
    Page<Estoque> findLowStock(Pageable pageable);

    @Query("SELECT e FROM Estoque e JOIN Produto p ON e.produtoId = p.id WHERE " +
           "LOWER(p.nome) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.codigo) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Estoque> findByProductSearch(@Param("search") String search, Pageable pageable);
}
