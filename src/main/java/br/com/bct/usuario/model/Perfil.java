package br.com.bct.usuario.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_perfil", schema = "bct")
@SequenceGenerator(sequenceName = "bct.seq_tb_perfil", name = "id", schema = "bct", allocationSize = 1)
public class Perfil extends EntidadeBase {

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String nome;

    @JsonManagedReference("perfil-operacao")
    @OneToMany(mappedBy = "perfil", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<PerfilOperacao> perfilOperacoes = new ArrayList<>();

    @JsonManagedReference("perfil-usuario")
    @OneToMany(mappedBy = "perfil", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<PerfilUsuario> perfilUsuarios = new ArrayList<>();

}
