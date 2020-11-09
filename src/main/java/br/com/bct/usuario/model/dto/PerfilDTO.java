package br.com.bct.usuario.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PerfilDTO extends EntidadeBaseDTO {

    private String codigo;
    private String nome;
    @JsonManagedReference(value = "perfil-operacoes")
    private List<PerfilOperacaoDTO> perfilOperacoes;
}
