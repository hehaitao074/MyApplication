package gank.heht.com.mygankapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;
import gank.heht.com.mygankapplication.view.PullRefreshRecyclerView;

/**
 * Created by hehaitao01 on 2017/3/9.
 */

public abstract  class BaseNewsFragment extends Fragment implements PullRefreshRecyclerView.RefreshLoadMoreListener {
    @BindView(R.id.pullrefresh_recycleview_newslist)
    PullRefreshRecyclerView pullRefreshRecyclerView;

    //根部view
    private View rootView;
    protected Context context;
    private Boolean hasInitData = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView==null){
            rootView = inflater.inflate(R.layout.fragment_newlist, null);//判断是否加载过view
        }
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //判断是否加载过数据
        if (!hasInitData) {
            initSwipeRefresh();
            initViews();
            updateViews(true);
            hasInitData = true;
        }
    }


    /**
     * 初始化下拉刷新
     */
    private void initSwipeRefresh() {
        if (pullRefreshRecyclerView != null) {
            pullRefreshRecyclerView.setRefreshLoadMoreListener(this);
            pullRefreshRecyclerView.setLinearLayout();

        }
    }

    /**
     * 绑定布局文件
     * @return  布局文件ID
     */
   // protected abstract int attachLayoutRes();


    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     * @param isRefresh 新增参数，用来判断是否为下拉刷新调用，下拉刷新的时候不应该再显示加载界面和异常界面
     */
    protected abstract void updateViews(boolean isRefresh);

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}