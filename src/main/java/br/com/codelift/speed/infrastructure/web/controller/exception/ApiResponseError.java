package br.com.codelift.speed.infrastructure.web.controller.exception;

import java.util.List;

public record ApiResponseError(int statusCode, String message, List<ErrorField> errorFields) {
}
