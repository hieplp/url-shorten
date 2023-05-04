package com.hieplp.url.common.payload.request.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
