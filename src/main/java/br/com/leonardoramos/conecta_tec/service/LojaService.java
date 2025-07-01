package br.com.leonardoramos.conecta_tec.service;


import br.com.leonardoramos.conecta_tec.dto.LojaCadastroDTO;
import br.com.leonardoramos.conecta_tec.entity.Loja;
import br.com.leonardoramos.conecta_tec.entity.Usuario;
import br.com.leonardoramos.conecta_tec.repository.LojaRepository;
import br.com.leonardoramos.conecta_tec.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LojaService {

    private final UsuarioRepository usuarioRepository;
    private final LojaRepository lojaRepository;
    private final PasswordEncoder passwordEncoder;

    public LojaService(UsuarioRepository usuarioRepository, LojaRepository lojaRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.lojaRepository = lojaRepository;
        this.passwordEncoder = passwordEncoder;

    }

    /**
     * Busca uma loja pelo ID.
     * @param id O ID da loja a ser buscada, no formato String.
     * @return A entidade Loja correspondente ao ID fornecido.
     */
    public Optional<Loja> buscarLojaPorId(String id) {
        return lojaRepository.findById(UUID.fromString(id));
    }

    /**
     * Busca uma loja pelo ID do usuário.
     * @param idUsuario O ID do usuário associado à loja.
     * @return A entidade Loja correspondente ao ID do usuário fornecido.
     */
    public Optional<Loja> buscarLojaPorIdUsuario(String idUsuario) {
        return lojaRepository.findByUsuarioId(UUID.fromString(idUsuario));
    }

    /**
     * Busca todas as lojas cadastradas no sistema.
     * @return  Uma lista de objetos Optional contendo todas as lojas.
     */
    public List<Optional<Loja>> buscarTodasLojas() {
        return lojaRepository.findAll().stream()
                .map(Optional::of)
                .toList();
    }

    /**
     * Atualiza os dados de uma loja existente.
     * @param dto Os dados de atualização da loja.
     * @param id  O ID da loja a ser atualizada, no formato String.
     * @return Um Optional contendo a entidade Loja atualizada, se encontrada.
     */
    public Optional<Loja> updateLoja(LojaCadastroDTO dto, String id) {
        return lojaRepository.findById(UUID.fromString(id))
                .map(existingLoja -> {
                    existingLoja.setNome(dto.getNomeLoja());
                    existingLoja.setTelefoneContato(dto.getTelefoneContato());
                    existingLoja.setEndereco(dto.getEndereco());

                    return lojaRepository.save(existingLoja);
                });
    }

    /**
     * Exclui uma loja pelo ID.
     * @param id O ID da loja a ser excluída, no formato String.
     */
    public void excluirLoja(String id) {
        lojaRepository.findById(UUID.fromString(id))
                .ifPresent(loja -> {
                    lojaRepository.delete(loja);
                });
    }

    /**
     * Cadastra um novo usuário e sua loja associada.
     * A operação é transacional: ou tudo é salvo, ou nada é.
     * @param dto Os dados de cadastro.
     * @return A entidade Loja que foi criada.
     */
    @Transactional
    public Loja cadastrarLoja(LojaCadastroDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmailUsuario()).isPresent()) {
            throw new IllegalStateException("E-mail já cadastrado no sistema.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.getNomeUsuario());
        novoUsuario.setEmail(dto.getEmailUsuario());
        novoUsuario.setSenha(passwordEncoder.encode(dto.getSenhaUsuario()));

        Loja novaLoja = new Loja();
        novaLoja.setNome(dto.getNomeLoja());
        novaLoja.setTelefoneContato(dto.getTelefoneContato());
        novaLoja.setEndereco(dto.getEndereco());

        novaLoja.setUsuario(novoUsuario);
        novoUsuario.setLoja(novaLoja);

        usuarioRepository.save(novoUsuario);

        return novaLoja;
    }
}
