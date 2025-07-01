package br.com.leonardoramos.conecta_tec.entity;

import br.com.leonardoramos.conecta_tec.entity.enums.StatusOrdemServico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ordens_servico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome_cliente", nullable = false, length = 150)
    private String nomeCliente;

    @Column(name = "contato_cliente", nullable = false, length = 100)
    private String contatoCliente;

    @Column(name = "detalhes_equipamento", nullable = false, columnDefinition = "TEXT")
    private String detalhesEquipamento;

    @Column(name = "problema_relatado", nullable = false, columnDefinition = "TEXT")
    private String problemaRelatado;

    @Column(name = "observacoes_tecnicas", columnDefinition = "TEXT")
    private String observacoesTecnicas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private StatusOrdemServico status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id", nullable = false)
    private Loja loja;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricoStatusOS> historico = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}