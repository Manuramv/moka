package mokapos.com.shopping.activities;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
/**
 * Created by Manuramv on 9/10/2018.
 */

public class MokaposApplication extends Application {

    private Picasso picasso;
    private static MokaposApplication mInstance;
    public static MokaposApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        setmPicassoObj(initPicassoObj());
    }



    public Picasso getmPicassoObj() {
        return picasso;
    }

    public void setmPicassoObj(Picasso mPicassoObj) {
        this.picasso = mPicassoObj;
    }


    public Picasso initPicassoObj() {
        //This is only for uat, not required for production - Murali
        com.squareup.okhttp.OkHttpClient client = new com.squareup.okhttp.OkHttpClient();
        // client.setProtocols(Arrays.asList(Protocol.HTTP_1_1));


        //   OkHttpClient client = new OkHttpClient();
        client.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] x509Certificates,
                    String s) throws java.security.cert.CertificateException {
                boolean silence;
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] x509Certificates,
                    String s) throws java.security.cert.CertificateException {
                boolean silence;
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        }};
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            client.setSslSocketFactory(sc.getSocketFactory());
            Log.d("tag", "https image checking ssl file");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Picasso picassoobj = null;
        try {
            picassoobj = new Picasso.Builder(this)
                    .downloader(new OkHttpDownloader(client))
                    .build();


            //  Picasso mPicassoObj = new Picasso.Builder(context).downloader(new OkHttpDownloader(client)).build();

            picassoobj.setIndicatorsEnabled(false);
            picassoobj.setLoggingEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return picassoobj;
    }
}
