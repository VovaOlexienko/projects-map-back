package oleksiienko.volodymyr.exception;

public class HttpRequestException extends RuntimeException{

    public HttpRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
