package br.com.bct.usuario.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tb_teste", schema = "bct")
@SequenceGenerator(sequenceName = "bct.seq_tb_teste", name = "id", schema = "bct", allocationSize = 1)
public class Teste extends EntidadeBase {

    @Column(nullable = false)
    private String nome;

}

