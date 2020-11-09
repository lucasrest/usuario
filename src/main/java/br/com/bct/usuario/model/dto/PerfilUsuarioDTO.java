package br.com.bct.usuario.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PerfilUsuarioDTO extends EntidadeBaseDTO {

    private PerfilDTO perfil;
    @JsonBackReference(value = "perfil-usuario")
    private UsuarioDTO usuario;
}
