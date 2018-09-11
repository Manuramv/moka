package mokapos.com.shopping.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mokapos.com.shopping.R;
import mokapos.com.shopping.activities.MokaposApplication;
import mokapos.com.shopping.fragments.AllDiscountsFragment;
import mokapos.com.shopping.fragments.AllItemsFragment;
import mokapos.com.shopping.interfaces.ItemClickInterface;
import mokapos.com.shopping.networking.response.AllItemResponse;
import mokapos.com.shopping.utilities.CommonUtilities;

/**
 * Created by Manuramv on 9/10/2018.
 */

public class AllitemsAdapter extends RecyclerView.Adapter<AllitemsAdapter.ViewHolder> {


    AppCompatActivity mAppCompatActivityObj;
    Integer leftIcon[] = {R.drawable.ic_percentage,R.drawable.ic_items};
    Integer rightIcon[] = {R.drawable.ic_next,R.drawable.ic_next};
    String mentText[] = {"All Discounts","All Itmes"};
    private AllDiscountsFragment discountFragment;
    private AllItemsFragment itemsFragment;
    List<AllItemResponse> dataList;
    ItemClickInterface itemClickInterface;


    public AllitemsAdapter(AppCompatActivity mAppCompatActivity, List<AllItemResponse> body, ItemClickInterface itemClickInterfaceObj) {
        this.mAppCompatActivityObj = mAppCompatActivity;
        dataList = body;
        itemClickInterface = itemClickInterfaceObj;
    }

    @NonNull
    @Override
    public AllitemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_shop_item, parent, false);
        return new AllitemsAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final AllitemsAdapter.ViewHolder holder, final int position) {

        holder.menutxt.setText(dataList.get(position).getTitle());

        final String count = String.valueOf(CommonUtilities.getRandomNumber(position));
        holder.priceText.setText(count);
        holder.priceText.setTag(count);
        Uri uri = Uri.parse(dataList.get(position).getThumbnailUrl());
        MokaposApplication.getInstance().getmPicassoObj().load(uri).fit().centerCrop().placeholder(R.color.grey).error(R.drawable.ic_items).into(holder.leftimg);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDetailPage(responseObj, position);
                /*Toast toast=Toast.makeText(mAppCompatActivityObj,"Selected items"+position,Toast.LENGTH_SHORT);
                toast.show();*/


                proceedWithItemClickBusinessLogic(position,dataList.get(position),holder.priceText.getTag().toString());
            }
        });

    }

    private void proceedWithItemClickBusinessLogic(int position, AllItemResponse selectedItemObj,String price) {
            //showCustomPopupMessage(mAppCompatActivityObj, selectedItemObj,price);
            itemClickInterface.showPopup(position, selectedItemObj,price);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView menutxt,priceText;
        ImageView leftimg;


        public ViewHolder(View view) {
            super(view);
            menutxt = (TextView) view.findViewById(R.id.menutxt);
            leftimg = (ImageView) view.findViewById(R.id.leftimg);
            priceText = (TextView) view.findViewById(R.id.price_text);


        }

    }




}
