package br.com.bct.usuario.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RecuperarContaDTO extends EntidadeBaseDTO {

    private String codigoRecuperacao;
    private LocalDateTime expiracao;
    private UsuarioDTO usuario;
}
