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

import com.blankj.utilcode.utils.LogUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;

/**
 * Created by hehaitao01 on 2017/3/9.
 */

public class MeiTuFragment extends Fragment implements OnTabSelectListener{


    @BindView(R.id.vp_meitu)
    ViewPager viewPager;
    @BindView(R.id.tl_meitu)
    SlidingTabLayout tabLayout_meitu;

    private ArrayList<Fragment> mPicFragments = new ArrayList<>();
    private final String[] mPicTitles = {"美图", "淘女郎", "花瓣妹子", "煎蛋妹子"};
    private MyPagerAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meitu, null);
        ButterKnife.bind(this,v);
        initView();
        return v;
    }

    private void initView(){
        mAdapter = new MyPagerAdapter(getChildFragmentManager());//这地方使用getChildFragmentManager()方法,管理fragment中的fragment
        viewPager.setAdapter(mAdapter);
        LogUtils.d("meitu",viewPager.getAdapter().getCount()+" "+mPicTitles.length);
        tabLayout_meitu.setViewPager(viewPager,mPicTitles);
        tabLayout_meitu.setOnTabSelectListener(this);
        viewPager.setCurrentItem(0);


    }
    private void initFragment(){
        mPicFragments.add(new GankFragment());
        mPicFragments.add(new TaoNvFragment());
        mPicFragments.add(new HuaBanFragment());
        mPicFragments.add(new JianDanFragment());
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
            return mPicFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mPicTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mPicFragments.get(position);
        }
    }
}