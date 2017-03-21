package gank.heht.com.mygankapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.ConvertUtils;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;
import com.panxw.android.imageindicator.AutoPlayManager;
import com.panxw.android.imageindicator.ImageIndicatorView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import gank.heht.com.mygankapplication.adapter.GankGridAdapter;
import gank.heht.com.mygankapplication.adapter.ListNewsAdapter;
import gank.heht.com.mygankapplication.bean.NewsInfo;
import gank.heht.com.mygankapplication.utils.GsonUtil;
import gank.heht.com.mygankapplication.view.NetworkImageIndicatorView;

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
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)) {
                    Logger.t("debug").d(result);
                    String jsonList = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray(""+mNewsId).toString();
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
            public void onError(Throwable ex, boolean isOnCallback) {
                Logger.t("debug").d(ex.getMessage());
                pullRefreshRecyclerView.setRefreshing(false);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                pullRefreshRecyclerView.setRefreshing(false);
            }

            @Override
            public void onFinished() {
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
}