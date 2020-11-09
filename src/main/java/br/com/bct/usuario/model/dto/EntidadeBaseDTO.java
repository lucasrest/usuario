package br.com.bct.usuario.model.dto;

import br.com.bct.usuario.model.EntidadeAPI;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EntidadeBaseDTO extends EntidadeAPI {

    private Long id;
}