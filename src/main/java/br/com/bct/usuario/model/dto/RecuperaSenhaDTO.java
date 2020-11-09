package br.com.bct.usuario.model.dto;

import br.com.bct.usuario.anotation.ValidPassword;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RecuperaSenhaDTO extends EntidadeBaseDTO {

    @ValidPassword
    @NotEmpty
    private String senha;
    @NotEmpty
    private String confirmarSenha;
    @NotEmpty
    private String codigoRecuperacao;

}