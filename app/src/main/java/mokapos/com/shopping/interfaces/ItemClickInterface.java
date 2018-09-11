package mokapos.com.shopping.interfaces;

import mokapos.com.shopping.networking.response.AllItemResponse;

/**
 * Created by Manuramv on 9/10/2018.
 */

public interface ItemClickInterface {
    public void showPopup(int position, AllItemResponse selectedItemObj, String price);
}
