package br.com.leonardoramos.conecta_tec.repository;

import br.com.leonardoramos.conecta_tec.entity.Assinatura;
import br.com.leonardoramos.conecta_tec.entity.enums.StatusAssinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, UUID> {
    /**
     * Busca uma assinatura pelo ID da loja.
     * @param lojaId ID da loja associada à assinatura.
     * @return Uma assinatura se encontrada, ou null caso não exista.
     */
    Optional<Assinatura> findByLojaId(UUID lojaId);

    /**
     * Busca todas as assinaturas com o status e data de fim especificados.
     * @param status Status da assinatura a ser buscada.
     * @param dataFim Data de fim a ser buscada.
     * @param dataFim2 Data de fim adicional para o intervalo de busca.
     * @return Uma lista de assinaturas que correspondem ao status e intervalo de data de fim especificados.
     */
    List<Assinatura> findAllByStatusAndDataFimBetween(
            StatusAssinatura status, LocalDateTime dataFim, LocalDateTime dataFim2
    );
}
