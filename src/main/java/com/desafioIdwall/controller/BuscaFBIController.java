package com.desafioIdwall.controller;

import com.desafioIdwall.domains.CriminosoFbi;
import com.desafioIdwall.exceptions.response.ErrorData;
import com.desafioIdwall.usecase.CategoriaFbiUseCase;
import com.desafioIdwall.usecase.NomeFbiUseCase;
import com.desafioIdwall.usecase.enums.CategoriaEnums;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/fbi")
public class BuscaFBIController {
    @Autowired
    NomeFbiUseCase nomeFbiUseCase;

    @Autowired
    CategoriaFbiUseCase categoriaFbiUseCase;

    @GetMapping(value = "/busca-nome")
    @Operation(summary = "Buscar por nome", description = "Método responsável por realizar a busca por nome, retornando as informações do criminoso através do retorno consumido pela API do FBI.",
            tags = {"Pesquisa por nome - FBI"})
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CriminosoFbi.class)))
    @ApiResponse(responseCode = "400", description = "Nome não encontrado ou formato incorreto no campo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorData.class)))
    public ResponseEntity<CriminosoFbi> buscaPorNome(@NotBlank(message = "O campo não poderá ser vazio ou nulo") @RequestParam(value = "nome") @Parameter(name = "nome", required = true, example = "HAN LINLIN", description = "Campo obrigatório para busca das informações do criminoso") String nome){
        return nomeFbiUseCase.execute(nome);
    }

    @GetMapping(value = "/busca-lista-categoria")
    @Operation(summary = "Buscar por categoria", description = "Conforme as categorias disponíveis para busca, o método será responsável pelo retorno de uma lista contendo todos os criminosos que pertencem a esta categoria.",
            tags = {"Pesquisa por categoria - FBI"})
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CriminosoFbi.class))))
    @ApiResponse(responseCode = "400", description = "Categoria não encontrada ou formato incorreto no campo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorData.class)))
    public ResponseEntity<List<CriminosoFbi>> buscaPorListaCategoria(@RequestParam(value = "categoria") @Parameter(name = "categoria", required = true, description = "Campo obrigatório para buscar lista de criminosos da categoria") CategoriaEnums categoria){
        return categoriaFbiUseCase.execute(categoria);
    }
}
