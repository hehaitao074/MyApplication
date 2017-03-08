package gank.heht.com.mygankapplication.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import com.blankj.utilcode.utils.LogUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;
import gank.heht.com.mygankapplication.adapter.GridAdapter;
import gank.heht.com.mygankapplication.bean.BeautifulGirls;
import gank.heht.com.mygankapplication.utils.GsonUtil;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.grid_recycler)
    RecyclerView recyclerView;

    private GridLayoutManager gridLayoutManager;
    GridAdapter gridAdapter = null;
    private int page = 1;
    StringBuilder url = new StringBuilder("http://gank.io/api/data/");
    private List<BeautifulGirls.ResultsBean> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//黄油刀的使用
        initView();
        setListener();
        try {
            url.append(URLEncoder.encode("福利", "utf-8"));
            url.append("/10/1");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        getData(url.toString());

    }


    private void initView() {
        gridLayoutManager = new GridLayoutManager(MainActivity.this, 3, GridLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局
        //调整SwipeRefreshLayout的位置
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        recyclerView.setLayoutManager(gridLayoutManager);
        gridAdapter = new GridAdapter(MainActivity.this, datas);
        recyclerView.setAdapter(gridAdapter);//recyclerview设置适配器

    }

    private void setListener() {
        //swipeRefreshLayout刷新监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData(url.toString());

            }
        });
    }

    private void getData(String url) {
        //设置swipeRefreshLayout为刷新状态
        swipeRefreshLayout.setRefreshing(true);
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)) {                    //数据解析
                    BeautifulGirls beautifulGirls = GsonUtil.GsonToBean(result, BeautifulGirls.class);
                    datas.addAll(beautifulGirls.getResults());
                    //实现适配器自定义的点击监听
                    gridAdapter.setOnRecyclerViewItemClickListener(new GridAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view) {
                            int position = recyclerView.getChildAdapterPosition(view);
                            // SnackbarUtil.ShortSnackbar(coordinatorLayout,"点击第"+position+"个",SnackbarUtil.Info).show();
                            //彩色Snackbar工具类，请看我之前的文章《没时间解释了，快使用Snackbar!——Android Snackbar花式使用指南》http://www.jianshu.com/p/cd1e80e64311
                        }

                        @Override
                        public void onItemLongClick(View view) {

                        }
                    });
                    LogUtils.d("hht",datas.toString());
                    //让适配器刷新数据
                    gridAdapter.notifyDataSetChanged();
                }
                //停止swipeRefreshLayout加载动画
                swipeRefreshLayout.setRefreshing(false);
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
