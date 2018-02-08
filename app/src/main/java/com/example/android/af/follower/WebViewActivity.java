package com.example.android.af.follower;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class WebViewActivity extends FragmentActivity {
    private static final int NUM_PAGES = 3;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    private int personPosition = 0;
    private PersonData mPersonData;

    private final String KEY_PERSON_POSITION = "com.example.android.af.follower.PERSON_POSITION";
    private final String KEY_SOCIAL_POSITION = "com.example.android.af.follower.SOCIAL_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mPager = findViewById(R.id.web_pager);

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        mPager.setAdapter(mPagerAdapter);
        personPosition = getIntent().getIntExtra(KEY_PERSON_POSITION, 0);
        int tabPosition = getIntent().getIntExtra(KEY_SOCIAL_POSITION, 0);
        mPersonData = PeopleList.getInstance().getList().get(personPosition);
        mPager.setCurrentItem(tabPosition);
    }

    String[] TabNames = new String[]{"Google+", "Twitter", "Instagram"};


    public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            WebViewFragment Frag = new WebViewFragment();

            Frag.mURL = position == 0 ? "http://www.google.com/" + mPersonData.getGoogleId() : position == 1 ? "http://twitter.com/" + mPersonData.getTwitterId() : "http://instagram.com/" + mPersonData.getInstagram();
            return Frag;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TabNames[position];
        }
    }
}

