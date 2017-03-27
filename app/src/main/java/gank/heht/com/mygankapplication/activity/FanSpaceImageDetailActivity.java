package gank.heht.com.mygankapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;
import gank.heht.com.mygankapplication.adapter.BaseAdapter;
import gank.heht.com.mygankapplication.adapter.FanImgAdapter;
import uk.co.senab.photoview.PhotoView;


public class FanSpaceImageDetailActivity extends AppCompatActivity {

    @BindView(R.id.fan_ry)
    RecyclerView fanRy;
    @BindView(R.id.fan_img_detail)
    PhotoView photoView;

    FanImgAdapter fanImgAdapter;


    private List<String> imgList = new ArrayList<>();

    private String saveUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_space_image_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        imgList = getIntent().getStringArrayListExtra("imgList");
        fanImgAdapter = new FanImgAdapter(this, imgList);
        fanRy.setAdapter(fanImgAdapter);

        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        fanRy.setLayoutManager(layoutManager);
        fanRy.setHasFixedSize(true);
        fanRy.setAdapter(fanImgAdapter);
        fanRy.addOnScrollListener(new CenterScrollListener());
        fanImgAdapter.setOnRecyclerViewItemClickListener(new BaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v) {
                int itemPosition = fanRy.getChildAdapterPosition(v);
                    String imgUrl = imgList.get(itemPosition);
                    ImageOptions imageOptions = new ImageOptions.Builder().setRadius(2).setUseMemCache(true).setFadeIn(true).build();
                    x.image().bind(photoView, imgUrl, imageOptions);

            }

            @Override
            public void onItemLongClick(View v) {

            }
        });

    }

}
