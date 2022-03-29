package com.devops.dxc.devops.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "UF no disponible")
public class UFNoDisponibleException extends Exception {

    public UFNoDisponibleException(String message) {
        super(message);
    }
}
