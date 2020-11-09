package br.com.bct.usuario.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_perfil_usuario", schema = "bct")
@SequenceGenerator(sequenceName = "bct.seq_tb_perfil_usuario", name = "id", schema = "bct", allocationSize = 1)
public class PerfilUsuario extends EntidadeBase {
	
    @ManyToOne
    @JsonBackReference("perfil-usuario")
    @JoinColumn(name="id_perfil", referencedColumnName = "id")
	private Perfil perfil;

    @ManyToOne
    @JsonBackReference("usuario")
    @JoinColumn(name="id_usuario", referencedColumnName = "id")
	private Usuario usuario;
}
