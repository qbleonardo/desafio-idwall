package com.desafioIdwall.usecase;

import com.desafioIdwall.domains.*;
import com.desafioIdwall.exceptions.NameInterpolNotFoundException;
import com.desafioIdwall.external.database.InterpolStorage;
import com.desafioIdwall.external.interpolclient.RedDetailsInterpolFeignClient;
import com.desafioIdwall.external.interpolclient.RedInterpolFeignClient;
import com.desafioIdwall.external.interpolclient.response.DetailsInterpolResponse;
import com.desafioIdwall.external.interpolclient.response.EmbeddedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterpolUseCase {

    RedInterpolFeignClient redInterpol;
    RedDetailsInterpolFeignClient redDetailsInterpol;
    InterpolStorage interpolStorage;

    public InterpolUseCase(RedInterpolFeignClient redInterpol, RedDetailsInterpolFeignClient redDetailsInterpol, InterpolStorage interpolStorage) {
        this.redInterpol = redInterpol;
        this.redDetailsInterpol = redDetailsInterpol;
        this.interpolStorage = interpolStorage;
    }

    public ResponseEntity<CriminosoInterpol> execute(String nome){
        String entityId;

        try{
            ResponseEntity<EmbeddedResponse> redNoticesResponse = redInterpol.getRedNotices(nome);
            entityId = redNoticesResponse.getBody().getEmbedded().getNotices().get(0).getEntityId();
        } catch (Exception ex){
            throw new NameInterpolNotFoundException();
        }

        String entityIdReplaced = entityId.replace("/", "-");

        ResponseEntity<DetailsInterpolResponse> redNoticeDetailsResponse = redDetailsInterpol.getRedNoticeDetails(entityIdReplaced);

        CriminosoInterpol responseInterpol = createCriminosoToResponse(redNoticeDetailsResponse);

        interpolStorage.save(responseInterpol);

        return new ResponseEntity<>(responseInterpol, redNoticeDetailsResponse.getStatusCode());
    }

    private CriminosoInterpol createCriminosoToResponse(ResponseEntity<DetailsInterpolResponse> redNoticeDetailsResponse) {
        return new CriminosoInterpol(redNoticeDetailsResponse.getBody().getEntityId(), redNoticeDetailsResponse.getBody().getForename(),
                            redNoticeDetailsResponse.getBody().getDateOfBirth(),
                            Caracteristicas.builder()
                                    .sexo(redNoticeDetailsResponse.getBody().getSexId())
                                    .altura(redNoticeDetailsResponse.getBody().getHeight())
                                    .cabelo(Optional.ofNullable(redNoticeDetailsResponse.getBody().getHairsId()).orElse(List.of("Informação não obtida")).toString())
                                    .build(),
                            Nacionalidade.builder()
                                    .tipo(redNoticeDetailsResponse.getBody().getNationalities().get(0))
                                    .build(),
                            Categoria.builder()
                                    .tipoCategoria(redNoticeDetailsResponse.getBody().getArrestWarrants().get(0).getCharge())
                                    .build(),
                            Idioma.builder()
                                    .idioma(redNoticeDetailsResponse.getBody().getLanguagesSpokenIds().get(0))
                                    .build(),
                            Nascimento.builder().paisId(redNoticeDetailsResponse.getBody().getCountryOfBirthId())
                                    .local(redNoticeDetailsResponse.getBody().getPlaceOfBirth()).build(),
                            ImagemInterpol.builder().imagem(redNoticeDetailsResponse.getBody().getLinks().getThumbnail().getHref()).build());
    }
}
