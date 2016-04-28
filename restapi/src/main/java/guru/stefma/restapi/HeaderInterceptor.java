package guru.stefma.restapi;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String basicAuth =
                "Basic " + Base64.encodeToString(BuildConfig.BASIC_AUTH_CREDENTIALS.getBytes(),
                        Base64.NO_WRAP);

        Request original = chain.request();

        Request request = original.newBuilder()
                .header("Authorization", basicAuth)
                .header("TT-Api-Version", BuildConfig.TT_API_VERSION)
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);
    }

}
