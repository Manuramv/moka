package mokapos.com.shopping.adapters;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mokapos.com.shopping.R;
import mokapos.com.shopping.fragments.AllDiscountsFragment;
import mokapos.com.shopping.fragments.AllItemsFragment;
import mokapos.com.shopping.networking.response.AllItemResponse;

/**
 * Created by Manuramv on 9/10/2018.
 */

public class AllDiscountsAdapter extends RecyclerView.Adapter<AllDiscountsAdapter.ViewHolder> {


    AppCompatActivity mAppCompatActivityObj;
    String mentText[] = {"Discount A","Discount B","Discount C","Discount D","Discount E"};
    String discountText[] = {"0%","10%","35.5%","50%","100%"};


    public AllDiscountsAdapter(AppCompatActivity mAppCompatActivity) {
        this.mAppCompatActivityObj = mAppCompatActivity;
        ;
    }

    @NonNull
    @Override
    public AllDiscountsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_shop_item, parent, false);
        return new AllDiscountsAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AllDiscountsAdapter.ViewHolder holder, final int position) {
            holder.leftimg.setImageResource(R.drawable.ic_percentage);
            holder.menutxt.setText(mentText[position]);
            holder.priceText.setText(discountText[position]);

    }

    @Override
    public int getItemCount() {
        return mentText.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView menutxt, priceText;
        ImageView leftimg;


        public ViewHolder(View view) {
            super(view);
            menutxt = (TextView) view.findViewById(R.id.menutxt);
            leftimg = (ImageView) view.findViewById(R.id.leftimg);
            priceText = (TextView) view.findViewById(R.id.price_text);


        }

    }


}
