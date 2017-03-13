package gank.heht.com.mygankapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by hehaitao01 on 2017/3/7.
 */

public class BaseAdapter <T>extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener,View.OnLongClickListener{
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

     Context mContext;
     List<T> datas;

    public  interface OnRecyclerViewItemClickListener{
         void onItemClick(View v);
         void onItemLongClick(View v);
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = null;
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public BaseAdapter(Context mContext, List<T> datas) {
        this.mContext = mContext;
        this.datas = datas;

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

}
