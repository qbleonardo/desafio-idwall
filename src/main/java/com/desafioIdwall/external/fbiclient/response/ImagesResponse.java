package com.desafioIdwall.external.fbiclient.response;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImagesResponse implements Serializable {

    private String caption;
    private String original;
    private String large;
    private String thumb;
}
