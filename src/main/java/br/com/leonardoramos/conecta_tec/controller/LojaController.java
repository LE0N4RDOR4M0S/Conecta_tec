package br.com.leonardoramos.conecta_tec.controller;

import br.com.leonardoramos.conecta_tec.dto.LojaCadastroDTO;
import br.com.leonardoramos.conecta_tec.entity.Loja;
import br.com.leonardoramos.conecta_tec.repository.LojaRepository;
import br.com.leonardoramos.conecta_tec.service.LojaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/lojas")
public class LojaController {

    private final LojaService lojaService;
    private final LojaRepository lojaRepository; // Injetado para a busca pública

    public LojaController(LojaService lojaService, LojaRepository lojaRepository) {
        this.lojaService = lojaService;
        this.lojaRepository = lojaRepository;
    }

    /**
     * Endpoint para cadastrar um novo usuário e sua loja.
     * HTTP POST /api/lojas/cadastro
     * @param dto Os dados para o cadastro.
     * @return A entidade Loja criada com status 201 Created.
     */
    @PostMapping("/cadastro")
    public ResponseEntity<Loja> cadastrarLoja(@Valid @RequestBody LojaCadastroDTO dto) {
        Loja novaLoja = lojaService.cadastrarLoja(dto);
        // Retorna a loja criada e o status HTTP 201 (Created)
        return new ResponseEntity<>(novaLoja, HttpStatus.CREATED);
    }

    /**
     * Endpoint para buscar os dados públicos de uma loja pelo seu ID.
     * Esta será a página da vitrine do lojista.
     * HTTP GET /api/lojas/{id}
     * @param id O ID da loja a ser buscada.
     * @return Os dados da loja com status 200 OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Loja> getLojaPublica(@PathVariable UUID id) {
        Loja loja = lojaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com o ID: " + id));
        return ResponseEntity.ok(loja);
    }
}
