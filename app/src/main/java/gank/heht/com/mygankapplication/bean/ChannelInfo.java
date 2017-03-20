package gank.heht.com.mygankapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by hehaitao01 on 2017/3/17.
 */

public class ChannelInfo implements Parcelable {

    private List<NewsTypeInfo> my_channel;
    private List<NewsTypeInfo> other_channel;

    public List<NewsTypeInfo> getMy_channel() {
        return my_channel;
    }

    public void setMy_channel(List<NewsTypeInfo> my_channel) {
        this.my_channel = my_channel;
    }

    public List<NewsTypeInfo> getOther_channel() {
        return other_channel;
    }

    public void setOther_channel(List<NewsTypeInfo> other_channel) {
        this.other_channel = other_channel;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.my_channel);
        dest.writeTypedList(this.other_channel);
    }

    public ChannelInfo() {
    }

    protected ChannelInfo(Parcel in) {
        this.my_channel = in.createTypedArrayList(NewsTypeInfo.CREATOR);
        this.other_channel = in.createTypedArrayList(NewsTypeInfo.CREATOR);
    }

    public static final Parcelable.Creator<ChannelInfo> CREATOR = new Parcelable.Creator<ChannelInfo>() {
        @Override
        public ChannelInfo createFromParcel(Parcel source) {
            return new ChannelInfo(source);
        }

        @Override
        public ChannelInfo[] newArray(int size) {
            return new ChannelInfo[size];
        }
    };

    @Override
    public String toString() {
        return "ChannelInfo{" +
                "my_channel=" + my_channel +
                ", other_channel=" + other_channel +
                '}';
    }
}
