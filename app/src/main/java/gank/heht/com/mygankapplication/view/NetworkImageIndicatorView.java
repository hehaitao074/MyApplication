package gank.heht.com.mygankapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.panxw.android.imageindicator.ImageIndicatorView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by hehaitao01 on 2017/3/21.
 */

public class NetworkImageIndicatorView extends ImageIndicatorView {
    public NetworkImageIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NetworkImageIndicatorView(Context context) {
        super(context);
    }

    public void setupLayoutByImageUrl(List<String> urlList) {
        for(String url: urlList) {
            ImageView imageView = new ImageView(getContext());
            ImageOptions imageOptions = new ImageOptions.Builder().setRadius(2).setUseMemCache(true).setFadeIn(true).build();
            x.image().bind(imageView, url, imageOptions);
            addViewItem(imageView);
        }
    }
}
