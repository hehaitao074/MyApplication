package gank.heht.com.mygankapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.utils.LogUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import gank.heht.com.mygankapplication.activity.SpaceImageDetailActivity;
import gank.heht.com.mygankapplication.adapter.GankGridAdapter;
import gank.heht.com.mygankapplication.adapter.JianDanGridAdapter;
import gank.heht.com.mygankapplication.bean.JianDanBean;
import gank.heht.com.mygankapplication.utils.GsonUtil;

/**
 * Created by hehaitao01 on 2017/3/9.
 */

public class JianDanFragment extends BaseNewsFragment{


    JianDanGridAdapter gridAdapter = null;
    private int page = 1;
    StringBuilder url = new StringBuilder("http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_ooxx_comments&page=");
    private List<JianDanBean.CommentsBean> datas = new ArrayList<>();
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
    protected void updateViews(boolean isRefresh) {
        refreshData(urlStr+page);
    }
    @Override
    protected void initViews()  {
        pullRefreshRecyclerView.setRefreshLoadMoreListener(this);
        pullRefreshRecyclerView.setGridLayout(2);
        gridAdapter = new JianDanGridAdapter(getActivity(), datas);
        pullRefreshRecyclerView.setAdapter(gridAdapter);//recyclerview设置适配器
        //实现适配器自定义的点击监听
        gridAdapter.setOnRecyclerViewItemClickListener(new GankGridAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Intent intent = new Intent(getActivity(), SpaceImageDetailActivity.class);
                int position = pullRefreshRecyclerView.getChildAdapterPosition(view);
                ArrayList<String> imgList = new ArrayList<>();
                imgList.addAll(datas.get(position).getPics());
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
                    JianDanBean infoBean = GsonUtil.GsonToBean(result, JianDanBean.class);
                    datas.addAll(infoBean.getComments());
                    //让适配器刷新数据
                    gridAdapter.notifyDataSetChanged();
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
