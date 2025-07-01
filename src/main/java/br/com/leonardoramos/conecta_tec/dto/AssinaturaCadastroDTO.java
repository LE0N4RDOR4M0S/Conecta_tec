package br.com.leonardoramos.conecta_tec.dto;

import br.com.leonardoramos.conecta_tec.entity.enums.StatusAssinatura;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AssinaturaCadastroDTO {
    @NotBlank(message = "O ID do plano não pode ser nulo ou vazio.")
    private String planoId;

    @NotBlank(message = "O ID da loja não pode ser nulo ou vazio.")
    private String lojaId;

    @NotBlank(message = "O status da assinatura não pode ser nulo ou vazio.")
    private StatusAssinatura status;
}
