package com.hieplp.url.common.payload.response;

import com.hieplp.url.common.constants.statusCode.ResponseCode;
import lombok.Data;

@Data
public class CommonResponse {
    protected String code;
    protected Object data;
    protected String message;

    public CommonResponse(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = new Object();
    }

    public CommonResponse(ResponseCode response, Object data) {
        this.code = response.getCode();
        this.message = response.getMessage();
        this.data = data;
    }
}
