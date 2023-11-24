package com.desafioIdwall.exceptions.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorData {

    private String code;
    private String title;
    private String detail;

    public ErrorData(String code, String title, String detail) {
        this.code = code;
        this.title = title;
        this.detail = detail;
    }

}
