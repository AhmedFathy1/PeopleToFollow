package com.example.android.af.follower;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Hosts a DetailsFragment
 */


public class DetailsActivity extends AppCompatActivity {

    private final String KEY_SELECTED = "com.example.android.af.follower.SELECTED";
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        int selected = getIntent().getIntExtra(KEY_SELECTED, 0);
        mPager.setCurrentItem(selected);
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            DetailsFragment Frag = new DetailsFragment();
            Frag.position = position;
            return Frag;
        }

        @Override
        public int getCount() {
            return PeopleList.getInstance().getList().size();
        }
    }
}