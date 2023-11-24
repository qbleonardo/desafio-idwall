package com.desafioIdwall.external.interpolclient;

import com.desafioIdwall.external.interpolclient.response.EmbeddedResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GetRedNotices", url = "https://ws-public.interpol.int/")
public interface RedInterpolFeignClient {

    @GetMapping(value = "/notices/v1/red/")
    ResponseEntity<EmbeddedResponse> getRedNotices(@RequestParam(value = "forename") String forename);
}
