package gank.heht.com.mygankapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.utils.LogUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import gank.heht.com.mygankapplication.activity.WebActivity;
import gank.heht.com.mygankapplication.adapter.GankGridAdapter;
import gank.heht.com.mygankapplication.adapter.ListInfoAdapter;
import gank.heht.com.mygankapplication.bean.InfoBean;
import gank.heht.com.mygankapplication.utils.GsonUtil;

/**
 * Created by hehaitao01 on 2017/3/9.
 */

public class MaNongFragment extends BaseNewsFragment{

    ListInfoAdapter webAdapter = null;
    private int page = 1;
    StringBuilder url = new StringBuilder("http://gank.io/api/data/all/10/");
    private List<InfoBean.ResultsBean> datas = new ArrayList<>();
    String urlStr="";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        urlStr = url.toString();
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        refreshData(urlStr+page);
    }

    @Override
    protected void initViews() {
        pullRefreshRecyclerView.setRefreshLoadMoreListener(this);
        pullRefreshRecyclerView.setLinearLayout();
        webAdapter = new ListInfoAdapter(getActivity(), datas);
        pullRefreshRecyclerView.setAdapter(webAdapter);//recyclerview设置适配器
        //实现适配器自定义的点击监听
        webAdapter.setOnRecyclerViewItemClickListener(new GankGridAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                int position = pullRefreshRecyclerView.getChildAdapterPosition(view);
                intent.putExtra("url", datas.get(position).getUrl());
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);

            }

            @Override
            public void onItemLongClick(View view) {

            }
        });

    }
    @Override
    public void onRefresh() {
        page = 1;
        datas.clear();
        refreshData(urlStr + page);
    }

    @Override
    public void onLoadMore() {
        refreshData(urlStr + (++page));
        LogUtils.d("page", page);
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
                if (!TextUtils.isEmpty(result)) {                    //数据解析
                    InfoBean infoBean = GsonUtil.GsonToBean(result, InfoBean.class);
                    datas.addAll(infoBean.getResults());
                    //让适配器刷新数据
                    webAdapter.notifyDataSetChanged();
                }
                //停止swipeRefreshLayout加载动画
                pullRefreshRecyclerView.setRefreshing(false);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.d("hht", ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}