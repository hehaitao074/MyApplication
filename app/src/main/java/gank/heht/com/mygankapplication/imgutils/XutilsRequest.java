package gank.heht.com.mygankapplication.imgutils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.blankj.utilcode.utils.LogUtils;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by hehaitao01 on 2017/3/8.
 */

//        x.image().bind(imageView,url,imageOptions);
//
//// assets file
//        x.image().bind(imageView,"assets://test.gif",imageOptions);
//
//// local file
//        x.image().bind(imageView,new File("/sdcard/test.gif").toURI().toString(),imageOptions);
//        x.image().bind(imageView,"/sdcard/test.gif",imageOptions);
//        x.image().bind(imageView,"file:///sdcard/test.gif",imageOptions);
//        x.image().bind(imageView,"file:/sdcard/test.gif",imageOptions);
//
//        x.image().bind(imageView,url,imageOptions,new Callback.CommonCallback<Drawable>(){...});
//        x.image().loadDrawable(url,imageOptions,new Callback.CommonCallback<Drawable>(){...});
//        x.image().loadFile(url,imageOptions,new Callback.CommonCallback<File>(){...});

public class XutilsRequest implements IimageListener {
    @Override
    public void display(Context context, ImageView imageView, String url, int progressId, int errorId, Object tag) {
        x.image().bind(imageView,url);
    }

    @Override
    public void display(Context context, ImageView imageView, String url, int progressId, int errorId) {
        x.image().bind(imageView,url);
    }

    @Override
    public void display(Context context, ImageView imageView, String url, int progressId) {
        x.image().bind(imageView,url);
    }

    @Override
    public void display(Context context, ImageView imageView, String url) {
        x.image().bind(imageView,url);
    }

    @Override
    public void display(Context context, ImageView imageView, Uri uri) {
        x.image().bind(imageView,uri.getPath());
    }

    @Override
    public void display(Context context, ImageView imageView, String url, int width, int height, boolean crop) {
        ImageOptions imageOptions = new ImageOptions.Builder().setSize(width,height).setCrop(crop).build();
        x.image().bind(imageView,url,imageOptions);
        LogUtils.d("hht","load img");
    }

    @Override
    public void display(Context context, ImageView imageView, String url, int width, int height, boolean crop, int radius) {
        ImageOptions imageOptions = new ImageOptions.Builder().setSize(width,height).setCrop(crop).setRadius(radius).build();
        x.image().bind(imageView,url,imageOptions);
        LogUtils.d("hht","load img");
    }


//    public void display(ImageView imageView, String url, ImageOptions imageOptions, Callback.CommonCallback commonCallback) {
//        x.image().bind(imageView,url,imageOptions,commonCallback);
//    }
}
