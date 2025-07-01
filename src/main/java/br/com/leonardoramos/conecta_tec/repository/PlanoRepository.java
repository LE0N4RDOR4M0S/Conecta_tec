package br.com.leonardoramos.conecta_tec.repository;

import br.com.leonardoramos.conecta_tec.entity.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, UUID> {
}
