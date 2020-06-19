package rest.helpers;

public enum ExpectedErrors {
    BAD_REQUEST(400, "Bad Request", null),
    NOT_FOUND(404,"Not Found",null),
    UNSUPPORTED_MEDIA_TYPE(415,"Unsupported Media Type",null);

    private final Integer status;

    private final String error;

    private final String message;

    ExpectedErrors(Integer status, String error, String message) {

        this.status = status;
        this.error = error;
        this.message = message;
    }

    public Integer getStatus() {

        return status;
    }

    public String getError() {

        return error;
    }

    public String getMessage() {

        return message;
    }
    }
