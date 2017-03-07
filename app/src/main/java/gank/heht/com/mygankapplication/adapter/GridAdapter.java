package gank.heht.com.mygankapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;
import gank.heht.com.mygankapplication.bean.BeautifulGirls;

/**
 * Created by hehaitao01 on 2017/3/7.
 */

public class GridAdapter extends RecyclerView.Adapter implements View.OnClickListener,View.OnLongClickListener{

    private Context mContext;
    private List<BeautifulGirls.ResultsBean> datas;

    public  interface OnRecyclerViewItemClickListener{
         void onItemClick(View v);
         void onItemLongClick(View v);
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = null;
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public GridAdapter(Context mContext, List<BeautifulGirls.ResultsBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext
        ).inflate(R.layout.layout_item_img, parent,
                false);//这个布局就是一个imageview用来显示图片
        MyViewHolder holder = new MyViewHolder(view);

        //给布局设置点击和长点击监听
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof MyViewHolder){
            //加载图片
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    //点击事件回调
    @Override
    public void onClick(View v) {
        if (onRecyclerViewItemClickListener != null) {
            onRecyclerViewItemClickListener.onItemClick(v);
        }
    }
    @Override
    public boolean onLongClick(View v) {
        if (onRecyclerViewItemClickListener!= null) {
            onRecyclerViewItemClickListener.onItemLongClick(v);
        }
        return false;
    }

    class  MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img_item)
        private ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
