package mokapos.com.shopping.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.Random;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import mokapos.com.shopping.R;
import mokapos.com.shopping.dto.AddShopCartItem;
import mokapos.com.shopping.networking.response.AllItemResponse;

/**
 * Created by Manuramv on 9/9/2018.
 */

public class CommonUtilities {

    private static ProgressDialog plainProgressIndicator;
    private static android.support.v7.app.AlertDialog alertDialog;

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = connectivity.getActiveNetworkInfo();

        boolean isConnected = info != null && info.isConnectedOrConnecting();
        return isConnected;

    }

    public static void showBusyIndicator(final AppCompatActivity activityObj) {
        try {
            if (activityObj != null) {
                activityObj.runOnUiThread(new Runnable() {
                    public void run() {
                        if (plainProgressIndicator == null)
                            plainProgressIndicator = new ProgressDialog(activityObj);

                        if (!(plainProgressIndicator.isShowing())) {
                            plainProgressIndicator = ProgressDialog.show(activityObj, "", "Loading", true, false);
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeBusyIndicator(final AppCompatActivity activityObj) {
        try {
            activityObj.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        if (plainProgressIndicator != null && plainProgressIndicator.isShowing()) {
                            plainProgressIndicator.dismiss();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static int getRandomNumber(int id) {
        if(id==0)
            id=1;
        return (new Random()).nextInt((99 - 10) + 1) *id;
    }



}
