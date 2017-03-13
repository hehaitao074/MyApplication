package gank.heht.com.mygankapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.ToastUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gank.heht.com.mygankapplication.R;
import uk.co.senab.photoview.PhotoViewAttacher;


public class SpaceImageDetailActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    @BindView(R.id.viewPager_img)
    ViewPager viewpager_img;
    @BindView(R.id.hint)
    TextView hint;
    @BindView(R.id.save)
    TextView save;
    private  List<String> imgList = new ArrayList<>();

    private String saveUrl  ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_image_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        imgList = getIntent().getStringArrayListExtra("imgList");
        MyAdapter adpter = new MyAdapter(SpaceImageDetailActivity.this,imgList);
        viewpager_img.setAdapter(adpter);
        viewpager_img.setCurrentItem(0);
        viewpager_img.addOnPageChangeListener(this);
        hint.setText( 1 + "/" + imgList.size());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        saveUrl = imgList.get(position);
        hint.setText(position + 1 + "/" + imgList.size());
    }
    @OnClick(R.id.save)
    public void save(final View view){
        ToastUtils.showLongToast("开始下载");
        LogUtils.d("debug",saveUrl);
        RequestParams params = new RequestParams(saveUrl);
        // 创建目录
        File appDir = new File(Environment.getExternalStorageDirectory(), "WoTuImgDownLoad");
        if (!appDir.exists()) {
            appDir.mkdir();
        }

        String fileName = System.currentTimeMillis() + ".jpg" ;
        File file = new File(appDir, fileName);
        params.setSaveFilePath(file.getAbsolutePath());
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<File>() {
            @Override
            public void onSuccess(File result) {
                ToastUtils.showLongToast("下载成功，请去相册查看");
                // 其次把文件插入到系统图库
                try {
                    MediaStore.Images.Media.insertImage(view.getContext().getContentResolver(), result.getAbsolutePath(), result.getName(), null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 最后通知图库更新
                view.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + result.getPath())));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtils.showLongToast("下载失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private class MyAdapter extends PagerAdapter {

        private Context context;
        private List<String> images;
        private SparseArray<View> cacheView;
        private ViewGroup containerTemp;

        public MyAdapter(Context context, List<String> images) {
            this.context = context;
            this.images = images;
            cacheView = new SparseArray<>(images.size());
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            if(containerTemp == null) containerTemp = container;

            View view = cacheView.get(position);

            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.layout_img_detail_item,container,false);
                view.setTag(position);
                final ImageView image = (ImageView) view.findViewById(R.id.smooth_img_detail);
                final PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(image);
                saveUrl = images.get(position);
                x.image().bind(image, images.get(position), new Callback.CommonCallback<Drawable>() {
                    @Override
                    public void onSuccess(Drawable result) {
                        photoViewAttacher.update();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

                photoViewAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(View view, float x, float y) {
                        Activity activity = (Activity) context;
                        activity.finish();
                    }
                });
                cacheView.put(position,view);
            }
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

    }
}
