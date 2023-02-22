package io.muenchendigital.digiwf.message.process.impl.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TechnicalError extends RuntimeException {

    private final String processInstanceId;
    private final String errorCode;
    private final String errorMessage;

}
