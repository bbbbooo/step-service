package com.example.stepbackend.global.security.dto;

import com.example.stepbackend.global.common.response.api.ApiResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonPropertyOrder({"status", "message", "timestamp", "body.*"})
public class AuthResponse {

    @JsonUnwrapped
    private ApiResponse apiResponse;
    private AuthResponseBody body;

    public AuthResponse() {}

    public AuthResponse setApiResponse(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
        return this;
    }

    public AuthResponse setBody(AuthResponseBody body) {
        this.body = body;
        return this;
    }

}
