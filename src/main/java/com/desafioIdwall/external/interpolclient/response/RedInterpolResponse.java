package com.desafioIdwall.external.interpolclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedInterpolResponse implements Serializable {

    private String forename;
    @JsonProperty(value = "entity_id")
    private String entityId;
}
