package br.com.bct.cadastro.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TesteDTO extends EntidadeBaseDTO {

    private String nome;

    public TesteDTO(Long id, String nome) {
        super(id);
        this.nome = nome;
    }
}
