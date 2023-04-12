package com.hieplp.url.common.payload.response;

import com.hieplp.url.common.constants.statusCode.ResponseCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse {
    protected String code;
    protected Object data;
    protected String messages;

    public static class CommonResponseBuilder {
        public CommonResponseBuilder response(ResponseCode response) {
            this.code = response.getCode();
            this.messages = response.getMessage();
            return this;
        }
    }
}
