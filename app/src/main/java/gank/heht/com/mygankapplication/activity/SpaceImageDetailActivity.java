package gank.heht.com.mygankapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;
import uk.co.senab.photoview.PhotoView;

public class SpaceImageDetailActivity extends AppCompatActivity {

    @BindView(R.id.smooth_img_detail)
    PhotoView smoothImageView;

    private String imagUrl  ="";
//    private int mLocationX = 0;
//    private int mLocationY = 0;
//    private int mWidth = 0;
//    private int mHeight=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_image_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        imagUrl = getIntent().getStringExtra("url");
//        mLocationX = getIntent().getIntExtra("locationX", 0);
//        mLocationY = getIntent().getIntExtra("locationY", 0);
//        mWidth = getIntent().getIntExtra("width", 0);
//        mHeight = getIntent().getIntExtra("height", 0);
//        smoothImageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
//        smoothImageView.transformIn();
//        smoothImageView.setScaleType(ImageView.ScaleType.FIT_XY);

        //加载图片
        x.image().bind(smoothImageView,imagUrl);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
