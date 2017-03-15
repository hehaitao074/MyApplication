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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;
import gank.heht.com.mygankapplication.application.GankApplication;
import gank.heht.com.mygankapplication.bean.NewsTypeInfo;

/**
 * Created by hehaitao01 on 2017/3/9.
 */

public class NewsMainFragment extends Fragment implements OnTabSelectListener{


    @BindView(R.id.vp_news_main)
    ViewPager viewPager;
    @BindView(R.id.tl_new_main)
    SlidingTabLayout tabLayout_meitu;

    private ArrayList<Fragment> mNewsFragments = new ArrayList<>();
    private List<NewsTypeInfo> newsTypeInfos = null;
    private  String[] mPicTitles = null;
    private MyPagerAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsTypeInfos = GankApplication.getInstance().getNewsTypeInfos();
        mPicTitles = new String[newsTypeInfos.size()];
        initFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_main, null);
        ButterKnife.bind(this,v);
        initView();
        return v;
    }

    private void initView(){
        mAdapter = new MyPagerAdapter(getChildFragmentManager());//这地方使用getChildFragmentManager()方法,管理fragment中的fragment
        viewPager.setAdapter(mAdapter);
        tabLayout_meitu.setViewPager(viewPager,mPicTitles);
        tabLayout_meitu.setOnTabSelectListener(this);
        viewPager.setCurrentItem(0);


    }
    private void initFragment(){
        for (int i=0;i<newsTypeInfos.size();i++) {
            NewsTypeInfo newsTypeInfo = newsTypeInfos.get(i);
            mPicTitles[i] = newsTypeInfo.getName();
            mNewsFragments.add(NewsListFragment.newInstance(newsTypeInfo.getTypeId(),newsTypeInfo.getUrl()));
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
            return mPicTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mNewsFragments.get(position);
        }
    }
}