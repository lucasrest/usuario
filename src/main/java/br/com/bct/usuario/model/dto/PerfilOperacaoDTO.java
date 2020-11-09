package br.com.bct.usuario.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PerfilOperacaoDTO extends EntidadeBaseDTO {

    @JsonBackReference(value = "perfil-operacoes")
    private PerfilDTO perfil;
    private OperacaoDTO operacao;
}
