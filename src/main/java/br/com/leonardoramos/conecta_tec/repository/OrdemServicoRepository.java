package br.com.leonardoramos.conecta_tec.repository;

import br.com.leonardoramos.conecta_tec.entity.OrdemServico;
import br.com.leonardoramos.conecta_tec.entity.enums.StatusOrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, UUID> {
    /**
     * Lista todas as ordens de serviço de uma loja.
     * @param lojaId O ID da loja.
     * @return Uma lista de ordens de serviço.
     */
    List<OrdemServico> findAllByLojaIdOrderByDataCriacaoDesc(UUID lojaId);

    /**
     * Lista todas as ordens de serviço de uma loja com um status específico.
     * Útil para filtrar o dashboard (ex: ver todas as OS "AGUARDANDO_PECA").
     * @param lojaId O ID da loja.
     * @param status O status a ser filtrado.
     * @return Uma lista de ordens de serviço.
     */
    List<OrdemServico> findAllByLojaIdAndStatus(UUID lojaId, StatusOrdemServico status);
}
