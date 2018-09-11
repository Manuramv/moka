package mokapos.com.shopping.networking;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import mokapos.com.shopping.networking.response.AllItemResponse;
import mokapos.com.shopping.utilities.MokaposContants;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Manuramv on 9/10/2018.
 */

public class ApiClient {
    private Retrofit retrofitObj;
    Context context;
    Gson gson = new GsonBuilder().setLenient().create();
    private ApiService apiService;


    public ApiClient(Context context) {
        this.context = context;

        try {
            this.context = context;
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .build();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    // Request customization: add request headers

                    Request.Builder requestBuilder = null;
                    requestBuilder = original.newBuilder();
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient client = httpClient.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]).hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            }).build();
            retrofitObj = new Retrofit.Builder()
                    .baseUrl(MokaposContants.END_POINT)
                    .client(client)

                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            apiService = retrofitObj.create(ApiService.class);
        } catch (Exception e) {
            Log.d("TAG", "Exception in Apiclient call::::" + e);
        }
    }
    public interface ApiService {

        /*@GET
        Call<AllItemResponse> getAllItemDetailsApi(@Url Uri url);*/

        @GET("photos/")
        Call<List<AllItemResponse>> getStatesAndDistrict(

        );


    }

    public ApiService getApiService() {
        return apiService;
    }
}
