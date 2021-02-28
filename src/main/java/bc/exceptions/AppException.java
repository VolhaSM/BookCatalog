package bc.exceptions;


import lombok.ToString;

@ToString
public class AppException extends RuntimeException {

    private AppError error;

    public AppException(AppError error) {
        this.error = error;
    }

    public AppException(AppError error, String message) {
        super(message);
        this.error = error;
    }

    public AppError getError() {
        return error;
    }

}
