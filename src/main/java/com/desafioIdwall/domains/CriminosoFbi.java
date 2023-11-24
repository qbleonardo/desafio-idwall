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
public class CriminosoFbi extends Criminoso{

    @Id
    private String id;
    private String idade;
    private Informacoes informacoes;
    private ImagemFbi imagens;

    public CriminosoFbi(String id, String nome, String idade, String dataNascimento, Caracteristicas caracteristicas, Categoria categoria, Nacionalidade nacionalidade, Informacoes informacoes, ImagemFbi imagens) {
        super(nome, dataNascimento, caracteristicas, nacionalidade, categoria);
        this.id = id;
        this.idade = idade;
        this.informacoes = informacoes;
        this.imagens = imagens;
    }

}
