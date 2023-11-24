package com.desafioIdwall.usecase;

import com.desafioIdwall.domains.*;
import com.desafioIdwall.external.database.FbiStorage;
import com.desafioIdwall.external.fbiclient.FbiFeignClient;
import com.desafioIdwall.external.fbiclient.response.FbiResponse;
import com.desafioIdwall.external.fbiclient.response.ItemFbiResponse;
import com.desafioIdwall.usecase.enums.CategoriaEnums;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.desafioIdwall.usecase.enums.CategoriaEnums.changeTypeCategoria;

@Service
public class CategoriaFbiUseCase {

    FbiFeignClient fbiFeignClient;
    FbiStorage fbiStorage;

    public CategoriaFbiUseCase(FbiFeignClient fbiFeignClient, FbiStorage fbiStorage) {
        this.fbiFeignClient = fbiFeignClient;
        this.fbiStorage = fbiStorage;
    }

    public ResponseEntity<List<CriminosoFbi>> execute(CategoriaEnums categoria){
        String posterClassification = "default";
        String tipoCategoria = changeTypeCategoria(categoria);

        ResponseEntity<ItemFbiResponse> responseFbi = fbiFeignClient.wanted(null, posterClassification);

        List<FbiResponse> response = responseFbi.getBody().getItems().stream()
                .filter(a -> a.getSubjects().get(0).equals(tipoCategoria) ||
                        a.getDescription().contains(tipoCategoria))
                .toList();

        List<CriminosoFbi> criminosoListResponse = createCriminosoResponse(response);

        criminosoListResponse.forEach(c -> fbiStorage.save(c));

        return new ResponseEntity<>(criminosoListResponse, HttpStatus.OK);
    }

    private List<CriminosoFbi> createCriminosoResponse(List<FbiResponse> fbiResponse) {
        return fbiResponse.stream().map(c ->
                new CriminosoFbi(c.getUid(), c.getTitle(), c.getAgeMax(), c.getDatesOfBirthUsed().get(0),
                        Caracteristicas.builder()
                                .sexo(c.getSex())
                                .altura(c.getHeightMax())
                                .cabelo(c.getHair())
                                .build(),
                        Categoria.builder()
                                .tipoCategoria(c.getSubjects().get(0))
                                .build(),
                        Nacionalidade.builder()
                                .tipo(c.getNationality())
                                .build(),
                        Informacoes.builder()
                                .cuidados(c.getCaution())
                                .descricao(c.getDescription())
                                .build(),
                        ImagemFbi.builder()
                                .imagemOriginal(c.getImages().get(0).getOriginal())
                                .imagemGrande(c.getImages().get(0).getLarge())
                                .thumb(c.getImages().get(0).getThumb())
                                .legenda(c.getImages().get(0).getCaption())
                                .build()))
                .collect(Collectors.toList());
    }
}
