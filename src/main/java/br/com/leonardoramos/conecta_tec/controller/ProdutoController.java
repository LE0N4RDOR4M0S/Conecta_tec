package br.com.leonardoramos.conecta_tec.controller;

import br.com.leonardoramos.conecta_tec.dto.ProdutoCadastroDTO;
import br.com.leonardoramos.conecta_tec.entity.Produto;
import br.com.leonardoramos.conecta_tec.service.ProdutoService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lojas/{lojaId}/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /**
     * Endpoint para adicionar um novo produto ao catálogo de uma loja.
     * Recebe dados do produto (JSON) e a imagem (multipart/form-data).
     * HTTP POST /api/lojas/{lojaId}/produtos
     *
     * @param lojaId O ID da loja (da URL).
     * @param dto Os dados do produto em JSON.
     * @param imagem O arquivo de imagem.
     * @return O produto criado com status 201 Created.
     * @throws IOException Se houver um erro no upload.
     */
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Produto> adicionarProduto(
            @PathVariable UUID lojaId,
            @Valid @RequestPart("produto") ProdutoCadastroDTO dto,
            @RequestPart("imagem") MultipartFile imagem) throws IOException {

        // IMPORTANTE: Em um sistema real, aqui você deve ter uma camada de segurança (ex: Spring Security com JWT)
        // para verificar se o usuário autenticado é de fato o dono da 'lojaId'.

        Produto novoProduto = produtoService.adicionarProduto(dto, imagem, lojaId);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    /**
     * Endpoint para listar todos os produtos de uma loja específica.
     * HTTP GET /api/lojas/{lojaId}/produtos
     *
     * @param lojaId O ID da loja (da URL).
     * @return Uma lista de produtos com status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutosPorLoja(@PathVariable UUID lojaId) {
        // Esta é uma chamada hipotética, você adicionaria este método ao seu ProdutoService
        // List<Produto> produtos = produtoService.listarProdutosDaLoja(lojaId);
        // Por simplicidade, vamos pular a camada de serviço aqui para demonstração.
        // Em uma aplicação real, a lógica estaria no ProdutoService.
        // return ResponseEntity.ok(produtos);

        // Este endpoint ainda precisa da implementação no serviço, mas a estrutura do controller está aqui.
        return ResponseEntity.ok().build(); // Retorno temporário
    }
}
