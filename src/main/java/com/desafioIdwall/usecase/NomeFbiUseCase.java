package com.desafioIdwall.usecase;

import com.desafioIdwall.domains.*;
import com.desafioIdwall.exceptions.NameFbiNotFoundException;
import com.desafioIdwall.external.database.FbiStorage;
import com.desafioIdwall.external.fbiclient.FbiFeignClient;
import com.desafioIdwall.external.fbiclient.response.FbiResponse;
import com.desafioIdwall.external.fbiclient.response.ItemFbiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class NomeFbiUseCase {

    FbiFeignClient fbiFeignClient;
    FbiStorage fbiStorage;


    public NomeFbiUseCase(FbiFeignClient fbiFeignClient, FbiStorage fbiStorage) {
        this.fbiFeignClient = fbiFeignClient;
        this.fbiStorage = fbiStorage;
    }

    public ResponseEntity<CriminosoFbi> execute(String nome) {
        ResponseEntity<ItemFbiResponse> criminosoResponse;
        FbiResponse fbiResponse;

        try {
            criminosoResponse = fbiFeignClient.wanted(nome, "default");
            fbiResponse = criminosoResponse.getBody().getItems().get(0);
        } catch (Exception ex) {
            throw new NameFbiNotFoundException();
        }

        CriminosoFbi criminosoFbi = createCrimonosoResponse(fbiResponse);

        fbiStorage.save(criminosoFbi);

        return new ResponseEntity<>(criminosoFbi, criminosoResponse.getStatusCode());
    }

    private CriminosoFbi createCrimonosoResponse(FbiResponse fbiResponse) {
        return new CriminosoFbi(fbiResponse.getUid(), fbiResponse.getTitle(), fbiResponse.getAgeMax(), fbiResponse.getDatesOfBirthUsed().get(0),
                Caracteristicas.builder()
                        .sexo(fbiResponse.getSex())
                        .altura(fbiResponse.getHeightMax())
                        .cabelo(fbiResponse.getHair())
                        .build(),
                Categoria.builder()
                        .tipoCategoria(fbiResponse.getSubjects().get(0))
                        .build(),
                Nacionalidade.builder()
                        .tipo(fbiResponse.getNationality())
                        .build(),
                Informacoes.builder()
                        .cuidados(fbiResponse.getCaution())
                        .descricao(fbiResponse.getDescription())
                        .build(),
                ImagemFbi.builder()
                        .imagemOriginal(fbiResponse.getImages().get(0).getOriginal())
                        .imagemGrande(fbiResponse.getImages().get(0).getLarge())
                        .thumb(fbiResponse.getImages().get(0).getThumb())
                        .legenda(fbiResponse.getImages().get(0).getCaption())
                        .build());
    }
}
