package br.com.leonardoramos.conecta_tec.repository;

import br.com.leonardoramos.conecta_tec.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
    /**
     * Busca todas as categorias associadas a uma loja específica.
     * @param lojaId ID da loja cujas categorias serão buscadas.
     * @return  Uma lista de categorias associadas à loja especificada.
     */
    List<Categoria> findAllByLojaId(UUID lojaId);
}
