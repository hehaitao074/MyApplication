package gank.heht.com.mygankapplication.imgutils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by hehaitao01 on 2017/3/8.
 */

public interface IimageListener {
    void display(Context context, ImageView imageView,
                 String url, int progressId, int errorId,
                 Object tag);
    void display(Context context, ImageView imageView,
                 String url, int progressId, int errorId);
    void display(Context context, ImageView imageView,
                 String url, int progressId);
    void display(Context context, ImageView imageView,
                 String url);
    void display(Context context, ImageView imageView, Uri uri);
    void display(Context context, ImageView imageView,
                 String url,int width,int height,boolean crop);
    void display(Context context, ImageView imageView,
                 String url,int width,int height,boolean crop,int radius);
}
