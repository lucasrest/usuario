package br.com.bct.cadastro.model.dto;

import br.com.bct.cadastro.model.EntidadeAPI;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EntidadeBaseDTO extends EntidadeAPI {

    private Long id;
}