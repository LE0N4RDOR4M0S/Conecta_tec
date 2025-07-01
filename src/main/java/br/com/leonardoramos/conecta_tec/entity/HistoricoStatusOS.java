package br.com.leonardoramos.conecta_tec.entity;

import br.com.leonardoramos.conecta_tec.entity.enums.StatusOrdemServico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "historico_status_os")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoStatusOS {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_novo", nullable = false, length = 50)
    private StatusOrdemServico statusNovo;

    @Column
    private String observacao;

    @CreationTimestamp
    @Column(name = "data_alteracao", updatable = false)
    private LocalDateTime dataAlteracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordem_servico_id", nullable = false)
    private OrdemServico ordemServico;
}
