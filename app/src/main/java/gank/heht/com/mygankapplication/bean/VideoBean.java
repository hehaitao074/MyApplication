package gank.heht.com.mygankapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hehaitao01 on 2017/3/15.
 */

public class VideoBean implements Parcelable {

    /**
     * topicImg : http://vimg1.ws.126.net/image/snapshot/2017/2/B/P/VCCFUJKBP.jpg
     * videosource : 新媒体
     * mp4Hd_url : null
     * topicDesc : 专注于搞笑类的领域，每天为你更新搞笑文章视频，为你的生活带来欢乐
     * topicSid : VCCFUJKBD
     * cover : http://vimg1.ws.126.net/image/snapshot/2017/3/M/V/VCEL7P0MV.jpg
     * title : 哄女生开心的“包治百病”升级版
     * playCount : 0
     * replyBoard : video_bbs
     * videoTopic : {"alias":"每天为你更新搞笑文章视频","tname":"搞笑奇趣录","ename":"T1487170967826","tid":"T1487170967826","topic_icons":"http://dingyue.nosdn.127.net/8VFkl1wxTWQL1BvHC2rtjqRyrDn4SP2jflhscxpRJlCeM1487170967512.jpg"}
     * sectiontitle :
     * replyid : CEL7P0MU008535RB
     * description :
     * mp4_url : http://flv2.bn.netease.com/videolib3/1703/15/MovqI1977/SD/MovqI1977-mobile.mp4
     * length : 47
     * playersize : 1
     * m3u8Hd_url : null
     * vid : VCEL7P0MU
     * m3u8_url : http://flv2.bn.netease.com/videolib3/1703/15/MovqI1977/SD/movie_index.m3u8
     * ptime : 2017-03-15 15:14:08
     * topicName : 搞笑奇趣录
     */

    private String topicImg;
    private String videosource;
    private String mp4Hd_url;
    private String topicDesc;
    private String topicSid;
    private String cover;
    private String title;
    private int playCount;
    private String replyBoard;
    private VideoTopicBean videoTopic;
    private String sectiontitle;
    private String replyid;
    private String description;
    private String mp4_url;
    private int length;
    private int playersize;
    private String m3u8Hd_url;
    private String vid;
    private String m3u8_url;
    private String ptime;
    private String topicName;

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public String getVideosource() {
        return videosource;
    }

    public void setVideosource(String videosource) {
        this.videosource = videosource;
    }

    public String getMp4Hd_url() {
        return mp4Hd_url;
    }

    public void setMp4Hd_url(String mp4Hd_url) {
        this.mp4Hd_url = mp4Hd_url;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public String getTopicSid() {
        return topicSid;
    }

    public void setTopicSid(String topicSid) {
        this.topicSid = topicSid;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public String getReplyBoard() {
        return replyBoard;
    }

    public void setReplyBoard(String replyBoard) {
        this.replyBoard = replyBoard;
    }

    public VideoTopicBean getVideoTopic() {
        return videoTopic;
    }

    public void setVideoTopic(VideoTopicBean videoTopic) {
        this.videoTopic = videoTopic;
    }

    public String getSectiontitle() {
        return sectiontitle;
    }

    public void setSectiontitle(String sectiontitle) {
        this.sectiontitle = sectiontitle;
    }

    public String getReplyid() {
        return replyid;
    }

    public void setReplyid(String replyid) {
        this.replyid = replyid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPlayersize() {
        return playersize;
    }

    public void setPlayersize(int playersize) {
        this.playersize = playersize;
    }

    public String getM3u8Hd_url() {
        return m3u8Hd_url;
    }

    public void setM3u8Hd_url(String m3u8Hd_url) {
        this.m3u8Hd_url = m3u8Hd_url;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getM3u8_url() {
        return m3u8_url;
    }

    public void setM3u8_url(String m3u8_url) {
        this.m3u8_url = m3u8_url;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public static class VideoTopicBean implements Parcelable {
        /**
         * alias : 每天为你更新搞笑文章视频
         * tname : 搞笑奇趣录
         * ename : T1487170967826
         * tid : T1487170967826
         * topic_icons : http://dingyue.nosdn.127.net/8VFkl1wxTWQL1BvHC2rtjqRyrDn4SP2jflhscxpRJlCeM1487170967512.jpg
         */

        private String alias;
        private String tname;
        private String ename;
        private String tid;
        private String topic_icons;

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getTopic_icons() {
            return topic_icons;
        }

        public void setTopic_icons(String topic_icons) {
            this.topic_icons = topic_icons;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.alias);
            dest.writeString(this.tname);
            dest.writeString(this.ename);
            dest.writeString(this.tid);
            dest.writeString(this.topic_icons);
        }

        public VideoTopicBean() {
        }

        protected VideoTopicBean(Parcel in) {
            this.alias = in.readString();
            this.tname = in.readString();
            this.ename = in.readString();
            this.tid = in.readString();
            this.topic_icons = in.readString();
        }

        public static final Creator<VideoTopicBean> CREATOR = new Creator<VideoTopicBean>() {
            @Override
            public VideoTopicBean createFromParcel(Parcel source) {
                return new VideoTopicBean(source);
            }

            @Override
            public VideoTopicBean[] newArray(int size) {
                return new VideoTopicBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.topicImg);
        dest.writeString(this.videosource);
        dest.writeString(this.mp4Hd_url);
        dest.writeString(this.topicDesc);
        dest.writeString(this.topicSid);
        dest.writeString(this.cover);
        dest.writeString(this.title);
        dest.writeInt(this.playCount);
        dest.writeString(this.replyBoard);
        dest.writeParcelable(this.videoTopic, flags);
        dest.writeString(this.sectiontitle);
        dest.writeString(this.replyid);
        dest.writeString(this.description);
        dest.writeString(this.mp4_url);
        dest.writeInt(this.length);
        dest.writeInt(this.playersize);
        dest.writeString(this.m3u8Hd_url);
        dest.writeString(this.vid);
        dest.writeString(this.m3u8_url);
        dest.writeString(this.ptime);
        dest.writeString(this.topicName);
    }

    public VideoBean() {
    }

    protected VideoBean(Parcel in) {
        this.topicImg = in.readString();
        this.videosource = in.readString();
        this.mp4Hd_url = in.readString();
        this.topicDesc = in.readString();
        this.topicSid = in.readString();
        this.cover = in.readString();
        this.title = in.readString();
        this.playCount = in.readInt();
        this.replyBoard = in.readString();
        this.videoTopic = in.readParcelable(VideoTopicBean.class.getClassLoader());
        this.sectiontitle = in.readString();
        this.replyid = in.readString();
        this.description = in.readString();
        this.mp4_url = in.readString();
        this.length = in.readInt();
        this.playersize = in.readInt();
        this.m3u8Hd_url = in.readString();
        this.vid = in.readString();
        this.m3u8_url = in.readString();
        this.ptime = in.readString();
        this.topicName = in.readString();
    }

    public static final Parcelable.Creator<VideoBean> CREATOR = new Parcelable.Creator<VideoBean>() {
        @Override
        public VideoBean createFromParcel(Parcel source) {
            return new VideoBean(source);
        }

        @Override
        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };
}
