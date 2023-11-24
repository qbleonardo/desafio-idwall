package com.desafioIdwall.external.interpolclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArrestWarrants {

    @JsonProperty(value = "issuing_country_id")
    private String issuingCountryId;
    private String charge;
    @JsonProperty(value = "charge_translation")
    private String chargeTranslation;
}
