package com.hieplp.url.common.payload.request;

import com.google.gson.JsonObject;
import com.hieplp.url.common.payload.HeaderInformation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonRequest {
    private JsonObject request;
    private HeaderInformation headers;
}
