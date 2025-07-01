package br.com.leonardoramos.conecta_tec.dto;

import lombok.Getter;

@Getter
public class LojaCadastroDTO {
    private String nomeLoja;
    private String telefoneContato;
    private String endereco;

    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
}
