package sympathyhome.iot.soma.smarthometabletapp.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import sympathyhome.iot.soma.smarthometabletapp.Data.YSDataUtil;
import sympathyhome.iot.soma.smarthometabletapp.Fragment.AlbumPhotoFragment;
import sympathyhome.iot.soma.smarthometabletapp.R;
import sympathyhome.iot.soma.smarthometabletapp.Transformer.SlowViewPager;
import sympathyhome.iot.soma.smarthometabletapp.Transformer.ZoomOutPageTransformer;
import sympathyhome.iot.soma.smarthometabletapp.Fragment.WebviewSettingFragment;

public class MainHomeActivity extends YSActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        initActivity();
    }

    private static final int NUM_PAGES = 11;

    private SlowViewPager mPager;

    private PagerAdapter mPagerAdapter;

    private int mCurrentPage;

    private Timer mTimer;

    private final int mTimerDuration = 15000;
    private final int mTimerStartDuration = 15000;

    private Map<String, List<String>> mFamilyPhotoMap;

    private LinearLayout mTouchLinearLayout;
    private boolean enableTouch = false;

    public void initActivity(){

        mTouchLinearLayout = (LinearLayout) findViewById(R.id.activity_main_home_linearlayout);
        mTouchLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enableTouch){
                    enableTouch = false;
                }
                else {
                    enableTouch = true;
                    mPager.setCurrentItem(0);
                    mTouchLinearLayout.setVisibility(View.INVISIBLE);
                    mTimer.cancel();
                    mTimer = null;
                    mCurrentPage = 0;
                }
            }
        });

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (SlowViewPager) findViewById(R.id.activity_main_home_pager);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(1);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("OnPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("OnPageScroll State Changed");
                if (mPager.getCurrentItem() == 1) {
                    mCurrentPage = 1;
                    enableTouch = false;
                    mTouchLinearLayout.setVisibility(View.VISIBLE);
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
                System.out.println("ChangeChange`");
            }
        });


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

            for (int i = 0 ; i<mAlbumPhotoFragmentArray.length; i++){
                mAlbumPhotoFragmentArray[i] = new AlbumPhotoFragment();
                Bundle args = new Bundle();
                args.putInt("image", i);
                mAlbumPhotoFragmentArray[i].setArguments(args);
            }
        }

        private Fragment mWebViewFragment = new WebviewSettingFragment();

        private AlbumPhotoFragment [] mAlbumPhotoFragmentArray = new AlbumPhotoFragment[NUM_PAGES -1];

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

                default:
                    return mAlbumPhotoFragmentArray[position-1];
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
