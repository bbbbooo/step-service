package com.example.stepbackend.global.common.response.error;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonPropertyOrder({ "code", "classes" })
public class ErrorResponseBody {
    private String classes;
    private String code;

    public ErrorResponseBody() {}

    public ErrorResponseBody(String classes, String code) {
        this.classes = classes;
        this.code = code;
    }

    public ErrorResponseBody setClasses(String classes) {
        this.classes = classes;
        return this;
    }

    public ErrorResponseBody setCode(String code) {
        this.code = code;
        return this;
    }
}