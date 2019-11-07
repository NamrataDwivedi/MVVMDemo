package kit.com.mvvm.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    private Context context;

    public ReceivedCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            String cookies = PreferenceManager.getDefaultSharedPreferences(context).getString(AddCookiesInterceptor.PREF_COOKIES, null);

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies = header;
            }

            SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(context).edit();
            memes.putString(AddCookiesInterceptor.PREF_COOKIES, cookies).apply();
            memes.commit();
        }

        return originalResponse;
    }
}