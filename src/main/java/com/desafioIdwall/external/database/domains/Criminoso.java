package com.desafioIdwall.external.database.domains;

import com.desafioIdwall.domains.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "criminosos")
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Criminoso {

    private String nome;
    private String dataNascimento;
    private Caracteristicas caracteristicas;
    private Nacionalidade nacionalidade;
    private Categoria categoria;
    private String idade;
    private Informacoes informacoes;
    private ImagemFbi imagens;
    private Idioma idiomaFalado;
    private Nascimento localNascimento;
    private ImagemInterpol imagem;

}
