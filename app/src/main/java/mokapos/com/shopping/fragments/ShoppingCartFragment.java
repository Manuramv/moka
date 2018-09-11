package mokapos.com.shopping.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mokapos.com.shopping.R;
import mokapos.com.shopping.adapters.ShopCartAdapter;
import mokapos.com.shopping.dto.AddShopCartItem;
import mokapos.com.shopping.utilities.MokaposContants;
import mokapos.com.shopping.utilities.SimpleDividerItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingCartFragment extends CustomBaseFragment implements View.OnClickListener {


    private View mFragmentView;
    private AppCompatActivity mAppCompatActivityObj;
    TextView txtNoitem,txtSubtotalamt,txtDiscountamount;
    RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    LinearLayout subtotalcontainer,discountcontainer;
    Button btnClearSale,btnCharge;
    ArrayList<AddShopCartItem> shopCartItemListObject;
    private int SUB_TOTAL_VALUE=0;
    double discountAmt =0;
    private ShopCartAdapter shopCartAdapter;

    public ShoppingCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        // Inflate the layout for this fragment
        mFragmentView =  inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        mAppCompatActivityObj = (AppCompatActivity) this.getActivity();
        shopCartItemListObject = new ArrayList<>();
        setCustomToolbar(mFragmentView,"Shopping cart",false);
        ImageView toolbarImage = (ImageView) mFragmentView.findViewById(R.id.homeicon);

        txtNoitem = (TextView) mFragmentView.findViewById(R.id.txt_noitem);
        mRecyclerView = (RecyclerView) mFragmentView.findViewById(R.id.rv_shopcartitems);
        discountcontainer = (LinearLayout) mFragmentView.findViewById(R.id.discountcontainer);
        subtotalcontainer = (LinearLayout) mFragmentView.findViewById(R.id.subtotalcontainer);
        btnClearSale = (Button) mFragmentView.findViewById(R.id.btn_clear);
        btnCharge = (Button) mFragmentView.findViewById(R.id.btn_charge);
        txtSubtotalamt = (TextView) mFragmentView.findViewById(R.id.txt_subtotalamt);
        txtDiscountamount = (TextView) mFragmentView.findViewById(R.id.txt_discountamount);

        btnCharge.setOnClickListener(this);
        btnClearSale.setOnClickListener(this);

        linearLayoutManager = new LinearLayoutManager(mAppCompatActivityObj, OrientationHelper.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(mAppCompatActivityObj));

        //hiding and setting the values as per business logic
        setWidgetsToInitial();

        return mFragmentView;
    }


    public void updateCartItems(ArrayList<AddShopCartItem> shopCartItemList ) {
        //txtNoitem.setText(update);
        shopCartItemListObject = shopCartItemList;
         shopCartAdapter = new ShopCartAdapter(mAppCompatActivityObj,shopCartItemListObject);
        mRecyclerView.setAdapter(shopCartAdapter);
        shopCartAdapter.notifyDataSetChanged();
        calculateDiscount();
        subTotalAmt();
        changeWidgets();
    }

    // Set the values and widgets to initial state.
    public void setWidgetsToInitial(){
        txtNoitem.setVisibility(View.VISIBLE);
        subtotalcontainer.setVisibility(View.GONE);
        discountcontainer.setVisibility(View.GONE);
        btnClearSale.setEnabled(false);
        btnCharge.setText("CHARGE $ 00");
        txtSubtotalamt.setText("0");
    }
//while adding the items to cart.
    public void changeWidgets(){
        txtNoitem.setVisibility(View.GONE);
        subtotalcontainer.setVisibility(View.VISIBLE);
        discountcontainer.setVisibility(View.VISIBLE);
        btnClearSale.setEnabled(true);
        btnCharge.setText("CHARGE $ "+SUB_TOTAL_VALUE);
        txtSubtotalamt.setText(String.valueOf(SUB_TOTAL_VALUE));
    }
    // Calculate the subtotal amount.
    public void subTotalAmt(){
        SUB_TOTAL_VALUE = 0;
        for(int i= 0;i< shopCartItemListObject.size();i++){
            SUB_TOTAL_VALUE = SUB_TOTAL_VALUE + shopCartItemListObject.get(i).getPrice();
            txtSubtotalamt.setText(String.valueOf(SUB_TOTAL_VALUE));
            btnCharge.setText("CHARGE $ "+SUB_TOTAL_VALUE);
        }
    }
    //calculating discount.
    public void calculateDiscount(){
        discountAmt = 0;
        for(int i= 0;i< shopCartItemListObject.size();i++){

            if(shopCartItemListObject.get(i).getDiscountPercent()!=0){
                discountAmt = discountAmt + ((shopCartItemListObject.get(i).getPrice() *shopCartItemListObject.get(i).getDiscountPercent() )/100);
                Log.d("TAG","discount amount::"+discountAmt);
                /*SUB_TOTAL_VALUE = SUB_TOTAL_VALUE + shopCartItemListObject.get(i).getPrice();
                txtSubtotalamt.setText(String.valueOf(SUB_TOTAL_VALUE));
                btnCharge.setText("CHARGE $ "+SUB_TOTAL_VALUE);*/
            }

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clear:
                shopCartItemListObject.clear();
                shopCartAdapter.notifyDataSetChanged();
                setWidgetsToInitial();
                break;
            case R.id.btn_charge:
                Toast toast=Toast.makeText(mAppCompatActivityObj,"You are good to pay your bill.",Toast.LENGTH_SHORT);
                toast.show();
            default:
                break;
        }
    }
}
