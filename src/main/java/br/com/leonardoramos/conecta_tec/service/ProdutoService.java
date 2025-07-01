package br.com.leonardoramos.conecta_tec.service;

import br.com.leonardoramos.conecta_tec.dto.ProdutoCadastroDTO;
import br.com.leonardoramos.conecta_tec.entity.Categoria;
import br.com.leonardoramos.conecta_tec.entity.Loja;
import br.com.leonardoramos.conecta_tec.entity.Produto;
import br.com.leonardoramos.conecta_tec.repository.CategoriaRepository;
import br.com.leonardoramos.conecta_tec.repository.LojaRepository;
import br.com.leonardoramos.conecta_tec.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final LojaRepository lojaRepository;
    private final CategoriaRepository categoriaRepository;
    private final FileStorageService fileStorageService;

    public ProdutoService(ProdutoRepository produtoRepository, LojaRepository lojaRepository, CategoriaRepository categoriaRepository, FileStorageService fileStorageService) {
        this.produtoRepository = produtoRepository;
        this.lojaRepository = lojaRepository;
        this.categoriaRepository = categoriaRepository;
        this.fileStorageService = fileStorageService;
    }

    /**
     * Busca um produto pelo ID.
     * @param id O ID do produto a ser buscado.
     * @return O produto encontrado ou uma exceção se não for encontrado.
     */
    public Produto buscarProdutoPorId(UUID id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + id));
    }

    /**
     * Busca todos os produtos de uma loja específica.
     * @param lojaId O ID da loja cujos produtos serão buscados.
     * @return Uma lista de produtos pertencentes à loja especificada.
     */
    public List<Produto> buscarProdutosPorLoja(String lojaId) {
        Loja loja = lojaRepository.findById(UUID.fromString(lojaId))
                .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com o ID: " + lojaId));
        return produtoRepository.findAllByLojaId(UUID.fromString(lojaId));
    }

    /**
     * Busca produtos de uma loja pelo nome.
     * @param lojaId O ID da loja cujos produtos serão buscados.
     * @param nome O nome do produto a ser buscado, podendo ser parcial.
     * @return Uma lista de produtos que pertencem à loja especificada e cujo nome contém a string fornecida.
     */
    public List<Produto> buscarProdutosPorLojaENome(String lojaId, String nome) {
        Loja loja = lojaRepository.findById(UUID.fromString(lojaId))
                .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com o ID: " + lojaId));
        return produtoRepository.findAllByLojaIdAndNomeContainingIgnoreCase(UUID.fromString(lojaId), nome);
    }

    /**
     * Busca todos os produtos cadastrados no sistema.
     * @return Uma lista de todos os produtos.
     */
    public List<Produto> buscarTodosProdutos() {
        return produtoRepository.findAll();
    }

    /**
     * Atualiza os dados de um produto existente.
     * @param dto Os dados do produto a serem atualizados.
     * @param id O ID do produto a ser atualizado.
     * @return Um Optional contendo o produto atualizado, se encontrado.
     */
    public Optional<Produto> updateProduto(ProdutoCadastroDTO dto, UUID id) {
        return produtoRepository.findById(id)
                .map(existingProduto -> {
                    existingProduto.setNome(dto.getNome());
                    existingProduto.setDescricao(dto.getDescricao());
                    existingProduto.setPreco(dto.getPreco());
                    existingProduto.setCategoria(categoriaRepository.findById(dto.getCategoriaId())
                            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + dto.getCategoriaId())));
                    return produtoRepository.save(existingProduto);
                });
    }

    /**
     * Exclui um produto pelo ID.
     * @param id O ID do produto a ser excluído.
     */
    public void excluirProduto(UUID id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + id));
        produtoRepository.delete(produto);
    }

    /**
     * Adiciona um novo produto ao catálogo de uma loja, incluindo o upload da imagem.
     * @param dto Os dados do produto.
     * @param imagem O arquivo de imagem do produto.
     * @param lojaId O ID da loja à qual o produto pertence.
     * @return O produto criado e salvo.
     * @throws IOException Se houver erro no upload do arquivo.
     */
    @Transactional
    public Produto adicionarProduto(ProdutoCadastroDTO dto, MultipartFile imagem, UUID lojaId) throws IOException {
        Loja loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada com o ID: " + lojaId));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + dto.getCategoriaId()));

        String urlImagem = fileStorageService.uploadFile(imagem);

        Produto novoProduto = new Produto();
        novoProduto.setNome(dto.getNome());
        novoProduto.setDescricao(dto.getDescricao());
        novoProduto.setPreco(dto.getPreco());
        novoProduto.setUrlImagemPrincipal(urlImagem);
        novoProduto.setLoja(loja);
        novoProduto.setCategoria(categoria);

        return produtoRepository.save(novoProduto);
    }
}
