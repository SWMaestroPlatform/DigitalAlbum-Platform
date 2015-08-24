package sympathyhome.iot.soma.smarthometabletapp.Transformer;

import java.lang.reflect.Field;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

/**
 * Created by YoonSeok on 15. 8. 24..
 */
public class SlowViewPager extends ViewPager {

    public SlowViewPager(Context context) {
        super(context);
    }
    public SlowViewPager(Context context, AttributeSet attr) {
        super(context, attr);
        setMyScroller();
    }



    private void setMyScroller()
    {
        try
        {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            scroller.set(this, new MyScroller(getContext()));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public class MyScroller extends Scroller
    {
        public MyScroller(Context context)
        {
            super(context, new DecelerateInterpolator());
        }
        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration)
        {
            super.startScroll(startX, startY, dx, dy, 3000 /*1 secs*/);
        }
    }
}
