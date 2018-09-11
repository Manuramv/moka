package mokapos.com.shopping.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import mokapos.com.shopping.R;
import mokapos.com.shopping.fragments.AllDiscountsFragment;
import mokapos.com.shopping.fragments.AllItemsFragment;

/**
 * Created by Manuramv on 9/10/2018.
 */

public class LibraryMenuAdapters extends RecyclerView.Adapter<LibraryMenuAdapters.ViewHolder> {


    AppCompatActivity mAppCompatActivityObj;
    Integer leftIcon[] = {R.drawable.ic_percentage,R.drawable.ic_items};
    Integer rightIcon[] = {R.drawable.ic_next,R.drawable.ic_next};
    String mentText[] = {"All Discounts","All Itmes"};
    private AllDiscountsFragment discountFragment;
    private AllItemsFragment itemsFragment;


    public LibraryMenuAdapters(AppCompatActivity mAppCompatActivity) {
        this.mAppCompatActivityObj = mAppCompatActivity;
        discountFragment = new AllDiscountsFragment();
        itemsFragment = new AllItemsFragment();

    }

    @NonNull
    @Override
    public LibraryMenuAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.library_menu_item, parent, false);
        return new LibraryMenuAdapters.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull LibraryMenuAdapters.ViewHolder holder, final int position) {
      /*if (dataList.get(position).getEmail().toString() != null && !TextUtils.isEmpty(dataList.get(position).getEmail().toString())) {
            holder.tvEmail.setText(dataList.get(position).getEmail());
        }*/

        holder.leftimg.setImageResource(leftIcon[position]);
        holder.rightimg.setImageResource(rightIcon[position]);
        holder.menutxt.setText(mentText[position]);


         holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDetailPage(responseObj, position);
                /*Toast toast=Toast.makeText(mAppCompatActivityObj,"Selected items"+position,Toast.LENGTH_SHORT);
                toast.show();*/
                showCorrespondingfragment(position);
            }
        });

    }

    private void showCorrespondingfragment(int position) {
        if(position ==0){
            navigateToFragment(discountFragment,true);
        } else if(position ==1){
            navigateToFragment(itemsFragment,true);
        }
    }


    @Override
    public int getItemCount() {
        return leftIcon.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView menutxt;
        ImageView leftimg,rightimg;


        public ViewHolder(View view) {
            super(view);
            menutxt = (TextView) view.findViewById(R.id.menutxt);
            leftimg = (ImageView) view.findViewById(R.id.leftimg);
            rightimg = (ImageView) view.findViewById(R.id.rightimg);


        }

    }

    public void navigateToFragment(Fragment frag, boolean addtostack) {

        FragmentTransaction ft = mAppCompatActivityObj.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_top, frag); // f1_container is your FrameLayout container
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (addtostack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

}