package br.com.m2yconductorservices.data.remote;

import android.os.Handler;
import android.os.Looper;
import br.com.m2yconductorservices.data.M2YCDTEnvironment;
import br.com.m2yconductorservices.data.local.M2YCDTPersistUserInformation;
import br.com.m2yconductorservices.data.local.M2YCDTPreferencesHelper;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import br.com.m2yconductorservices.M2YCDTNetworkConstants;
import br.com.m2yconductorservices.data.remote.models.response.ErrorResponse;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by mobile2you on 29/11/16.
 */

public class M2YCDTErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private static final String ERROR_UNKNOWN_HOST = "Rede Indisponível";
    private static final String ERROR_TIMEOUT_ERROR = "Tempo Excedido.";
    private static final String ERROR_PARSE_PRODUCTION = "Pode ser necessário atualizar seu aplicativo.";
    private static final String ERROR_PARSE_DEBUG = "JsonParseException";

    private final RxJava2CallAdapterFactory original;

    private M2YCDTErrorHandlingCallAdapterFactory() {
        original = RxJava2CallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new M2YCDTErrorHandlingCallAdapterFactory();

    }

    @Override
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper implements CallAdapter<Single<?>> {
        private final Retrofit retrofit;
        private final CallAdapter<?> wrapped;

        public RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<?> wrapped) {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public <R> Single<?> adapt(Call<R> call) {
            return ((Single) wrapped.adapt(call)).onErrorResumeNext(new Function<Throwable, SingleSource>() {
                @Override
                public SingleSource apply(Throwable throwable) throws Exception {
                    return Single.error(asRetrofitException(throwable));
                }
            });
        }

        private M2YCDTRetrofitException asRetrofitException(Throwable throwable) {
            if (throwable instanceof HttpException) {
                // We had non-2XX http error
                switch (((HttpException) throwable).code()) {
                    case M2YCDTNetworkConstants.CODE_UNKNOWN:
                        M2YCDTErrorBus.INSTANCE.setEvent(new Throwable(((HttpException) throwable).response().raw().request().url().toString() + " - " + ((HttpException) throwable).message()));
                        break;
                }
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();

                try {
                    String json = response.errorBody().string();
                    ErrorResponse message = new Gson().fromJson(json, ErrorResponse.class);
                    return M2YCDTRetrofitException.httpError(message.getMessage(), response);
                } catch (Exception ex) {
                    return M2YCDTRetrofitException.httpError(response.raw().request().url().toString(), response);
                }

            } else if (throwable instanceof IOException) {
                M2YCDTRetrofitException returnableThrowable = M2YCDTRetrofitException.unexpectedError(throwable);
                // A network or conversion error happened
                if (throwable instanceof UnknownHostException) {
                    // Network error
                    throwable = new UnknownHostException(ERROR_UNKNOWN_HOST);
                    returnableThrowable = M2YCDTRetrofitException.networkError((IOException) throwable);
                } else if (throwable instanceof SocketTimeoutException) {
                    // Timeout error
                    throwable = new SocketTimeoutException(ERROR_TIMEOUT_ERROR);
                    returnableThrowable = M2YCDTRetrofitException.networkError((IOException) throwable);
                } else if (throwable instanceof ProtocolException) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            M2YCDTPersistUserInformation.INSTANCE.clear();
                            M2YCDTPreferencesHelper.INSTANCE.clearSharedPref();
                            M2YCDTUserUnauthorizedBus.INSTANCE.setEvent(ERROR_PARSE_PRODUCTION);
                        }
                    });
                    returnableThrowable = M2YCDTRetrofitException.protocolError(throwable);
                } else if (throwable instanceof ConnectException) {
                    // Network error
                    returnableThrowable = M2YCDTRetrofitException.networkError(new IOException(ERROR_UNKNOWN_HOST));
                }
                return returnableThrowable;
            } else if (throwable instanceof JsonParseException) {
                //Parse error
                M2YCDTErrorBus.INSTANCE.setEvent(throwable);
                throwable = new IOException(M2YCDTEnvironment.INSTANCE.getDebug() ?
                        ERROR_PARSE_DEBUG + " " + throwable.getMessage() :
                        ERROR_PARSE_PRODUCTION);
                return M2YCDTRetrofitException.unexpectedError(throwable);
            }


            // We don't know what happened. We need to simply convert to an unknown error
            return M2YCDTRetrofitException.unexpectedError(throwable);
        }

    }
}
