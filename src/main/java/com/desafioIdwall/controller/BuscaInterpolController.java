package com.desafioIdwall.controller;

import com.desafioIdwall.domains.CriminosoInterpol;
import com.desafioIdwall.exceptions.response.ErrorData;
import com.desafioIdwall.usecase.InterpolUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/interpol")
public class BuscaInterpolController {

    @Autowired
    InterpolUseCase interpolUseCase;

    @GetMapping("/busca-nome")
    @Operation(summary = "Buscar por nome", description = "Método responsável por realizar a busca por nome, retornando as informações do criminoso através do retorno consumido pela API da Interpol.",
    tags = "Pesquisa por nome - Interpol")
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CriminosoInterpol.class)))
    @ApiResponse(responseCode = "400", description = "Nome não encontrado ou formato incorreto no campo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorData.class)))
    public ResponseEntity<CriminosoInterpol> buscaPorNome(@RequestParam(value = "nome") @Parameter(name = "nome", required = true, example = "NOEL ANTONIO", description = "Campo obrigatório para busca das informações do criminoso") String nome){
        return interpolUseCase.execute(nome);
    }
}
