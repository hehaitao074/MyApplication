package gank.heht.com.mygankapplication.application;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import org.xutils.BuildConfig;
import org.xutils.common.util.LogUtil;
import org.xutils.x;

import java.util.List;

import gank.heht.com.mygankapplication.bean.NewsTypeInfo;
import gank.heht.com.mygankapplication.utils.NewsUtils;

/**
 * Created by hehaitao01 on 2017/3/7.
 */

public class GankApplication extends Application {

    private static  List<NewsTypeInfo> newsTypeInfos = null;
    private static GankApplication single=null;
    @Override
    public void onCreate() {
        super.onCreate();
        single=this;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        Utils.init(getApplicationContext());//初始化开源工具类
        initTbs();
    }
    public GankApplication() {}

    //静态工厂方法
    public static GankApplication getInstance() {
        if (single == null) {
            single = new GankApplication();
        }
        return single;
    }
    private void initTbs() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                LogUtil.i("onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };

        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                LogUtil.i("onDownloadFinish");
            }

            @Override
            public void onInstallFinish(int i) {
                LogUtil.i("onInstallFinish");
            }

            @Override
            public void onDownloadProgress(int i) {
                LogUtil.i("onDownloadProgress:" + i);
            }
        });

        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    /**
     * 初始化新闻频道
     * @return
     */
    public  List<NewsTypeInfo> getNewsTypeInfos(){
        if(newsTypeInfos==null){
            newsTypeInfos = NewsUtils.getNewsTypesFromAssets(getApplicationContext());
        }
        return  newsTypeInfos;
    }

}
