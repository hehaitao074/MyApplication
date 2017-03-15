package gank.heht.com.mygankapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hehaitao01 on 2017/3/15.
 */

public class NewsTypeInfo implements Parcelable {

    /**
     * name : 头条
     * typeId : T1348647909107
     * url : http://c.m.163.com/nc/article/headline/{0}/{1}-20.html
     */

    private String name;
    private String typeId;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.typeId);
        dest.writeString(this.url);
    }

    public NewsTypeInfo() {
    }

    protected NewsTypeInfo(Parcel in) {
        this.name = in.readString();
        this.typeId = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<NewsTypeInfo> CREATOR = new Parcelable.Creator<NewsTypeInfo>() {
        @Override
        public NewsTypeInfo createFromParcel(Parcel source) {
            return new NewsTypeInfo(source);
        }

        @Override
        public NewsTypeInfo[] newArray(int size) {
            return new NewsTypeInfo[size];
        }
    };
}
