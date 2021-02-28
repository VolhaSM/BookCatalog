package bc.exceptions;

import org.springframework.http.HttpStatus;

public enum HttpAppError implements AppError {

    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    EMAIL_CONFIRMATION_BAD_TOKEN(HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_CONFIRMED(HttpStatus.BAD_REQUEST),
    EMAIL_CONFIRMATION_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST),
    TOO_OFTEN(HttpStatus.BAD_REQUEST),
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST),
    ACCESS_DENIED(HttpStatus.FORBIDDEN),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND),
    EXTERNAL_API_ERROR(HttpStatus.SERVICE_UNAVAILABLE),
    USER_DISABLED(HttpStatus.FORBIDDEN),
    INVALID_EMAIL_OR_PASSWORD(HttpStatus.BAD_REQUEST),
    NOT_IMPLEMENTED_YET(HttpStatus.NOT_IMPLEMENTED);

    private HttpStatus status;

    HttpAppError(HttpStatus status) {
        this.status = status;
    }

    @Override
    public Integer getStatus() {
        return status.value();
    }

    @Override
    public String toString() {
        return this.name();
    }

    @Override
    public String getErrorCode() {
        return this.name();
    }

}
