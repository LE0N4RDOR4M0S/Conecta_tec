package br.com.leonardoramos.conecta_tec.repository;

import br.com.leonardoramos.conecta_tec.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    /** Lista todos os produtos associados a uma loja específica.
     * @param lojaId ID da loja cujos produtos serão buscados.
     * @return Uma lista de produtos associados à loja especificada.
     */
    List<Produto> findAllByLojaId(UUID lojaId);

    /** Busca produtos por nome, ignorando maiúsculas e minúsculas.
     *  @param lojaId ID da loja cujos produtos serão buscados.
     *  @param nome Nome do produto a ser buscado, podendo conter parte do nome.
     *  @return Uma lista de produtos cujo nome contém a string especificada, ignorando maiúsculas e minúsculas.
     */
    List<Produto> findAllByLojaIdAndNomeContainingIgnoreCase(UUID lojaId, String nome);
}
