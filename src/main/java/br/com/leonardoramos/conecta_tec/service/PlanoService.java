package br.com.leonardoramos.conecta_tec.service;

import br.com.leonardoramos.conecta_tec.entity.Plano;
import br.com.leonardoramos.conecta_tec.repository.PlanoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public Optional<Plano> buscarPlanoPorId(String id) {
        return planoRepository.findById(UUID.fromString(id));
    }
}
