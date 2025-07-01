package br.com.leonardoramos.conecta_tec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "planos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column
    private String descricao;

    @Column(name = "limite_produtos")
    private Integer limiteProdutos;

    @Column(name = "permite_ordens_de_servico", nullable = false)
    private boolean permiteOrdensDeServico;

    @Column(name = "permite_banco_de_produtos", nullable = false)
    private boolean permiteBancoDeProdutos;

    @Column(nullable = false)
    private boolean ativo = true;
}
