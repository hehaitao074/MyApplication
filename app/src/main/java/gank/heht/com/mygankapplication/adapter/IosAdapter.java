package gank.heht.com.mygankapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import gank.heht.com.mygankapplication.R;
import gank.heht.com.mygankapplication.bean.InfoBean;


/**
 * Created by hehaitao01 on 2017/3/7.
 */

public class IosAdapter extends BaseAdapter {

    public IosAdapter(Context mContext, List<InfoBean.ResultsBean> datas) {
        super(mContext, datas);

    }

    @Override
    public IosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext
        ).inflate(R.layout.layout_item_ios, parent,
                false);//这个布局就是一个imageview用来显示图片
        IosViewHolder holder = new IosViewHolder(view);
        //给布局设置点击和长点击监听
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof IosViewHolder) {
            InfoBean.ResultsBean resultsBean = datas.get(position);
            String imgUrl = "";
            if(resultsBean.getImages()!=null&&resultsBean.getImages().size()>0){
                imgUrl = resultsBean.getImages().get(0);
            }
            //加载图片
            ImageOptions imageOptions = new ImageOptions.Builder().setRadius(2).setUseMemCache(true).setFadeIn(true).setSize(120, 160).build();
            x.image().bind(((IosViewHolder) holder).mImg, imgUrl, imageOptions);
            ((IosViewHolder) holder).mTxtTltle.setText(resultsBean.getDesc());
            ((IosViewHolder) holder).mTxtTWho.setText("来源: "+resultsBean.getWho());
        }
    }


    class IosViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTxtTltle;
        TextView mTxtTWho;

        public IosViewHolder(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.img_item__list);
            mTxtTltle = (TextView) itemView.findViewById(R.id.txt_title);
            mTxtTWho = (TextView) itemView.findViewById(R.id.txt_who);
        }
    }
}
