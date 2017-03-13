package gank.heht.com.mygankapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.LogUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;
import gank.heht.com.mygankapplication.activity.SpaceImageDetailActivity;
import gank.heht.com.mygankapplication.adapter.GankGridAdapter;
import gank.heht.com.mygankapplication.adapter.HuaBanGridAdapter;
import gank.heht.com.mygankapplication.bean.HuaBanInfoBean;
import gank.heht.com.mygankapplication.bean.HuaBanMeiziInfo;
import gank.heht.com.mygankapplication.view.PullRefreshRecyclerView;

/**
 * Created by hehaitao01 on 2017/3/9.
 */

public class HuaBanFragment extends Fragment implements PullRefreshRecyclerView.RefreshLoadMoreListener {
    @BindView(R.id.pullrefresh_recycleview_gank)
    PullRefreshRecyclerView pullRefreshRecyclerView;

    HuaBanGridAdapter gridAdapter = null;
    private int page = 1;
    StringBuilder url = new StringBuilder("http://route.showapi.com/819-1?showapi_appid=15314&type=34&showapi_sign=d424376f51f1467da1b8c75debebf148&num=20&page=");
    private List<HuaBanMeiziInfo> datas = new ArrayList<>();
    String urlStr="";

    private String mTitle;

    public static HuaBanFragment getInstance(String title) {
        HuaBanFragment sf = new HuaBanFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        urlStr = url.toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gank, null);
        ButterKnife.bind(this,v);
        initView();
        refreshData(urlStr+page);
        return v;
    }

    private void initView() {
        pullRefreshRecyclerView.setRefreshLoadMoreListener(this);
        pullRefreshRecyclerView.setGridLayout(2);
        gridAdapter = new HuaBanGridAdapter(getActivity(), datas);
        pullRefreshRecyclerView.setAdapter(gridAdapter);//recyclerview设置适配器
        //实现适配器自定义的点击监听
        gridAdapter.setOnRecyclerViewItemClickListener(new GankGridAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Intent intent = new Intent(getActivity(), SpaceImageDetailActivity.class);
                int position = pullRefreshRecyclerView.getChildAdapterPosition(view);
                ArrayList<String> imgList = new ArrayList<>();
                imgList.add(datas.get(position).getThumb());
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
                    HuaBanInfoBean huaBanInfoBean = HuaBanInfoBean.createFromJson(result);
                    datas.addAll(huaBanInfoBean.infos);
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
