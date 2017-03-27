package gank.heht.com.mygankapplication.application;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;
import com.facebook.stetho.Stetho;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import org.xutils.BuildConfig;
import org.xutils.common.util.LogUtil;
import org.xutils.x;

import java.util.logging.Level;

import gank.heht.com.mygankapplication.bean.ChannelInfo;
import gank.heht.com.mygankapplication.utils.HttpUtils;
import gank.heht.com.mygankapplication.utils.NewsUtils;

/**
 * Created by hehaitao01 on 2017/3/7.
 */

public class GankApplication extends Application {

    private static  ChannelInfo channelInfo = null;
    private static GankApplication single=null;
    private static final boolean DEBUG = true;
    @Override
    public void onCreate() {
        super.onCreate();
        single=this;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        Utils.init(getApplicationContext());//初始化开源工具类
        initTbs();
        initOkGo();
        if(DEBUG){
            initStetho();//开启调试影响性能
        }

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
    public  ChannelInfo getNewsTypeInfos(){
        if(channelInfo==null){
            channelInfo = NewsUtils.getNewsTypesFromAssets(getApplicationContext());
        }
        return  channelInfo;
    }

    private void  initOkGo(){
        //必须调用初始化
        OkGo.init(this);

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()
                    // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                    // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                    .debug("OkGo", Level.INFO, true)
                    .setConnectTimeout(HttpUtils.CONN_TIME_OUT)  //全局的连接超时时间
                    .setReadTimeOut(HttpUtils.READ_TIME_OUT)     //全局的读取超时时间
                    .setWriteTimeOut(HttpUtils.WRITE_TIME_OUT)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(HttpUtils.CACHE_TIME_OUT)

                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(3);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

}
