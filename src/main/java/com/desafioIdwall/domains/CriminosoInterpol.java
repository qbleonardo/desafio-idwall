package com.desafioIdwall.domains;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "criminosos")
@JsonPropertyOrder(value = {"id", "nome", "idade", "dataNascimento", "caracteristicas", "categoria", "nacionalidade", "informacoes", "imagens"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CriminosoInterpol extends Criminoso{

    @Id
    private String id;
    private Idioma idiomaFalado;
    private Nascimento localNascimento;
    private ImagemInterpol imagem;

    public CriminosoInterpol(String id, String nome, String dataNascimento, Caracteristicas caracteristicas, Nacionalidade nacionalidade, Categoria categoria, Idioma idiomaFalado, Nascimento localNascimento, ImagemInterpol imagem) {
        super(nome, dataNascimento, caracteristicas, nacionalidade, categoria);
        this.id = id;
        this.idiomaFalado = idiomaFalado;
        this.localNascimento = localNascimento;
        this.imagem = imagem;
    }

}
