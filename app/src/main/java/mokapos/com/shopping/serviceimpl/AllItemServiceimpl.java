package mokapos.com.shopping.serviceimpl;

import android.app.Fragment;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import mokapos.com.shopping.R;
import mokapos.com.shopping.activities.CustomBaseActivity;
import mokapos.com.shopping.fragments.AllItemsFragment;
import mokapos.com.shopping.fragments.CustomBaseFragment;
import mokapos.com.shopping.networking.ApiClient;
import mokapos.com.shopping.networking.response.AllItemResponse;
import mokapos.com.shopping.utilities.CommonUtilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Manuramv on 9/10/2018.
 */

public class AllItemServiceimpl {
    private final AppCompatActivity mActivityObj;
    AllItemsFragment mFragment;

    public AllItemServiceimpl(AppCompatActivity mActivityObj, AllItemsFragment mFragmentObj) {
        this.mActivityObj = mActivityObj;
        this.mFragment = mFragmentObj;

    }

    public void getallItemsFromServerAPI(String getItemsUrl)
    {
        try {
            if (CommonUtilities.isConnectingToInternet(mActivityObj.getApplicationContext())) {
                CommonUtilities.showBusyIndicator(mActivityObj);
                String url = getItemsUrl;
                Uri getItemUrltoLoad = Uri.parse(url);
                Call <List<AllItemResponse>> call = new ApiClient(mActivityObj).getApiService().getStatesAndDistrict();

                call.enqueue(new Callback<List<AllItemResponse>>() {
                    @Override
                    public void onResponse(Call<List<AllItemResponse>> call, Response<List<AllItemResponse>> response) {

                        List<AllItemResponse> rs = response.body();
                        CommonUtilities.removeBusyIndicator(mActivityObj);
                        mFragment.allItemsServerResponse(rs);
                        Log.d("TAG","response in success:;"+rs.size());
                    }

                    @Override
                    public void onFailure(Call<List<AllItemResponse>> call, Throwable t) {
                        CommonUtilities.removeBusyIndicator(mActivityObj);
                        Log.d("TAG","response in error:;"+call.toString());
                        Log.d("TAG","response in error:;"+t.toString());
                    }
                });




            } else {
                Toast toast= Toast.makeText(mActivityObj,mActivityObj.getResources().getString(R.string.internet_warn),Toast.LENGTH_SHORT);
                toast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtilities.removeBusyIndicator(mActivityObj);
        }
    }


    class SponsorsResult {

        @SerializedName("")
        private List<AllItemResponse> sponsors;

        public List<AllItemResponse> getSponsors() {
            return sponsors;
        }
    }
}
