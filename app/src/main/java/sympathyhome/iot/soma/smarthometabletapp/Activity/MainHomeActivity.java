package sympathyhome.iot.soma.smarthometabletapp.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import sympathyhome.iot.soma.smarthometabletapp.Fragment.AlbumPhotoFragment;
import sympathyhome.iot.soma.smarthometabletapp.R;
import sympathyhome.iot.soma.smarthometabletapp.Transformer.SlowViewPager;
import sympathyhome.iot.soma.smarthometabletapp.Transformer.ZoomOutPageTransformer;
import sympathyhome.iot.soma.smarthometabletapp.Fragment.WebviewSettingFragment;

public class MainHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        initActivity();
    }

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private SlowViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    private int mCurrentPage;

    private Timer mTimer;

    private final int mTimerDuration = 3000;
    private final int mTimerStartDuration = 3000;

    private Map<String, List<String>> mFamilyPhotoMap;

    public void initActivity(){

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (SlowViewPager) findViewById(R.id.activity_main_home_pager);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(1);

        mTimer = new Timer(true);

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        UpdateGUI();
                    }
                });

            }
        }, mTimerStartDuration, mTimerDuration);


    }

    private void UpdateGUI() {
        //tv.setText(String.valueOf(i));
//        startActivity(new Intent(MainActivity.this, AlbumPhotoActivity.class));
        if (mCurrentPage == NUM_PAGES) {
            mCurrentPage = 0;
        }
        mPager.setCurrentItem(++mCurrentPage);

    }

    private void photoData(){
        Iterator<String> keys = mFamilyPhotoMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
//            mImageAdapter.addSections(key, new FamilyImageViewAdapter(mFamilyPhotoMap.get(key)));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private Fragment mWebViewFragment = new WebviewSettingFragment();
        private Fragment mAlbumPhotoFragment = new AlbumPhotoFragment();

        private Map<String, List<String>> mFamilyPhotoMap;

        public void setmFamilyPhotoMap(Map<String, List<String>> familyPhotoMap){
            mFamilyPhotoMap = familyPhotoMap;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return mWebViewFragment;
                case 1:
                    return mAlbumPhotoFragment;

                default:
                    AlbumPhotoFragment frame = new AlbumPhotoFragment();
                    frame.changePhoto(position);
                    return frame;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
