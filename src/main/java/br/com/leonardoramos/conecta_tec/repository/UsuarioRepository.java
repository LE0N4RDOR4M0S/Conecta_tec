package br.com.leonardoramos.conecta_tec.repository;

import br.com.leonardoramos.conecta_tec.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    /**
     * Busca um usuário pelo seu email.
     * @param email Email do usuário a ser buscado.
     * @return Um Optional contendo o usuário se encontrado, ou vazio caso não exista.
     */
    Optional<Usuario> findByEmail(String email);
}
