package br.com.leonardoramos.conecta_tec.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LojaCadastroDTO {
    @NotBlank(message = "O nome da loja não pode ser nulo ou vazio.")
    private String nomeLoja;

    @NotBlank(message = "O contato da loja não pode ser nulo ou vazio.")
    private String telefoneContato;

    private String endereco;

    @NotBlank(message = "O nome do usuário não pode ser nulo ou vazio.")
    private String nomeUsuario;

    @NotBlank(message = "O email do usuário não pode ser nulo ou vazio.")
    private String emailUsuario;

    @NotBlank(message = "A senha do usuário não pode ser nulo ou vazio.")
    private String senhaUsuario;
}
