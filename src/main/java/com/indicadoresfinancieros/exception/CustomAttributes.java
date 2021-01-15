package com.indicadoresfinancieros.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Component
public class CustomAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributesMap = super.getErrorAttributes(request, options);
        Throwable throwable = getError(request);
        if (throwable instanceof ResponseStatusException) {
            ResponseStatusException ex = (ResponseStatusException) throwable;
            errorAttributesMap.put("code", ex.getStatus().value());
            errorAttributesMap.put("message", "Invalid Request");
            errorAttributesMap.remove("timestamp");
            errorAttributesMap.remove("path");
            errorAttributesMap.remove("error");
            errorAttributesMap.remove("requestId");
            errorAttributesMap.remove("status");
        }
        return errorAttributesMap;
    }
}
