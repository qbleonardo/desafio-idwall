package com.desafioIdwall.external.fbiclient;

import com.desafioIdwall.external.fbiclient.response.ItemFbiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "FbiApi", url = "https://api.fbi.gov/")
public interface FbiFeignClient {

    @GetMapping(value = "/wanted", consumes = "application/json", produces = "application/json")
    ResponseEntity<ItemFbiResponse> wanted(@RequestParam(value = "title", required = false) String title,
                                           @RequestParam(value = "posterClassification", required = false) String posterClassification);
}
