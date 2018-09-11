package mokapos.com.shopping.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mokapos.com.shopping.R;
import mokapos.com.shopping.adapters.LibraryMenuAdapters;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends CustomBaseFragment {
    private View mFragmentView;
    RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    public AppCompatActivity mAppCompatActivityObj;

    public LibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_library, container, false);
        mAppCompatActivityObj = (AppCompatActivity) this.getActivity();

        setCustomToolbar(mFragmentView,"Library",false);

        LibraryMenuAdapters libraryMenuAdapters = new LibraryMenuAdapters(mAppCompatActivityObj);


        mRecyclerView = (RecyclerView)mFragmentView.findViewById(R.id.rv_itemlist);
        linearLayoutManager = new LinearLayoutManager(mAppCompatActivityObj, OrientationHelper.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(libraryMenuAdapters);



        return mFragmentView;
    }



}
