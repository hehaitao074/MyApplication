package gank.heht.com.mygankapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;
import gank.heht.com.mygankapplication.adapter.ChannelAdapter;
import gank.heht.com.mygankapplication.application.GankApplication;
import gank.heht.com.mygankapplication.bean.ChannelInfo;
import gank.heht.com.mygankapplication.utils.FileHelper;
import gank.heht.com.mygankapplication.utils.GsonUtil;
import gank.heht.com.mygankapplication.utils.ItemDragHelperCallback;


/**
 * 频道 增删改查 排序
 *
 */
public class ChannelActivity extends AppCompatActivity implements ChannelAdapter.OnFinishChannelEditListenner {

    @BindView(R.id.recy)
     RecyclerView mRecy;

    private ChannelInfo channelInfo;
    private OnChannelChangeListenner onChannelChangeListenner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        channelInfo = GankApplication.getInstance().getNewsTypeInfos();
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecy.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecy);

        final ChannelAdapter adapter = new ChannelAdapter(this, helper, channelInfo.getMy_channel(), channelInfo.getOther_channel());
        adapter.setOnFinishChannelEditListenner(this);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mRecy.setAdapter(adapter);

        adapter.setOnMyChannelItemClickListener(new ChannelAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(ChannelActivity.this, channelInfo.getMy_channel().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onChannelEditFinished() {
        //保存频道的编辑内容
        String channelJson = GsonUtil.GsonString(channelInfo);
        boolean isOk = FileHelper.writeStringData(this,"NewsChannel",channelJson);
        if(isOk){
            ToastUtils.showLongToast("保存成功");
        }

    }
    public interface OnChannelChangeListenner{
        void onChannelChange();
    }

    public void setOnChannelChangeListenner(OnChannelChangeListenner onChannelChangeListenner) {
        this.onChannelChangeListenner = onChannelChangeListenner;
    }
}
