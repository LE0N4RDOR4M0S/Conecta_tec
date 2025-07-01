package br.com.leonardoramos.conecta_tec.service;

import br.com.leonardoramos.conecta_tec.dto.AssinaturaCadastroDTO;
import br.com.leonardoramos.conecta_tec.entity.Assinatura;
import br.com.leonardoramos.conecta_tec.repository.AssinaturaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository) {
        this.assinaturaRepository = assinaturaRepository;
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

}
