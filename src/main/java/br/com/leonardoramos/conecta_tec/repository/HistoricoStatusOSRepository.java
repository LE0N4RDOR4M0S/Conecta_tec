package br.com.leonardoramos.conecta_tec.repository;

import br.com.leonardoramos.conecta_tec.entity.HistoricoStatusOS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoricoStatusOSRepository extends JpaRepository<HistoricoStatusOS, UUID> {
    /**
     * Lista o histórico de uma ordem de serviço, ordenado pela data.
     * Essencial para montar a "linha do tempo" da OS.
     * @param ordemServicoId O ID da Ordem de Serviço.
     * @return Uma lista com o histórico de status.
     */
    List<HistoricoStatusOS> findAllByOrdemServicoIdOrderByDataAlteracaoAsc(UUID ordemServicoId);
}
