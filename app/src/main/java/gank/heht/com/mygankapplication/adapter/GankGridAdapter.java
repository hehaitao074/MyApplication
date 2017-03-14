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
import gank.heht.com.mygankapplication.bean.InfoBean;


/**
 * Created by hehaitao01 on 2017/3/7.
 */

public class GankGridAdapter extends BaseAdapter <InfoBean.ResultsBean>{

    public GankGridAdapter(Context mContext, List<InfoBean.ResultsBean> datas) {
        super(mContext, datas);

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
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {
            InfoBean.ResultsBean resultsBean = datas.get(position);
            //加载图片
            ImageOptions imageOptions = new ImageOptions.Builder().setRadius(2).setUseMemCache(true).setFadeIn(true).setSize(300, 400).build();
            x.image().bind(((MyViewHolder) holder).img, resultsBean.getUrl(), imageOptions);

        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_item);
        }
    }
}
