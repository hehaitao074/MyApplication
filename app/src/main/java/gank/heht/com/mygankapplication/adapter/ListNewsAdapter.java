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
import gank.heht.com.mygankapplication.bean.NewsInfo;


/**
 * Created by hehaitao01 on 2017/3/7.
 */

public class ListNewsAdapter extends BaseAdapter<NewsInfo> {


    public ListNewsAdapter(Context mContext, List<NewsInfo> datas) {
        super(mContext, datas);

    }


    @Override
    public AndroidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new AndroidViewHolder(VIEW_FOOTER);
        } else if (viewType == TYPE_HEADER) {
            return new AndroidViewHolder(VIEW_HEADER);
        } else {
            View view = LayoutInflater.from(mContext
            ).inflate(R.layout.layout_item_news, parent,
                    false);//这个布局就是一个imageview用来显示图片
            AndroidViewHolder holder = new AndroidViewHolder(view);
            //给布局设置点击和长点击监听
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            if (holder instanceof AndroidViewHolder) {
                NewsInfo newsInfo = datas.get(position);
                String imgUrl = "";
                if (newsInfo.getImgsrc() != null) {
                    imgUrl = newsInfo.getImgsrc();
                }
                //加载图片
                ImageOptions imageOptions = new ImageOptions.Builder().setRadius(2).setUseMemCache(true).setFadeIn(true).setSize(120, 160).build();
                x.image().bind(((AndroidViewHolder) holder).mImg, imgUrl, imageOptions);
                ((AndroidViewHolder) holder).mTxtTltle.setText(newsInfo.getTitle());
                ((AndroidViewHolder) holder).mTxtTWho.setText("来源: " + newsInfo.getSource());
            }
        }
    }






    class AndroidViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTxtTltle;
        TextView mTxtTWho;

        public AndroidViewHolder(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.news_item__list);
            mTxtTltle = (TextView) itemView.findViewById(R.id.new_txt_title);
            mTxtTWho = (TextView) itemView.findViewById(R.id.new_txt_who);
        }
    }
}