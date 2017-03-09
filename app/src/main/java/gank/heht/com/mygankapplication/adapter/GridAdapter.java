package gank.heht.com.mygankapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import gank.heht.com.mygankapplication.R;
import gank.heht.com.mygankapplication.bean.BeautifulGirls;


/**
 * Created by hehaitao01 on 2017/3/7.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> implements View.OnClickListener,View.OnLongClickListener{

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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if(holder instanceof MyViewHolder){
            //加载图片
            ImageOptions imageOptions = new ImageOptions.Builder().setRadius(2).setUseMemCache(true).setFadeIn(true).setSize(90,120).build();
           x.image().bind(((MyViewHolder) holder).img,datas.get(position).getUrl(),imageOptions);
           // ImageRequestManager.getRequest().display(mContext,((MyViewHolder) holder).img,datas.get(position).getUrl(), ConvertUtils.dp2px(80),ConvertUtils.dp2px(80),false);
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
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
           img = (ImageView) itemView.findViewById(R.id.img_item);
        }
    }
}
