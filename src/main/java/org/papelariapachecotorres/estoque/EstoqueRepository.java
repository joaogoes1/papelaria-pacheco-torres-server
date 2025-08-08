package org.papelariapachecotorres.estoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
    List<Estoque> findByProdutoId(Integer produtoId);
}
