package gank.heht.com.mygankapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.ConvertUtils;
import com.google.gson.JsonParser;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;
import com.panxw.android.imageindicator.AutoPlayManager;
import com.panxw.android.imageindicator.ImageIndicatorView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import gank.heht.com.mygankapplication.adapter.GankGridAdapter;
import gank.heht.com.mygankapplication.adapter.ListNewsAdapter;
import gank.heht.com.mygankapplication.bean.NewsInfo;
import gank.heht.com.mygankapplication.utils.GsonUtil;
import gank.heht.com.mygankapplication.view.NetworkImageIndicatorView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hehaitao01 on 2017/3/9.
 */

public class NewsListFragment extends BaseNewsFragment{
    ListNewsAdapter androidAdapter = null;
    private static final String NEWS_TYPE_KEY = "NewsTypeKey";
    private static final String NEWS_TYPE_URL = "NewsTypeUrl";
    private String mNewsId;
    private String mNewsUrl;
    private int page = 1;
    private List<NewsInfo> datas = new ArrayList<>();
    String urlStr="";
    private static  final  String HEADLINE = "T1348647909107";
    AutoPlayManager autoBrocastManager;

    NetworkImageIndicatorView networkImageIndicatorView;

    public static NewsListFragment newInstance(String newsId,String newsUrl) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_TYPE_KEY, newsId);
        bundle.putString(NEWS_TYPE_URL, newsUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewsId = getArguments().getString(NEWS_TYPE_KEY);
            mNewsUrl = getArguments().getString(NEWS_TYPE_URL);
        }
    }
    @Override
    protected void initViews() {
        androidAdapter = new ListNewsAdapter(getActivity(), datas);
        pullRefreshRecyclerView.setAdapter(androidAdapter);//recyclerview设置适配器
        //实现适配器自定义的点击监听
        androidAdapter.setOnRecyclerViewItemClickListener(new GankGridAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
//                Intent intent = new Intent(getActivity(), WebActivity.class);
//                int position = pullRefreshRecyclerView.getChildAdapterPosition(view);
//                intent.putExtra("url", datas.get(position).);
//                startActivity(intent);
//                getActivity().overridePendingTransition(0, 0);

            }

            @Override
            public void onItemLongClick(View view) {

            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        urlStr = MessageFormat.format(mNewsUrl,mNewsId,page);
        Logger.t("debug").d(urlStr);
        refreshData(urlStr);

    }

    @Override
    public void onRefresh() {
        page=1;
        datas.clear();
        urlStr = MessageFormat.format(mNewsUrl,mNewsId,page);
        refreshData(urlStr);
    }

    @Override
    public void onLoadMore() {
        urlStr = MessageFormat.format(mNewsUrl,mNewsId,(++page));
        refreshData(urlStr);

    }


    /**
     * 刷新数据
     * @param url
     */
    private void refreshData(final String url) {
        //设置swipeRefreshLayout为刷新状态
        pullRefreshRecyclerView.setRefreshing(true);
        OkGo.get(url)    // 请求方式和请求url, get请求不需要拼接参数，支持get，post，put，delete，head，options请求
                .tag(this)               // 请求的 tag, 主要用于取消对应的请求
                .cacheKey(url)    // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (!TextUtils.isEmpty(s)) {
                            Logger.t("debug").d(s);
                            String jsonList = new JsonParser().parse(s).getAsJsonObject().getAsJsonArray(""+mNewsId).toString();
                            //数据解析
                            List<NewsInfo> infoBeans = GsonUtil.jsonToList(jsonList,NewsInfo.class);
                            datas.addAll(infoBeans);
                            //让适配器刷新数据
                            androidAdapter.notifyDataSetChanged();
                            //添加头部头图轮播
                            if(page==1&&null!=infoBeans.get(0).getAds()&&infoBeans.get(0).getAds().size()>=0){
                                List<String> urlList = new ArrayList<String>();
                                for(NewsInfo.AdData adData: infoBeans.get(0).getAds()){
                                    urlList.add(adData.getImgsrc());
                                }
                                //初始化轮播头图
                                initImageIndicatorView(urlList);
                            }
                        }
                        //停止swipeRefreshLayout加载动画
                        pullRefreshRecyclerView.setRefreshing(false);
                    }

                    @Override
                    public void onCacheSuccess(String s, Call call) {
                        super.onCacheSuccess(s, call);
                        if (!TextUtils.isEmpty(s)) {
                            Logger.t("debug").d(s);
                            String jsonList = new JsonParser().parse(s).getAsJsonObject().getAsJsonArray(""+mNewsId).toString();
                            //数据解析
                            List<NewsInfo> infoBeans = GsonUtil.jsonToList(jsonList,NewsInfo.class);
                            datas.addAll(infoBeans);
                            //让适配器刷新数据
                            androidAdapter.notifyDataSetChanged();
                            //添加头部头图轮播
                            if(page==1&&null!=infoBeans.get(0).getAds()&&infoBeans.get(0).getAds().size()>=0){
                                List<String> urlList = new ArrayList<String>();
                                for(NewsInfo.AdData adData: infoBeans.get(0).getAds()){
                                    urlList.add(adData.getImgsrc());
                                }
                                //初始化轮播头图
                                initImageIndicatorView(urlList);
                            }
                        }
                        //停止swipeRefreshLayout加载动画
                        pullRefreshRecyclerView.setRefreshing(false);
                    }
                });

    }

    /**
     * 轮播图初始化
     * @param urlList
     */
    private void initImageIndicatorView(List<String> urlList) {
        networkImageIndicatorView = new NetworkImageIndicatorView(getActivity());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(140));
        networkImageIndicatorView.setLayoutParams(params);
        networkImageIndicatorView.setIndicateStyle(ImageIndicatorView.INDICATE_USERGUIDE_STYLE);
        autoBrocastManager =  new AutoPlayManager(networkImageIndicatorView);
        autoBrocastManager.setBroadcastEnable(true);
        autoBrocastManager.setBroadCastTimes(5);//loop times
        autoBrocastManager.setBroadcastTimeIntevel(3 * 1000, 3 * 1000);//set first play time and interval
        androidAdapter.addHeaderView(networkImageIndicatorView);
        networkImageIndicatorView.setupLayoutByImageUrl(urlList);
        networkImageIndicatorView.show();
        autoBrocastManager.loop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}