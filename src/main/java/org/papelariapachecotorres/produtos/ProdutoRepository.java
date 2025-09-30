package org.papelariapachecotorres.produtos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    Optional<Produto> findByCodigo(String codigo);

    Page<Produto> findByCategoria(String categoria, Pageable pageable);

    @Query("SELECT p FROM Produto p WHERE " +
           "(LOWER(p.nome) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.codigo) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "AND (:categoria IS NULL OR p.categoria = :categoria)")
    Page<Produto> findBySearchAndCategory(@Param("search") String search,
                                          @Param("categoria") String categoria,
                                          Pageable pageable);
}
