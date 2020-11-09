package br.com.bct.usuario.model.dto;

import br.com.bct.usuario.anotation.ValidPassword;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsuarioDTO extends EntidadeBaseDTO {

    private String codigo;
    private String email;
    private String nome;
    @NotEmpty
    @ValidPassword
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    @JsonManagedReference(value = "perfil-usuario")
    private List<PerfilUsuarioDTO> perfilUsuario;
}
