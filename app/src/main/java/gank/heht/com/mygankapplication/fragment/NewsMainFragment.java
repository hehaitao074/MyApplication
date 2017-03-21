package gank.heht.com.mygankapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gank.heht.com.mygankapplication.R;
import gank.heht.com.mygankapplication.activity.ChannelActivity;
import gank.heht.com.mygankapplication.application.GankApplication;
import gank.heht.com.mygankapplication.bean.NewsTypeInfo;
import gank.heht.com.mygankapplication.entity.MessageEvent;

/**
 * Created by hehaitao01 on 2017/3/9.
 */

public class NewsMainFragment extends Fragment implements OnTabSelectListener{


    @BindView(R.id.vp_news_main)
    ViewPager viewPager;
    @BindView(R.id.tl_new_main)
    SlidingTabLayout tabLayout_meitu;
    @BindView(R.id.txt_channel)
    TextView txtChannel;

    private ArrayList<Fragment> mNewsFragments = new ArrayList<>();
    private List<NewsTypeInfo> newsTypeInfos = null;
    private  String[] mChannelTitles = null;
    private MyPagerAdapter mAdapter;

    public NewsMainFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

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
        tabLayout_meitu.setViewPager(viewPager,mChannelTitles);
        tabLayout_meitu.setOnTabSelectListener(this);
        viewPager.setCurrentItem(0);


    }
    private void initFragment(){
        newsTypeInfos = GankApplication.getInstance().getNewsTypeInfos().getMy_channel();
        Logger.t("channel").d(newsTypeInfos);
        mChannelTitles = new String[newsTypeInfos.size()];
        for (int i=0;i<newsTypeInfos.size();i++) {
            NewsTypeInfo newsTypeInfo = newsTypeInfos.get(i);
            mChannelTitles[i] = newsTypeInfo.getName();
            mNewsFragments.add(NewsListFragment.newInstance(newsTypeInfo.getTypeId(),newsTypeInfo.getUrl()));
        }


    }


    @OnClick(R.id.txt_channel)
    public void editChannel(View v){
        Intent intent = new Intent(getActivity(), ChannelActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(0, 0);
    }

    @Override
    public void onTabSelect(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
            return mChannelTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mNewsFragments.get(position);
        }
    }


    @Subscribe
    public void onMessage(MessageEvent messageEvent){
        if(messageEvent.isChange){
            mNewsFragments.clear();
            initFragment();
            mAdapter.notifyDataSetChanged();
            tabLayout_meitu.setViewPager(viewPager,mChannelTitles);
        }

    }

}