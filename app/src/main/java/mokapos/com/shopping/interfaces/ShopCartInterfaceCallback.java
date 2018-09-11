package mokapos.com.shopping.interfaces;

import java.util.ArrayList;

import mokapos.com.shopping.dto.AddShopCartItem;

/**
 * Created by Manuramv on 9/10/2018.
 */

public interface ShopCartInterfaceCallback {
    public void updateShopCartItems(ArrayList<AddShopCartItem> shopCartItems);
}
