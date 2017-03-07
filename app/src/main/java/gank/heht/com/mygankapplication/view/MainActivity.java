package gank.heht.com.mygankapplication.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.grid_recycler)
    RecyclerView recyclerView;

    private GridLayoutManager gridLayoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//黄油刀的使用

        setSupportActionBar(toolbar);

    }


    private void intiView(){




    }
}
