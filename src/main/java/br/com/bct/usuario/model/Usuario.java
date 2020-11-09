package br.com.bct.usuario.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_usuario", schema = "bct")
@SequenceGenerator(sequenceName = "bct.seq_tb_usuario", name = "id", schema = "bct", allocationSize = 1)
public class Usuario extends EntidadeBase {

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;

    @NotEmpty
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;


    @JsonBackReference("usuario")
    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL})
    private List<PerfilUsuario> perfilUsuario = new ArrayList<>();

    public List<String> getPerfil() {
        return perfilUsuario.stream().map(perfUsuario -> perfUsuario.getPerfil().getNome()).collect(Collectors.toList());
    }
}