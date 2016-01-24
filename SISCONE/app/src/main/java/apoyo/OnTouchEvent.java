package apoyo;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by user on 24/01/2016.
 */
public class OnTouchEvent implements View.OnTouchListener {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        return false;
    }
}
