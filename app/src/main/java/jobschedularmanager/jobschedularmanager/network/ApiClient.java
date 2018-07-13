package jobschedularmanager.jobschedularmanager.network;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static jobschedularmanager.jobschedularmanager.network.NetworkConstant.NETWORK_TIMEOUT;


/**
 * Created by user on 25/4/18.
 */

public class ApiClient {

    public static final String BASE_URL = "http://169.38.100.202:801";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
