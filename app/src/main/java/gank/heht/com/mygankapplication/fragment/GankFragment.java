package gank.heht.com.mygankapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import gank.heht.com.mygankapplication.activity.SpaceImageDetailActivity;
import gank.heht.com.mygankapplication.adapter.GankGridAdapter;
import gank.heht.com.mygankapplication.bean.InfoBean;
import gank.heht.com.mygankapplication.utils.GsonUtil;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hehaitao01 on 2017/3/9.
 */

public class GankFragment extends BaseNewsFragment{
    GankGridAdapter gridAdapter = null;
    private int page = 1;
    StringBuilder url = new StringBuilder("http://gank.io/api/data/");
    private List<InfoBean.ResultsBean> datas = new ArrayList<>();
    String urlStr="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            url.append(URLEncoder.encode("福利", "utf-8"));
            url.append("/10/");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        urlStr = url.toString();
    }

    @Override
    protected void initViews() {
        pullRefreshRecyclerView.setRefreshLoadMoreListener(this);
        pullRefreshRecyclerView.setGridLayout(2);
        gridAdapter = new GankGridAdapter(getActivity(), datas);
        pullRefreshRecyclerView.setAdapter(gridAdapter);//recyclerview设置适配器
        //实现适配器自定义的点击监听
        gridAdapter.setOnRecyclerViewItemClickListener(new GankGridAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Intent intent = new Intent(getActivity(), SpaceImageDetailActivity.class);
                int position = pullRefreshRecyclerView.getChildAdapterPosition(view);
                ArrayList<String> imgList = new ArrayList<>();
                imgList.add(datas.get(position).getUrl());
                intent.putStringArrayListExtra("imgList", imgList);
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
        refreshData(urlStr+page);
    }

    @Override
    public void onRefresh() {
        page = 1;
        datas.clear();
        refreshData(urlStr + page);
        Logger.t("img").d(urlStr);
    }

    @Override
    public void onLoadMore() {
        refreshData(urlStr + (++page));

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
                        Logger.t("net").d(s);
                        if (!TextUtils.isEmpty(s)) {

                            //数据解析
                            InfoBean infoBean = GsonUtil.GsonToBean(s, InfoBean.class);
                            datas.addAll(infoBean.getResults());
                            //让适配器刷新数据
                            gridAdapter.notifyDataSetChanged();
                        }
                        //停止swipeRefreshLayout加载动画
                        pullRefreshRecyclerView.setRefreshing(false);
                    }

                    @Override
                    public void onCacheSuccess(String s, Call call) {
                        super.onCacheSuccess(s, call);
                        Logger.t("cache").d(s);
                        if (!TextUtils.isEmpty(s)) {

                            //数据解析
                            InfoBean infoBean = GsonUtil.GsonToBean(s, InfoBean.class);
                            datas.addAll(infoBean.getResults());
                            //让适配器刷新数据
                            gridAdapter.notifyDataSetChanged();
                        }
                        //停止swipeRefreshLayout加载动画
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
