package kit.com.mvvm.network;

import android.content.Context;
import android.preference.PreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class AddCookiesInterceptor implements Interceptor {
    static final String PREF_COOKIES = "PREF_COOKIES";
    private Context context;

    AddCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String preferences = PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_COOKIES, null);
        if (preferences != null) {
            builder.addHeader("Cookie", preferences);

        }

        return chain.proceed(builder.build());
    }
}