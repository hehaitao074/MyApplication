package gank.heht.com.mygankapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import gank.heht.com.mygankapplication.activity.IjkPlayerActivity;
import gank.heht.com.mygankapplication.adapter.GankGridAdapter;
import gank.heht.com.mygankapplication.adapter.ListVideoAdapter;
import gank.heht.com.mygankapplication.bean.VideoBean;
import gank.heht.com.mygankapplication.utils.GsonUtil;

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
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)) {
                    Logger.t("debug").d(result);
                    String jsonList = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray(""+mNewsId).toString();
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
}