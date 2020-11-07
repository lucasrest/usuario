package br.com.bct.cadastro.model;

import br.com.bct.cadastro.anotation.DataAPI;
import br.com.bct.cadastro.enums.Status;
import br.com.bct.cadastro.util.LocalDateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class EntidadeBase extends EntidadeAPI {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    private Long id;

    @DataAPI
    @Column
    private LocalDateTime alteracao;

    @DataAPI
    @Column(nullable = false)
    private LocalDateTime inclusao;

    @Column
    private Integer status = Status.ATIVO.getValue();

    @PrePersist
    public void prePersist() {
        inclusao = LocalDateTimeUtils.dataAtual();
    }

    @PreUpdate
    public void preUpdate() {
        alteracao = LocalDateTimeUtils.dataAtual();
    }
}
