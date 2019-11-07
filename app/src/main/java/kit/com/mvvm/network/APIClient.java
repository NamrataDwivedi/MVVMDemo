package kit.com.mvvm.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import kit.com.mvvm.MyApplication;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static APIClient apiClient;
    private Retrofit retrofit = null;
    private APIInterface apiInterface;
    private final String BASE_URL="https://randomuser.me/";


    public static APIClient getInstance() {
        if (apiClient == null) {
            synchronized (APIClient.class) {
                if (apiClient == null) {
                    apiClient = new APIClient();
                }
            }
        }

        return apiClient;
    }

    private Retrofit getClient(Context context) {
        if (retrofit == null) {

            OkHttpClient client;
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
            builder.addInterceptor(new AddCookiesInterceptor(context));
            builder.addInterceptor(new ReceivedCookiesInterceptor(context));
            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);
            client = builder.build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)//
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


    public APIInterface getApiInterface() {
        if (apiInterface == null)
            apiInterface = APIClient.getInstance().getClient(MyApplication.getContext()).create(APIInterface.class);

        return apiInterface;
    }
}
