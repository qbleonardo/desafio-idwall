package com.desafioIdwall.domains;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Criminoso {

    private String nome;
    private String dataNascimento;
    private Caracteristicas caracteristicas;
    private Nacionalidade nacionalidade;
    private Categoria categoria;


}
