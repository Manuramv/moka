package mokapos.com.shopping.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mokapos.com.shopping.R;
import mokapos.com.shopping.adapters.AllitemsAdapter;
import mokapos.com.shopping.dto.AddShopCartItem;
import mokapos.com.shopping.interfaces.ItemClickInterface;
import mokapos.com.shopping.interfaces.ShopCartInterfaceCallback;
import mokapos.com.shopping.networking.response.AllItemResponse;
import mokapos.com.shopping.serviceimpl.AllItemServiceimpl;
import mokapos.com.shopping.utilities.EditTextMinMax;
import mokapos.com.shopping.utilities.MokaposContants;
import mokapos.com.shopping.utilities.SimpleDividerItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllItemsFragment extends CustomBaseFragment implements ItemClickInterface,View.OnClickListener{


    private View mFragmentView;
    private AppCompatActivity mAppCompatActivityObj;
    AllItemsFragment currentFrag;
    RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AllitemsAdapter allitemsAdapterObj;
    ShopCartInterfaceCallback shopCartInterfaceCallbackObj;
    private android.support.v7.app.AlertDialog alertDialog;
    private ItemClickInterface itemClickInterface;
    ArrayList<AddShopCartItem> shopCartItemsList;
    ArrayList<AddShopCartItem> tempShopCartListObj;
    private boolean rendertheObject = true;
    SwitchCompat switchA,switchB,switchC,switchD,switchE;
    private double selectedDiscountPercentage=0;

    public AllItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentView = inflater.inflate(R.layout.fragment_all_items, container, false);
        mAppCompatActivityObj = (AppCompatActivity) this.getActivity();
        setCustomToolbar(mFragmentView,"All Items",true);
        currentFrag = this;
        shopCartItemsList = new ArrayList<>();
        shopCartInterfaceCallbackObj = (ShopCartInterfaceCallback)mAppCompatActivityObj;
        itemClickInterface = (ItemClickInterface)this;
        ImageView toolbarImage = (ImageView) mFragmentView.findViewById(R.id.homeicon);
        mRecyclerView = (RecyclerView) mFragmentView.findViewById(R.id.rv_allitmes);
        linearLayoutManager = new LinearLayoutManager(mAppCompatActivityObj, OrientationHelper.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(mAppCompatActivityObj));

        tempShopCartListObj = new ArrayList<>();



        toolbarImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("YAG","BUTTON CLICKED");
                //  mAppCompatActivityObj.finish();
                mAppCompatActivityObj.getSupportFragmentManager().popBackStack();
            }
        });

        getAllItemsDetailsFromserver();

        return mFragmentView;
    }

    private void getAllItemsDetailsFromserver() {
        AllItemServiceimpl allItemServiceimplObj = new AllItemServiceimpl(mAppCompatActivityObj,currentFrag);
        allItemServiceimplObj.getallItemsFromServerAPI(MokaposContants.END_POINT);
    }

    public void allItemsServerResponse(List<AllItemResponse> body){
      Log.d("tag","Response items::"+body.size());
        AllitemsAdapter allitemsAdapterObj = new AllitemsAdapter(mAppCompatActivityObj,body,itemClickInterface);
        mRecyclerView.setAdapter(allitemsAdapterObj);


    }
/////////////////////////////
    // Add item logic follows
    ////////


