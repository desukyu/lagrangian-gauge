package id.lagrangian.gauge.exception;

public class LastUpdateFindException extends Exception {

    public static final String NOT_FOUND = "Last Update is not found";
    public static final String PARSE_FAILED = "Last Update is failed to parse";

    public LastUpdateFindException(String message) {
        super(message);
    }
}
