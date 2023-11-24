package com.desafioIdwall.external.interpolclient;

import com.desafioIdwall.external.interpolclient.response.DetailsInterpolResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GetRedNoticeDetails", url = "https://ws-public.interpol.int/")
public interface RedDetailsInterpolFeignClient {

    @GetMapping(value = "/notices/v1/red/{noticeId}")
    ResponseEntity<DetailsInterpolResponse> getRedNoticeDetails(@PathVariable(value = "noticeId") String noticeId);
}
