package gank.heht.com.mygankapplication.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.dl7.player.media.IjkPlayerView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;

public class IjkPlayerActivity extends AppCompatActivity {
    @BindView(R.id.player_view)
   IjkPlayerView mPlayerView;

    private String imgUrl = "";
    private String videoUrl = "";
    private String videoTitle ="";
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijk_player);
        ButterKnife.bind(this);
        //  Choose any one interface you need, init() must be the first to use.
        init();
    }

    private void init() {

        imgUrl = getIntent().getStringExtra("imgUrl");
        videoUrl = getIntent().getStringExtra("videoUrl");
        videoTitle = getIntent().getStringExtra("videoTitle");
        ImageOptions imageOptions = new ImageOptions.Builder().setRadius(2).setUseMemCache(true).setFadeIn(true).build();
        x.image().bind(mPlayerView.mPlayerThumb, imgUrl, imageOptions);
        mPlayerView.init()              // Initialize, the first to use
                .setTitle(videoTitle)  	// set title
                .setSkipTip(-1)  // set the position you want to skip
                .alwaysFullScreen()
                .enableOrientation()    // enable orientation
                .setVideoPath(videoUrl)    // set video url
                //.setVideoSource(null, videoUrl, videoUrl, videoUrl, null) // set multiple video url
                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)  // set the initial video url
                .enableDanmaku()
                .setDanmakuSource(getResources().openRawResource(R.raw.bili))
                .start();   // Start playing
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPlayerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlayerView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayerView.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPlayerView.configurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mPlayerView.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (mPlayerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}

