package br.com.leonardoramos.conecta_tec.service;

import br.com.leonardoramos.conecta_tec.dto.AssinaturaCadastroDTO;
import br.com.leonardoramos.conecta_tec.entity.Assinatura;
import br.com.leonardoramos.conecta_tec.repository.AssinaturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;

    private final LojaService lojaService;
    private final PlanoService planoService;

    public AssinaturaService(AssinaturaRepository assinaturaRepository, LojaService lojaService, PlanoService planoService) {
        this.assinaturaRepository = assinaturaRepository;
        this.lojaService = lojaService;
        this.planoService = planoService;
    }

    /**
     * Busca uma assinatura pelo ID da loja.
     * @param lojaId ID da loja associada à assinatura, no formato String.
     * @return Um objeto Optional contendo a assinatura se encontrada, ou vazio caso não exista.
     */
    public Optional<Assinatura> buscarAssinaturaPorLojaId(String lojaId) {
        return assinaturaRepository.findByLojaId(UUID.fromString(lojaId));
    }

    /**
     * Salva uma assinatura no repositório.
     * @param dto A assinatura a ser salva.
     * @return A assinatura salva.
     */
    public Assinatura salvarAssinatura(AssinaturaCadastroDTO dto) {
        Assinatura assinatura = new Assinatura();
        //TODO:
//        assinatura.setPlano(dto.getPlanoId());
//        assinatura.setLoja(dto.getLojaId());
//        assinatura.setDataInicio(java.time.LocalDateTime.now());
//        assinatura.setStatus(dto.getStatus());
        return assinaturaRepository.save(assinatura);
    }

    /**
     * Busca uma assinatura pelo ID.
     * @param id O ID da assinatura a ser buscada, no formato String.
     * @return Um objeto Optional contendo a assinatura se encontrada, ou vazio caso não exista.
     */
    public Optional<Assinatura> buscarAssinaturaPorId(String id) {
        return assinaturaRepository.findById(UUID.fromString(id));
    }

    /**
     *
     */
    public Optional<List<Assinatura>> buscarTodasAssassinaturas() {
        return Optional.of(assinaturaRepository.findAll());
    }

    /**
     * Exclui uma assinatura pelo ID.
     * @param id O ID da assinatura a ser excluída, no formato String.
     */
    public void excluirAssinatura(String id) {
        assinaturaRepository.deleteById(UUID.fromString(id));
    }

    /**
     * Atualiza uma assinatura existente.
     * @param dto A assinatura a ser atualizada.
     * @param id O ID da assinatura a ser atualizada, no formato String.
     * @return A assinatura atualizada.
     */
    public Optional<Assinatura> updateAssinatura(AssinaturaCadastroDTO dto, String id){
        Optional<Assinatura> existente = assinaturaRepository.findById(UUID.fromString(id));
        if (existente.isEmpty()) {
            throw new RuntimeException("Assinatura não encontrada");
        }
        existente.get().setPlano(planoService.buscarPlanoPorId(dto.getPlanoId()).get());
        existente.get().setLoja(lojaService.buscarLojaPorId(dto.getLojaId()).get());
        existente.get().setStatus(dto.getStatus());
        return Optional.of(assinaturaRepository.save(existente.get()));
    }

}
