package gank.heht.com.mygankapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;
import gank.heht.com.mygankapplication.fragment.MaNongFragment;
import gank.heht.com.mygankapplication.fragment.MeiTuFragment;
import gank.heht.com.mygankapplication.fragment.NewsMainFragment;
import gank.heht.com.mygankapplication.fragment.VideoMainFragment;

public class TabMainActivity extends AppCompatActivity implements OnTabSelectListener {

    @BindView(R.id.vp)
    ViewPager viewPager;
    @BindView(R.id.tl_1)
    SlidingTabLayout tabLayout_1;

    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"美图", "新闻", "视频", "码农"};
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);
        ButterKnife.bind(this);

        initFragment();
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabLayout_1.setViewPager(viewPager,mTitles);
        tabLayout_1.setOnTabSelectListener(this);
        viewPager.setCurrentItem(0);

    }

    private void initFragment(){
        mFragments.add(new MeiTuFragment());
        mFragments.add(new NewsMainFragment());
        mFragments.add(new VideoMainFragment());
        mFragments.add(new MaNongFragment());

    }

    @Override
    public void onTabSelect(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {
        Toast.makeText(mContext, "onTabReselect&position--->" + position, Toast.LENGTH_SHORT).show();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
