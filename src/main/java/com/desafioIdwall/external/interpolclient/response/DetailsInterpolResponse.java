package com.desafioIdwall.external.interpolclient.response;

import com.desafioIdwall.external.interpolclient.model.ArrestWarrants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailsInterpolResponse implements Serializable {

    @JsonProperty(value = "entity_id")
    private String entityId;
    @JsonProperty(value = "arrest_warrants")
    private List<ArrestWarrants> arrestWarrants;
    private String forename;
    @JsonProperty(value = "date_of_birth")
    private String dateOfBirth;
    private List<String> nationalities;
    @JsonProperty(value = "country_of_birth_id")
    private String countryOfBirthId;
    @JsonProperty(value = "place_of_birth")
    private String placeOfBirth;
    @JsonProperty(value = "languages_spoken_ids")
    private List<String>  languagesSpokenIds;
    private String height;
    @JsonProperty(value = "sex_id")
    private String sexId;
    @JsonProperty(value = "eyes_colors_id")
    private List<String> eyesColorsId;
    @JsonProperty(value = "hairs_id")
    private List<String> hairsId;
    @JsonProperty(value = "_links")
    private ImagemResponse links;


}
