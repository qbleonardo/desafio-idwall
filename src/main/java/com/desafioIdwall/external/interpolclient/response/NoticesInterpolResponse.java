package com.desafioIdwall.external.interpolclient.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticesInterpolResponse implements Serializable {

    private List<RedInterpolResponse> notices;
}
