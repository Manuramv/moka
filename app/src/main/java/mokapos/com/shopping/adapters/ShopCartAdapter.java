package mokapos.com.shopping.adapters;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import mokapos.com.shopping.R;
import mokapos.com.shopping.activities.MokaposApplication;
import mokapos.com.shopping.dto.AddShopCartItem;
import mokapos.com.shopping.utilities.MokaposContants;

/**
 * Created by Manuramv on 9/10/2018.
 */

public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.ViewHolder> {


    AppCompatActivity mAppCompatActivityObj;
    ArrayList<AddShopCartItem> addShopCartItemList;
    ArrayList<AddShopCartItem> tempShopCartListObj;
    int subTotalPrice =0;
    private boolean rendertheObject = true;

    public ShopCartAdapter(AppCompatActivity mAppCompatActivity, ArrayList<AddShopCartItem> addShopCartItemObj) {
        this.mAppCompatActivityObj = mAppCompatActivity;
        this.addShopCartItemList = addShopCartItemObj;
        tempShopCartListObj = new ArrayList<>();

    }

    @NonNull
    @Override
    public ShopCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_cart_item, parent, false);
        return new ShopCartAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ShopCartAdapter.ViewHolder holder, final int position) {



/*
            for(int i=0; i < addShopCartItemList.size(); i++ ){
                for(int j=0; j< tempShopCartListObj.size();j++){
                    if(addShopCartItemList.get(position).getItemNo().equalsIgnoreCase(tempShopCartListObj.get(j).getItemNo())){
                        Log.d("TAG","duplicate item::no=="+addShopCartItemList.get(position).getItemNo()+"..position=="+position+"..price="+addShopCartItemList.get(position).getPrice());
                    } else {
                        Log.d("Tag","no duplicate items found");
                    }
                }

            }*/

        Iterator<AddShopCartItem> it1 = tempShopCartListObj.iterator();
        Iterator<AddShopCartItem> it2 = addShopCartItemList.iterator();

           /* if(tempShopCartListObj.size()>0) {
                //for (int i = 0; i < addShopCartItemList.size(); i++) {
                    for(int j=0;j<tempShopCartListObj.size();j++) {
                        if (addShopCartItemList.get(position).getItemNo().equalsIgnoreCase(tempShopCartListObj.get(j).getItemNo())) {
                            rendertheObject = false;
                            Log.d("TAG", "duplicate item::no==" + addShopCartItemList.get(position).getItemNo() + "..position==" + position + "..price=" + addShopCartItemList.get(position).getPrice());
                            break;
                        } else {
                            rendertheObject = true;
                            Log.d("Tag", "no duplicate items found::" + addShopCartItemList.get(position).getItemNo());
                        }
                    }
               // }
            }*/
        tempShopCartListObj.add(addShopCartItemList.get(position));

            if(rendertheObject){
                holder.itemId.setText("Item "+addShopCartItemList.get(position).getItemNo());
                holder.quantity.setText(addShopCartItemList.get(position).getQuantities());
                holder.amount.setText(String.valueOf(addShopCartItemList.get(position).getPrice()));
            } else {
                /*holder.itemId.setText("Item "+addShopCartItemList.get(position).getItemNo());
                holder.quantity.setText(addShopCartItemList.get(position).getQuantities());
                holder.amount.setText(String.valueOf(addShopCartItemList.get(position).getPrice()));*/
            }





    }

    @Override
    public int getItemCount() {
        return addShopCartItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemId,quantity,amount;

        public ViewHolder(View view) {
            super(view);
            itemId = (TextView) view.findViewById(R.id.txt_itemid);
            quantity = (TextView) view.findViewById(R.id.txt_quantity);
            amount = (TextView) view.findViewById(R.id.txt_amount);


        }

    }


}
