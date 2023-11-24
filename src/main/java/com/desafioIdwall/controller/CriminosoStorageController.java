package com.desafioIdwall.controller;

import com.desafioIdwall.domains.CriminosoFbi;
import com.desafioIdwall.exceptions.response.ErrorData;
import com.desafioIdwall.external.database.domains.Criminoso;
import com.desafioIdwall.usecase.StorageUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/storage")
public class CriminosoStorageController {
    @Autowired
    StorageUseCase storageUseCase;

    @GetMapping(path = "/busca-nome")
    @Operation(summary = "Buscar por nome", description = "Método responsável por realizar a busca por nome, retornando as informações dos criminosos armazenadas pela API do FBI e Interpool.",
            tags = {"Pesquisa por nome - Storage"})
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Criminoso.class))))
    @ApiResponse(responseCode = "404", description = "Nome não encontrado ou formato incorreto no campo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorData.class)))
    public List<Criminoso> getCriminosoByNome(@RequestParam(value = "nome") String nome){
        return storageUseCase.executeFindByName(nome);
    }

    @GetMapping(path = "/busca-ano")
    @Operation(summary = "Buscar por ano de nascimento", description = "Método responsável por realizar a busca por ano de nascimento, retornando as informações dos criminosos armazenadas pela API do FBI e Interpool.",
            tags = {"Pesquisa por ano de nascimento - Storage"})
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Criminoso.class))))
    @ApiResponse(responseCode = "404", description = "Ano de nascimento não encontrada ou formato incorreto no campo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorData.class)))
    public List<Criminoso> getCriminosoByAno(@RequestParam(value = "anoNascimento") @Pattern(regexp = "^\\d{4}$") String anoNascimento){
        return storageUseCase.executeFindByAno(anoNascimento);
    }
}