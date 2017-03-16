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
import gank.heht.com.mygankapplication.bean.VideoBean;


/**
 * Created by hehaitao01 on 2017/3/7.
 */

public class ListVideoAdapter extends BaseAdapter<VideoBean> {


    public ListVideoAdapter(Context mContext, List<VideoBean> datas) {
        super(mContext, datas);

    }


    @Override
    public AndroidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext
        ).inflate(R.layout.layout_item_video, parent,
                false);//这个布局就是一个imageview用来显示图片
        AndroidViewHolder holder = new AndroidViewHolder(view);
        //给布局设置点击和长点击监听
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof AndroidViewHolder) {
            VideoBean videoBean = datas.get(position);
            String imgUrl = "";
            if (videoBean.getCover() != null) {
                imgUrl = videoBean.getCover();
            }
            //加载图片
            ImageOptions imageOptions = new ImageOptions.Builder().setRadius(2).setUseMemCache(true).setFadeIn(true).build();
            x.image().bind(((AndroidViewHolder) holder).mImg, imgUrl, imageOptions);
            ((AndroidViewHolder) holder).mTxtTltle.setText(videoBean.getTitle());

        }
    }


    class AndroidViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTxtTltle;


        public AndroidViewHolder(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.video_item__list);
            mTxtTltle = (TextView) itemView.findViewById(R.id.video_txt_title);
        }
    }
}