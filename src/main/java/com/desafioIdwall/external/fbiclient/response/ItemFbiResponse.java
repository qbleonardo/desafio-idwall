package com.desafioIdwall.external.fbiclient.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemFbiResponse implements Serializable {

    private List<FbiResponse> items;
}
