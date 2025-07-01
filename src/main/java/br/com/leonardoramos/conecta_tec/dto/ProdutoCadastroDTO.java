package br.com.leonardoramos.conecta_tec.dto;

import br.com.leonardoramos.conecta_tec.entity.enums.StatusProduto;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class ProdutoCadastroDTO {
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private StatusProduto status;
    private UUID categoriaId;
}
