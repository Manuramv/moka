package mokapos.com.shopping.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mokapos.com.shopping.R;
import mokapos.com.shopping.adapters.AllDiscountsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllDiscountsFragment extends CustomBaseFragment {
    private View mFragmentView;
    RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    public AppCompatActivity mAppCompatActivityObj;


    public AllDiscountsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentView =  inflater.inflate(R.layout.fragment_all_discounts, container, false);
        mAppCompatActivityObj = (AppCompatActivity) this.getActivity();

        setCustomToolbar(mFragmentView,"All Discounts",true);
        ImageView toolbarImage = (ImageView) mFragmentView.findViewById(R.id.homeicon);
        mRecyclerView  = (RecyclerView) mFragmentView.findViewById(R.id.rv_alldiscount);
        linearLayoutManager = new LinearLayoutManager(mAppCompatActivityObj, OrientationHelper.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        AllDiscountsAdapter allDiscountsAdapterObj = new AllDiscountsAdapter(mAppCompatActivityObj);
        mRecyclerView.setAdapter(allDiscountsAdapterObj);

        toolbarImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("YAG","BUTTON CLICKED");
              //  mAppCompatActivityObj.finish();
                mAppCompatActivityObj.getSupportFragmentManager().popBackStack();
            }
        });

        return mFragmentView;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_back, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_filter:
                //call function as per your requirement
                mAppCompatActivityObj.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
