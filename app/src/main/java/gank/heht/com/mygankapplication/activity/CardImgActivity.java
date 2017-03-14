package gank.heht.com.mygankapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.heht.com.mygankapplication.R;
import gank.heht.com.mygankapplication.adapter.CardAdapter;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

/**
 * Created by hehaitao01 on 2017/3/9.
 */

public class CardImgActivity extends AppCompatActivity {
    @BindView(R.id.recycleview_card)
    RecyclerView recyclerView;

    CardAdapter cardAdapter = null;
    private  List<String> imgList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cardimg_activity);
        ButterKnife.bind(this);
        initView();

    }



    private void initView() {
        imgList = getIntent().getStringArrayListExtra("imgList");
        cardAdapter = new CardAdapter(this,imgList);
        recyclerView.setAdapter(cardAdapter);
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), imgList);
        ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback); CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
        cardCallback.setOnSwipedListener(new OnSwipeListener() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {

            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Object o, int direction) {

            }

            @Override
            public void onSwipedClear() {

            }
        });

    }



}
