package mokapos.com.shopping.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import mokapos.com.shopping.R;
import mokapos.com.shopping.dto.AddShopCartItem;
import mokapos.com.shopping.fragments.LibraryFragment;
import mokapos.com.shopping.fragments.ShoppingCartFragment;
import mokapos.com.shopping.interfaces.ShopCartInterfaceCallback;

public class MainActivity extends CustomBaseActivity implements ShopCartInterfaceCallback {

    private LibraryFragment libraryFragment;
    private ShoppingCartFragment shopCartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        libraryFragment = new LibraryFragment();
        shopCartFragment = new ShoppingCartFragment();
        loadTwoFragmentsTogether();
    }




    public void navigateToFragment(Fragment frag, boolean addtostack) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_top, frag); // f1_container is your FrameLayout container
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (addtostack) {
            ft.addToBackStack(null);
        }

        invalidateOptionsMenu();
        ft.commit();
    }

    public void loadTwoFragmentsTogether() {
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.frame_top, libraryFragment);
        transaction.add(R.id.frame_bottom, shopCartFragment);

        transaction.commit();

    }

    @Override
    public void updateShopCartItems(ArrayList<AddShopCartItem> shopCartList) {
        shopCartFragment.updateCartItems(shopCartList);
    }
}
