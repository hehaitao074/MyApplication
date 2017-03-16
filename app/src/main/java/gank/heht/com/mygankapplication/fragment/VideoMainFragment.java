package gank.heht.com.mygankapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;
import gank.heht.com.mygankapplication.utils.NewsConst;

/**
 * Created by hehaitao01 on 2017/3/9.
 */

public class VideoMainFragment extends Fragment implements OnTabSelectListener {


    @BindView(R.id.vp_video_main)
    ViewPager viewPager;
    @BindView(R.id.tl_video_main)
    SlidingTabLayout tabLayout_Video;

    private ArrayList<Fragment> mNewsFragments = new ArrayList<>();
    private final String[] VIDEO_ID = new String[]{
            "V9LG4B3A0", "V9LG4E6VR", "V9LG4CHOR", "00850FRB"
    };
    private final String[] VIDEO_TITLE = new String[]{
            "热点", "搞笑", "娱乐", "精品"
    };
    private MyPagerAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video_main, null);
        ButterKnife.bind(this, v);
        initView();
        return v;
    }

    private void initView() {
        mAdapter = new MyPagerAdapter(getChildFragmentManager());//这地方使用getChildFragmentManager()方法,管理fragment中的fragment
        viewPager.setAdapter(mAdapter);
        tabLayout_Video.setViewPager(viewPager, VIDEO_TITLE);
        tabLayout_Video.setOnTabSelectListener(this);
        viewPager.setCurrentItem(0);


    }

    private void initFragment() {
        for (int i = 0; i < VIDEO_ID.length; i++) {
            mNewsFragments.add(VideoListFragment.newInstance(VIDEO_ID[i],NewsConst.NEWS_VIDEOS));
        }

    }

    @Override
    public void onTabSelect(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mNewsFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return VIDEO_TITLE[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mNewsFragments.get(position);
        }
    }
}