//interface callback method..
    @Override
    public void showPopup(int position, AllItemResponse selectedItemObj, String price) {
        showCustomPopupMessage(mAppCompatActivityObj, selectedItemObj ,  price);
    }


    public  void showCustomPopupMessage(final Activity mContext, final AllItemResponse selectedItemObj , final String price){
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = null;
        LayoutInflater inflaterObjRef = null;
        View layoutObjRef = null;
        Button cancelBtn ,btnSave;
        ImageButton btnReduce,btnAdd;
        selectedDiscountPercentage = 0;

        TextView priceText;
        final EditText etQuantity;
        Button allowButtonRef = null;
        try {
            // mFragmentObjGlobal=mFragmentObj;
            alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(mContext);
            alertDialogBuilder.setCancelable(false);
            inflaterObjRef = mContext.getLayoutInflater();
            layoutObjRef = inflaterObjRef.inflate(R.layout.popup_screen, null);
            alertDialogBuilder.setView(layoutObjRef);
            cancelBtn = (Button) layoutObjRef.findViewById(R.id.btn_cancel);
            btnSave = (Button) layoutObjRef.findViewById(R.id.btn_save);
            btnReduce = (ImageButton) layoutObjRef.findViewById(R.id.btn_reduce);
            btnAdd = (ImageButton) layoutObjRef.findViewById(R.id.btn_add);
            switchA = (SwitchCompat) layoutObjRef.findViewById(R.id.switch_id1);
            switchB = (SwitchCompat) layoutObjRef.findViewById(R.id.switch_id2);
            switchC = (SwitchCompat) layoutObjRef.findViewById(R.id.switch_id3);
            switchD = (SwitchCompat) layoutObjRef.findViewById(R.id.switch_id4);
            switchE = (SwitchCompat) layoutObjRef.findViewById(R.id.switch_id5);
            switchA.setOnClickListener(this);
            switchB.setOnClickListener(this);
            switchC.setOnClickListener(this);
            switchD.setOnClickListener(this);
            switchE.setOnClickListener(this);

            etQuantity = (EditText) layoutObjRef.findViewById(R.id.et_quantity);
            etQuantity.setFilters(new InputFilter[]{ new EditTextMinMax("1", "1000")});
            //  messageText = (TextView) layoutObjRef.findViewById(R.id.messageText);
            priceText = (TextView) layoutObjRef.findViewById(R.id.txt_item_price);
            priceText.setText(setthePrice(String.valueOf(selectedItemObj.getId()),price));
            // messageText.setText(msg);
            // allowButtonRef = (Button) layoutObjRef.findViewById(R.id.allow_button);
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            btnReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(etQuantity.getText().toString()!=null &&etQuantity.getText().length()>0){
                        int value = Integer.parseInt(etQuantity.getText().toString());
                        if(value > 1)
                            etQuantity.setText((value - 1)+"");
                        else
                            etQuantity.setText("1");
                    }


                }
            });
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(etQuantity.getText().toString()!=null &&etQuantity.getText().length()>0){
                        int value = Integer.parseInt(etQuantity.getText().toString());
                        if(value!=1000)
                            etQuantity.setText((value + 1)+"");
                    } else{
                        etQuantity.setText("1");
                    }

                }
            });
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddShopCartItem addShopCartItemObj = new AddShopCartItem();
                    addShopCartItemObj.setDiscount(etQuantity.getText().toString());
                    addShopCartItemObj.setDiscount("A");
                    addShopCartItemObj.setDiscountPercent(selectedDiscountPercentage);
                    addShopCartItemObj.setQuantities(etQuantity.getText().toString());
                    addShopCartItemObj.setItemNo(selectedItemObj.getId().toString());
                    ////////checking the duplicate item before adding

                    if(tempShopCartListObj.size()>0) {
                        for(int j=0;j<tempShopCartListObj.size();j++) {
                            if (selectedItemObj.getId().toString().equalsIgnoreCase(tempShopCartListObj.get(j).getItemNo())) {
                                if(tempShopCartListObj.get(j).getDiscountPercent() == selectedDiscountPercentage){
                                    rendertheObject = false;
                                    Log.d("TAG", "duplicate item::no==" + selectedItemObj.getId().toString() + "..position==" );
                                    //getting single item price before proceeding..
                                    int singleItemPrice = shopCartItemsList.get(j).getPrice() / Integer.parseInt(shopCartItemsList.get(j).getQuantities());

                                    ////////

                                    shopCartItemsList.get(j).setQuantities(String.valueOf(Integer.parseInt(shopCartItemsList.get(j).getQuantities())+Integer.parseInt(etQuantity.getText().toString())));
                                    shopCartItemsList.get(j).setPrice(singleItemPrice *(Integer.parseInt(shopCartItemsList.get(j).getQuantities())));
                                    //MokaposContants.SUB_TOTAL_VALUE = MokaposContants.SUB_TOTAL_VALUE + singleItemPrice *(Integer.parseInt(shopCartItemsList.get(j).getQuantities()));
                                    shopCartInterfaceCallbackObj.updateShopCartItems(shopCartItemsList);
                                    break;
                                } else {
                                    rendertheObject = true;
                                }


                            } else {
                                rendertheObject = true;
                                Log.d("Tag", "no duplicate items found::" + tempShopCartListObj.get(j).getItemNo());

                            }
                        }
                    } else{
                        addShopCartItemObj.setPrice(priceOfTheItem(price, etQuantity.getText().toString()));
                        //MokaposContants.SUB_TOTAL_VALUE = MokaposContants.SUB_TOTAL_VALUE + addShopCartItemObj.getPrice();
                        shopCartItemsList.add(addShopCartItemObj);
                        tempShopCartListObj.add(addShopCartItemObj);
                        shopCartInterfaceCallbackObj.updateShopCartItems(shopCartItemsList);
                        rendertheObject = false;
                    }

                    if(rendertheObject){

                        addShopCartItemObj.setPrice(priceOfTheItem(price, etQuantity.getText().toString()));
                       // MokaposContants.SUB_TOTAL_VALUE = MokaposContants.SUB_TOTAL_VALUE + addShopCartItemObj.getPrice();
                        shopCartItemsList.add(addShopCartItemObj);
                        tempShopCartListObj.add(addShopCartItemObj);
                        shopCartInterfaceCallbackObj.updateShopCartItems(shopCartItemsList);
                        rendertheObject = false;
                    }



                    /////////////end of checking duplicate item
                    //below portion commenting to check--whether duplicate item skip working or not
                    /*addShopCartItemObj.setPrice(priceOfTheItem(price, etQuantity.getText().toString()));
                    MokaposContants.SUB_TOTAL_VALUE = MokaposContants.SUB_TOTAL_VALUE + addShopCartItemObj.getPrice();
                    shopCartItemsList.add(addShopCartItemObj);
                    tempShopCartListObj.add(addShopCartItemObj);
                    shopCartInterfaceCallbackObj.updateShopCartItems(shopCartItemsList);*/
                    //// dup item testing

                    alertDialog.dismiss();

                }
            });
            alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int priceOfTheItem(String price, String s) {
        int val = Integer.parseInt(price);
        int discount = Integer.parseInt(s);
        return  val * discount;

    }

    private static String setthePrice(String Item, String Price) {

        return "Item"+Item +"- $ "+Price;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.switch_id1:
                    selectedSwitch(1);
                break;
            case R.id.switch_id2:
                selectedSwitch(2);
                break;
                case R.id.switch_id3:
                    selectedSwitch(3);
                break;
                case R.id.switch_id4:
                    selectedSwitch(4);
                break;
                case R.id.switch_id5:
                    selectedSwitch(5);
                break;
            default:
                break;
        }
    }

    private void selectedSwitch(int i) {
        Log.d("TAG","Selected switch is::"+i);
        if(i==1){
            selectedDiscountPercentage = 0;
            switchB.setChecked(false);
            switchC.setChecked(false);
            switchD.setChecked(false);
            switchE.setChecked(false);
        } else if(i ==2 ) {
            selectedDiscountPercentage =10;
            switchA.setChecked(false);
            switchC.setChecked(false);
            switchD.setChecked(false);
            switchE.setChecked(false);
        } else if(i ==3 ) {
            selectedDiscountPercentage = 35.5;
            switchA.setChecked(false);
            switchB.setChecked(false);
            switchD.setChecked(false);
            switchE.setChecked(false);
        } else if(i ==4 ) {
            selectedDiscountPercentage = 50;
            switchA.setChecked(false);
            switchB.setChecked(false);
            switchC.setChecked(false);
            switchE.setChecked(false);
        }else if(i ==5 ) {
            selectedDiscountPercentage = 100;
            switchA.setChecked(false);
            switchB.setChecked(false);
            switchC.setChecked(false);
            switchD.setChecked(false);

        }
    }
}
