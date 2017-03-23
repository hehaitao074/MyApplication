package gank.heht.com.mygankapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.JsonParser;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import gank.heht.com.mygankapplication.activity.IjkPlayerActivity;
import gank.heht.com.mygankapplication.adapter.GankGridAdapter;
import gank.heht.com.mygankapplication.adapter.ListVideoAdapter;
import gank.heht.com.mygankapplication.bean.VideoBean;
import gank.heht.com.mygankapplication.utils.GsonUtil;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hehaitao01 on 2017/3/9.
 */

public class VideoListFragment extends BaseNewsFragment{
    ListVideoAdapter androidAdapter = null;
    private static final String NEWS_TYPE_KEY = "NewsTypeKey";
    private static final String NEWS_TYPE_URL = "NewsTypeUrl";
    private String mNewsId;
    private String mNewsUrl;
    private int page = 1;
    private List<VideoBean> datas = new ArrayList<>();
    String urlStr="";

    public static VideoListFragment newInstance(String newsId, String newsUrl) {
        VideoListFragment fragment = new VideoListFragment();
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
        androidAdapter = new ListVideoAdapter(getActivity(), datas);
        pullRefreshRecyclerView.setAdapter(androidAdapter);//recyclerview设置适配器
        //实现适配器自定义的点击监听
        androidAdapter.setOnRecyclerViewItemClickListener(new GankGridAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Intent intent = new Intent(getActivity(), IjkPlayerActivity.class);
                int position = pullRefreshRecyclerView.getChildAdapterPosition(view);
                intent.putExtra("imgUrl", datas.get(position).getCover());
                intent.putExtra("videoUrl", datas.get(position).getMp4_url());
                intent.putExtra("videoTitle", datas.get(position).getTitle());
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);

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
    private void refreshData(String url) {
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
                            List<VideoBean> infoBeans = GsonUtil.jsonToList(jsonList,VideoBean.class);
                            datas.addAll(infoBeans);
                            //让适配器刷新数据
                            androidAdapter.notifyDataSetChanged();
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
                            List<VideoBean> infoBeans = GsonUtil.jsonToList(jsonList,VideoBean.class);
                            datas.addAll(infoBeans);
                            //让适配器刷新数据
                            androidAdapter.notifyDataSetChanged();
                        }
                        //停止swipeRefreshLayout加载动画
                        pullRefreshRecyclerView.setRefreshing(false);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        pullRefreshRecyclerView.setRefreshing(false);
                    }

                    @Override
                    public void onCacheError(Call call, Exception e) {
                        super.onCacheError(call, e);
                        pullRefreshRecyclerView.setRefreshing(false);
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}