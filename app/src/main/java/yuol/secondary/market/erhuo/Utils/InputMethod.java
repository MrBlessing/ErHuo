package yuol.secondary.market.erhuo.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import yuol.secondary.market.erhuo.Utils.ActivityCollector;

public class InputMethod {
    //强制隐藏输入法
    public static void hideInputMethod(EditText editText,Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
}
