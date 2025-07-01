package br.com.leonardoramos.conecta_tec.repository;

import br.com.leonardoramos.conecta_tec.entity.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LojaRepository extends JpaRepository<Loja, UUID> {
    /**
     * Busca uma loja pelo ID do usuário associado.
     * @param usuarioId ID do usuário associado à loja.
     * @return Um Optional contendo a loja se encontrada, ou vazio caso não exista.
     */
    Optional<Loja> findByUsuarioId(UUID usuarioId);
}
