package br.com.bct.usuario.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OperacaoDTO extends EntidadeBaseDTO {

    private String codigo;

    private String uri;

    private String descricao;
}
