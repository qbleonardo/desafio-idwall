package com.desafioIdwall.external.interpolclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddedResponse implements Serializable {

    @JsonProperty(value = "_embedded")
    private NoticesInterpolResponse embedded;
}
