package com.example.stepbackend.global.common.response.error;

import com.example.stepbackend.global.common.response.api.ApiResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonPropertyOrder({"status", "message", "time", "result.*"})
public class ErrorResponse {

    @JsonUnwrapped
    private ApiResponse apiResponse;
    private ErrorResponseBody result;

    public ErrorResponse() {}

    public ErrorResponse(ApiResponse apiResponse, ErrorResponseBody errorResponseBody) {
        this.apiResponse = apiResponse;
        this.result = errorResponseBody;
    }

    public ErrorResponse setApiResponse(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
        return this;
    }

    public ErrorResponse setResult(ErrorResponseBody result) {
        this.result = result;
        return this;
    }

}
