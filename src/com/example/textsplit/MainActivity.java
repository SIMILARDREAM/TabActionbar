package com.example.textsplit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private ViewPager        mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private Fragment1        mFragment1      = new Fragment1();
    private Fragment2        mFragment2      = new Fragment2();
    private Fragment3        mFragment3      = new Fragment3();
    private int              currIndex       = 0;               // 当前页卡编号
    private static final int TAB_INDEX_COUNT = 3;

    private static final int TAB_INDEX_ONE   = 0;
    private static final int TAB_INDEX_TWO   = 1;
    private static final int TAB_INDEX_THREE = 2;

    private TextView         tab1;
    private TextView         tab2;
    private TextView         tab3;
    private View             cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_actionbar, null);
        cursor = v.findViewById(R.id.cursor);
        tab1 = (TextView) v.findViewById(R.id.tab1);
        tab2 = (TextView) v.findViewById(R.id.tab2);
        tab3 = (TextView) v.findViewById(R.id.tab3);
        tab1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                updateTabs(1);
                mViewPager.setCurrentItem(0);
            }
        });

        actionBar.setCustomView(v);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mViewPagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // final ActionBar actionBar = getSupportActionBar();
                // actionBar.setSelectedNavigationItem(position);
                updateTabs(position + 1);
                Animation animation = new TranslateAnimation(120 * currIndex, 120 * position, 0, 0);// 显然这个比較简洁，仅仅有一行代码。
                currIndex = position;
                animation.setFillAfter(true);// True:图片停在动画结束位置
                animation.setDuration(300);
                if (cursor != null)
                    cursor.startAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                case ViewPager.SCROLL_STATE_IDLE:
                    // TODO
                    break;
                case ViewPager.SCROLL_STATE_DRAGGING:

                    break;
                case ViewPager.SCROLL_STATE_SETTLING:
                    // TODO
                    break;
                default:
                    // TODO
                    break;
                }
            }
        });
        mViewPager.setCurrentItem(0);
        updateTabs(1);
    }

    public void updateTabs(int position) {

        switch (position) {
        case 1:
            tab1.setBackgroundColor(getResources().getColor(android.R.color.white));
            tab2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            tab3.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            break;
        case 2:
            tab2.setBackgroundColor(getResources().getColor(android.R.color.white));
            tab1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            tab3.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            break;
        case 3:
            tab3.setBackgroundColor(getResources().getColor(android.R.color.white));
            tab2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            tab1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
            case TAB_INDEX_ONE:
                return mFragment1;
            case TAB_INDEX_TWO:
                return mFragment2;
            case TAB_INDEX_THREE:
                return mFragment3;
            }
            throw new IllegalStateException("No fragment at position " + position);
        }

        @Override
        public int getCount() {
            return TAB_INDEX_COUNT;
        }

    }

}
