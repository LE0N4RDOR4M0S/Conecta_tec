package br.com.leonardoramos.conecta_tec.repository;

import br.com.leonardoramos.conecta_tec.entity.VariacaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VariacaoProdutoRepository extends JpaRepository<VariacaoProduto, UUID> {
    /**
     * Busca todas as variações de produto associadas a um produto específico.
     * @param produtoId ID do produto cujas variações serão buscadas.
     * @return Uma lista de variações de produto associadas ao produto especificado.
     */
    List<VariacaoProduto> findAllByProdutoId(UUID produtoId);
}
