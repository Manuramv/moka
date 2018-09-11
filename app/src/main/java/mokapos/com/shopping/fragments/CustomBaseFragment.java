package mokapos.com.shopping.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mokapos.com.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class CustomBaseFragment extends android.support.v4.app.Fragment {


    public CustomBaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_base, container, false);
    }

    public  void setCustomToolbar(View mFragmentView,String toolbarTxt,boolean leftIconVisible){
        Toolbar toolbar = (Toolbar) mFragmentView.findViewById(R.id.tool_bar);
        TextView toolbarText = (TextView) mFragmentView.findViewById(R.id.toolbar_text);
        ImageView toolbarImage = (ImageView) mFragmentView.findViewById(R.id.homeicon);
        toolbarText.setText(toolbarTxt);
        if(leftIconVisible){
           toolbarImage.setVisibility(View.VISIBLE);
            setHasOptionsMenu(true);
        } else {
            toolbarImage.setVisibility(View.GONE);
        }

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

    }

   // public abstract boolean onCreateOptionsMenu(Menu menu);

   /* public abstract boolean onCreateOptionsMenu(Menu menu);*/
}
