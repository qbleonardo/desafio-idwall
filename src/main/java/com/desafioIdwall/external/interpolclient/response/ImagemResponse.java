package com.desafioIdwall.external.interpolclient.response;

import lombok.*;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImagemResponse {

    private ThumbnailResponse thumbnail;
}
