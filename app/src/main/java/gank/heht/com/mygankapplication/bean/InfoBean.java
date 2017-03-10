package gank.heht.com.mygankapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by hehaitao01 on 2017/3/7.
 */

public class InfoBean {


    /**
     * error : false
     * results : [{"_id":"58bd386e421aa95810795c10","createdAt":"2017-03-06T18:22:38.246Z","desc":"前端每周清单：Instant App将至，WebAssembly将获默认支持，PWA实践渐增","publishedAt":"2017-03-07T11:52:11.670Z","source":"chrome","type":"Android","url":"https://zhuanlan.zhihu.com/p/25597082","used":true,"who":"王下邀月熊"},{"_id":"58bd631b421aa90f0334512f","createdAt":"2017-03-06T21:24:43.452Z","desc":"Android Transition Framework详解","images":["http://img.gank.io/69f29004-2d49-4498-95ad-892bbd10a40e"],"publishedAt":"2017-03-07T11:52:11.670Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/e497123652b5","used":true,"who":null},{"_id":"58be22f6421aa90efc4fb680","createdAt":"2017-03-07T11:03:18.97Z","desc":"Android 查询高亮辅助组件","images":["http://img.gank.io/286cfe99-163d-4aec-9e0b-79ded55e40f8"],"publishedAt":"2017-03-07T11:52:11.670Z","source":"chrome","type":"Android","url":"https://github.com/cyrilmottier/QueryHighlighter","used":true,"who":"带马甲"},{"_id":"58be2323421aa90efc4fb681","createdAt":"2017-03-07T11:04:03.65Z","desc":"MD 风格引导页","images":["http://img.gank.io/e5393ebc-f930-4e24-87c2-690407f0af64"],"publishedAt":"2017-03-07T11:52:11.670Z","source":"chrome","type":"Android","url":"https://github.com/Vexigon/Material-Onboarding","used":true,"who":"带马甲"},{"_id":"58b7c6da421aa90f131785f0","createdAt":"2017-03-02T15:16:42.58Z","desc":"HorizontalPicker view 支持文本和icon","images":["http://img.gank.io/65953272-a41a-4cf4-85cd-3138edf99dab"],"publishedAt":"2017-03-06T11:17:33.140Z","source":"chrome","type":"Android","url":"https://github.com/GoodieBag/HorizontalPicker","used":true,"who":"Jason"},{"_id":"58bb931a421aa90efc4fb65b","createdAt":"2017-03-05T12:24:58.745Z","desc":"VirtualLayout是一个针对RecyclerView的LayoutManager扩展","publishedAt":"2017-03-06T11:17:33.140Z","source":"chrome","type":"Android","url":"https://github.com/alibaba/vlayout","used":true,"who":"Jason"},{"_id":"58bb93d9421aa90f0334511f","createdAt":"2017-03-05T12:28:09.166Z","desc":"Android Markdown 解析库，做的很棒，很有用。","images":["http://img.gank.io/2c55cb54-4321-4b62-8969-50fe15a42e55","http://img.gank.io/736520a8-ce61-4f1b-ad71-7773cc9a173b","http://img.gank.io/29c52aef-9fc2-45e5-828c-04705babb4f9"],"publishedAt":"2017-03-06T11:17:33.140Z","source":"chrome","type":"Android","url":"https://github.com/tiagohm/MarkdownView","used":true,"who":"Jason"},{"_id":"58bc2b27421aa90efc4fb663","createdAt":"2017-03-05T23:13:43.879Z","desc":"Java 语法清单","publishedAt":"2017-03-06T11:17:33.140Z","source":"chrome","type":"Android","url":"https://zhuanlan.zhihu.com/p/25578170","used":true,"who":"王下邀月熊"},{"_id":"58bcb243421aa95810795c02","createdAt":"2017-03-06T08:50:11.127Z","desc":"让 Android HTML 类支持显示更多 Tag，极大的丰富了 TextView 可以展示的 Tag 类型。","publishedAt":"2017-03-06T11:17:33.140Z","source":"chrome","type":"Android","url":"https://github.com/Pixplicity/HtmlCompat","used":true,"who":"代码家"},{"_id":"58bcb2a6421aa90efc4fb667","createdAt":"2017-03-06T08:51:50.505Z","desc":"虽然发了很多个 Pin 码效果的 UI 裤子，但是这个相对好看和正规一些。","images":["http://img.gank.io/e4bd928b-6702-4482-bfa9-0dce0cdad84a"],"publishedAt":"2017-03-06T11:17:33.140Z","source":"chrome","type":"Android","url":"https://github.com/GoodieBag/Pinview","used":true,"who":"带马甲"},{"_id":"58bcb303421aa90efc4fb668","createdAt":"2017-03-06T08:53:23.950Z","desc":"Material Design 风格的 Tag （Chip） 标签组件，漂亮，实用。","images":["http://img.gank.io/7100d74e-e7ee-4df6-acb8-d48d9c5a475c","http://img.gank.io/5eda65f8-cd5b-4ee0-9794-33e9ef148eee"],"publishedAt":"2017-03-06T11:17:33.140Z","source":"chrome","type":"Android","url":"https://github.com/robertlevonyan/materialChipView","used":true,"who":"代码家"},{"_id":"58b7f064421aa90efc4fb644","createdAt":"2017-03-02T18:13:56.571Z","desc":"浅谈RxJava与多线程并发","publishedAt":"2017-03-03T12:13:36.333Z","source":"web","type":"Android","url":"http://www.zjutkz.net/2017/02/09/%E6%B5%85%E8%B0%88RxJava%E4%B8%8E%E5%A4%9A%E7%BA%BF%E7%A8%8B%E5%B9%B6%E5%8F%91/","used":true,"who":null},{"_id":"58b8c1f6421aa90f131785f3","createdAt":"2017-03-03T09:08:06.355Z","desc":"一个相机的库","images":["http://img.gank.io/a79d1c20-6adc-4f30-8e4e-9ee68566bc24","http://img.gank.io/9bdab99c-9915-4f06-873c-5849f640ea64"],"publishedAt":"2017-03-03T12:13:36.333Z","source":"chrome","type":"Android","url":"https://github.com/flurgle/CameraKit-Android","used":true,"who":"花开堪折枝"},{"_id":"58b8c2b1421aa90f131785f5","createdAt":"2017-03-03T09:11:13.540Z","desc":"又一个高斯模糊的库子","images":["http://img.gank.io/4d82b2a7-a7f1-4d43-b9f8-f483ba8c9368"],"publishedAt":"2017-03-03T12:13:36.333Z","source":"chrome","type":"Android","url":"https://github.com/flurgle/BlurKit-Android","used":true,"who":"花开堪折枝"},{"_id":"58b8ec1b421aa90efc4fb64d","createdAt":"2017-03-03T12:07:55.251Z","desc":"注解方式来解析 html 节点","publishedAt":"2017-03-03T12:13:36.333Z","source":"chrome","type":"Android","url":"https://github.com/florent37/RxRetroJsoup","used":true,"who":"带马甲"},{"_id":"58b8ec60421aa95810795bf9","createdAt":"2017-03-03T12:09:04.797Z","desc":"插件化系列详解，附带 Demo。","images":["http://img.gank.io/5e83ea3a-89df-495b-b278-fd2644c477d0"],"publishedAt":"2017-03-03T12:13:36.333Z","source":"chrome","type":"Android","url":"https://github.com/ljqloveyou123/LiujiaqiAndroid","used":true,"who":"带马甲"},{"_id":"58b6657d421aa90efc4fb634","createdAt":"2017-03-01T14:09:01.716Z","desc":"仿iOS下载进度样式的按钮","images":["http://img.gank.io/299d3173-975c-4bea-b2a8-5080f7d324b7"],"publishedAt":"2017-03-02T11:51:29.672Z","source":"web","type":"Android","url":"https://github.com/jiang111/CProgressButton","used":true,"who":"NewTab"},{"_id":"58b79456421aa90f131785e8","createdAt":"2017-03-02T11:41:10.244Z","desc":"轻轻松松集成 Wechat 支付和支付宝支付。","publishedAt":"2017-03-02T11:51:29.672Z","source":"chrome","type":"Android","url":"https://github.com/mayubao/Android-Pay","used":true,"who":"带马甲"},{"_id":"58b4f56f421aa90f033450f6","createdAt":"2017-02-28T11:58:39.539Z","desc":"Kotlin 中有很多非常好的特性，扩展方法、伴生对象、原生支持动态代理、伪多继承 ","publishedAt":"2017-03-01T12:03:57.461Z","source":"web","type":"Android","url":"https://kymjs.com/code/2017/02/26/01/","used":true,"who":"张涛"},{"_id":"58b56370421aa90efc4fb624","createdAt":"2017-02-28T19:48:00.517Z","desc":"Android 应用层开发 Drawable 的一些叨叨絮","publishedAt":"2017-03-01T12:03:57.461Z","source":"web","type":"Android","url":"http://blog.csdn.net/yanbober/article/details/56844869","used":true,"who":"yanbo"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Parcelable {
        /**
         * _id : 58bd386e421aa95810795c10
         * createdAt : 2017-03-06T18:22:38.246Z
         * desc : 前端每周清单：Instant App将至，WebAssembly将获默认支持，PWA实践渐增
         * publishedAt : 2017-03-07T11:52:11.670Z
         * source : chrome
         * type : Android
         * url : https://zhuanlan.zhihu.com/p/25597082
         * used : true
         * who : 王下邀月熊
         * images : ["http://img.gank.io/69f29004-2d49-4498-95ad-892bbd10a40e"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.createdAt);
            dest.writeString(this.desc);
            dest.writeString(this.publishedAt);
            dest.writeString(this.source);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeByte(this.used ? (byte) 1 : (byte) 0);
            dest.writeString(this.who);
            dest.writeStringList(this.images);
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this._id = in.readString();
            this.createdAt = in.readString();
            this.desc = in.readString();
            this.publishedAt = in.readString();
            this.source = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.used = in.readByte() != 0;
            this.who = in.readString();
            this.images = in.createStringArrayList();
        }

        public static final Parcelable.Creator<ResultsBean> CREATOR = new Parcelable.Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel source) {
                return new ResultsBean(source);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };

        @Override
        public String toString() {
            return "ResultsBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    ", images=" + images +
                    '}';
        }
    }
}
