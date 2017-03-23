package gank.heht.com.mygankapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by long on 2016/8/26.
 * 整合专题和详情的新闻列表实体
 */
public class NewsItemInfo implements Parcelable {

    private String id;
    private String docID;
    private String type;
    private String href;
    private String postid;
    private int votecount;
    private int replyCount;
    private String tag;
    private String ltitle;
    private String digest;
    private String url;
    private String ipadcomment;
    private String docid;
    private String title;
    private String source;
    private String lmodify;
    private String imgsrc;
    private String ptime;
    private String skipType;
    private String specialID;

    public String getPhotosetID() {
        return photosetID;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    private String photosetID;

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getSpecialID() {
        return specialID;
    }

    public void setSpecialID(String specialID) {
        this.specialID = specialID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLtitle() {
        return ltitle;
    }

    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIpadcomment() {
        return ipadcomment;
    }

    public void setIpadcomment(String ipadcomment) {
        this.ipadcomment = ipadcomment;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.docID);
        dest.writeString(this.type);
        dest.writeString(this.href);
        dest.writeString(this.postid);
        dest.writeInt(this.votecount);
        dest.writeInt(this.replyCount);
        dest.writeString(this.tag);
        dest.writeString(this.ltitle);
        dest.writeString(this.digest);
        dest.writeString(this.url);
        dest.writeString(this.ipadcomment);
        dest.writeString(this.docid);
        dest.writeString(this.title);
        dest.writeString(this.source);
        dest.writeString(this.lmodify);
        dest.writeString(this.imgsrc);
        dest.writeString(this.ptime);
        dest.writeString(this.skipType);
        dest.writeString(this.specialID);
        dest.writeString(this.photosetID);
    }

    public NewsItemInfo() {
    }

    protected NewsItemInfo(Parcel in) {
        this.id = in.readString();
        this.docID = in.readString();
        this.type = in.readString();
        this.href = in.readString();
        this.postid = in.readString();
        this.votecount = in.readInt();
        this.replyCount = in.readInt();
        this.tag = in.readString();
        this.ltitle = in.readString();
        this.digest = in.readString();
        this.url = in.readString();
        this.ipadcomment = in.readString();
        this.docid = in.readString();
        this.title = in.readString();
        this.source = in.readString();
        this.lmodify = in.readString();
        this.imgsrc = in.readString();
        this.ptime = in.readString();
        this.skipType = in.readString();
        this.specialID = in.readString();
        this.photosetID = in.readString();
    }

    public static final Parcelable.Creator<NewsItemInfo> CREATOR = new Parcelable.Creator<NewsItemInfo>() {
        @Override
        public NewsItemInfo createFromParcel(Parcel source) {
            return new NewsItemInfo(source);
        }

        @Override
        public NewsItemInfo[] newArray(int size) {
            return new NewsItemInfo[size];
        }
    };
}
