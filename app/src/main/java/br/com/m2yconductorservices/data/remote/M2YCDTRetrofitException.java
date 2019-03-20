package br.com.m2yconductorservices.data.remote;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by mobile2you on 29/11/16.
 */

public class M2YCDTRetrofitException extends RuntimeException {
    static M2YCDTRetrofitException httpError(String url, Response response) {
        String message;
//        if (url == null)
//            message = response.code() + " " + response.message();
//        else
            message = url;
        return new M2YCDTRetrofitException(message, url, response, null);
    }

    static M2YCDTRetrofitException networkError(IOException exception) {
        return new M2YCDTRetrofitException(exception.getMessage(), null, null, exception);
    }

    static M2YCDTRetrofitException unexpectedError(Throwable exception) {
        return new M2YCDTRetrofitException(exception.getMessage(), null, null, exception);
    }

    /**
     * Identifies the event kind which triggered a {@link M2YCDTRetrofitException}.
     */
    public enum Kind {
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    private final String url;
    private final Response response;

    private M2YCDTRetrofitException(String message, String url, Response response, Throwable exception) {
        super(message, exception);
        this.url = url;
        this.response = response;
    }

    /**
     * The request URL which produced the error.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Response object containing status code, headers, body, etc.
     */
    public Response getResponse() {
        return response;
    }

}

