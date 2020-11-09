package br.com.bct.usuario.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "tb_operacao", schema = "bct")
@SequenceGenerator(sequenceName = "bct.seq_tb_operacao", name = "id", schema = "bct", allocationSize = 1)
public class Operacao extends EntidadeBase {

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String codigo;

    @NotEmpty
    @Column
    private String uri;

    @NotEmpty
    @Column
    private String descricao;


}
