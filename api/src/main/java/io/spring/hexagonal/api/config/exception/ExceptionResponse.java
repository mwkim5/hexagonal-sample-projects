package io.spring.hexagonal.api.config.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
public class ExceptionResponse {

    private final LocalDateTime timestamp;
    private final String message;
    private final String code;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<InvalidParameter> invalidParameters;


    public ExceptionResponse(String message, String code, List<InvalidParameter> invalidParameters) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.code = code;
        this.invalidParameters = invalidParameters;
    }

    public ExceptionResponse(String message, String code) {
        this(message, code, null);
    }

	public record InvalidParameter(
        String field,
        Object value,
        String message
    ) {
    }
}
