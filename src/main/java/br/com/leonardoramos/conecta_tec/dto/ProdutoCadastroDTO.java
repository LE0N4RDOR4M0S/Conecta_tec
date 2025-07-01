package br.com.leonardoramos.conecta_tec.dto;

import br.com.leonardoramos.conecta_tec.entity.Categoria;
import br.com.leonardoramos.conecta_tec.entity.enums.StatusProduto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class ProdutoCadastroDTO {
    @NotBlank(message = "O nome do produto não pode ser nulo ou vazio.")
    private String nome;

    @NotBlank(message = "A descrição do produto não pode ser nulo ou vazio.")
    private String descricao;

    @NotBlank(message = "O preço do produto não pode ser nulo ou vazio.")
    private BigDecimal preco;

    @NotBlank(message = "O status do produto não pode ser nulo ou vazio.")
    private StatusProduto status;

    @NotBlank(message = "O ID da categoria não pode ser nulo ou vazio.")
    private UUID categoriaId;
}
