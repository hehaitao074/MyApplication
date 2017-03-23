package gank.heht.com.mygankapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hehaitao01 on 2017/3/13.
 */

public class TaoNvListBean implements Parcelable {


    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean implements Parcelable {

        private int ret_code;
        private PagebeanBean pagebean;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public PagebeanBean getPagebean() {
            return pagebean;
        }

        public void setPagebean(PagebeanBean pagebean) {
            this.pagebean = pagebean;
        }

        public static class PagebeanBean implements Parcelable {


            private int allPages;
            private int currentPage;
            private int allNum;
            private int maxResult;
            private List<ContentlistBean> contentlist;

            public int getAllPages() {
                return allPages;
            }

            public void setAllPages(int allPages) {
                this.allPages = allPages;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getAllNum() {
                return allNum;
            }

            public void setAllNum(int allNum) {
                this.allNum = allNum;
            }

            public int getMaxResult() {
                return maxResult;
            }

            public void setMaxResult(int maxResult) {
                this.maxResult = maxResult;
            }

            public List<ContentlistBean> getContentlist() {
                return contentlist;
            }

            public void setContentlist(List<ContentlistBean> contentlist) {
                this.contentlist = contentlist;
            }

            public static class ContentlistBean implements Parcelable {


                private int totalFavorNum;
                private String link;
                private String userId;
                private String avatarUrl;
                private String realName;
                private String type;
                private int totalFanNum;
                private String cardUrl;
                private String city;
                private List<String> imgList;

                public int getTotalFavorNum() {
                    return totalFavorNum;
                }

                public void setTotalFavorNum(int totalFavorNum) {
                    this.totalFavorNum = totalFavorNum;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getAvatarUrl() {
                    return avatarUrl;
                }

                public void setAvatarUrl(String avatarUrl) {
                    this.avatarUrl = avatarUrl;
                }

                public String getRealName() {
                    return realName;
                }

                public void setRealName(String realName) {
                    this.realName = realName;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public int getTotalFanNum() {
                    return totalFanNum;
                }

                public void setTotalFanNum(int totalFanNum) {
                    this.totalFanNum = totalFanNum;
                }

                public String getCardUrl() {
                    return cardUrl;
                }

                public void setCardUrl(String cardUrl) {
                    this.cardUrl = cardUrl;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public List<String> getImgList() {
                    return imgList;
                }

                public void setImgList(List<String> imgList) {
                    this.imgList = imgList;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.totalFavorNum);
                    dest.writeString(this.link);
                    dest.writeString(this.userId);
                    dest.writeString(this.avatarUrl);
                    dest.writeString(this.realName);
                    dest.writeString(this.type);
                    dest.writeInt(this.totalFanNum);
                    dest.writeString(this.cardUrl);
                    dest.writeString(this.city);
                    dest.writeStringList(this.imgList);
                }

                public ContentlistBean() {
                }

                protected ContentlistBean(Parcel in) {
                    this.totalFavorNum = in.readInt();
                    this.link = in.readString();
                    this.userId = in.readString();
                    this.avatarUrl = in.readString();
                    this.realName = in.readString();
                    this.type = in.readString();
                    this.totalFanNum = in.readInt();
                    this.cardUrl = in.readString();
                    this.city = in.readString();
                    this.imgList = in.createStringArrayList();
                }

                public static final Creator<ContentlistBean> CREATOR = new Creator<ContentlistBean>() {
                    @Override
                    public ContentlistBean createFromParcel(Parcel source) {
                        return new ContentlistBean(source);
                    }

                    @Override
                    public ContentlistBean[] newArray(int size) {
                        return new ContentlistBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.allPages);
                dest.writeInt(this.currentPage);
                dest.writeInt(this.allNum);
                dest.writeInt(this.maxResult);
                dest.writeList(this.contentlist);
            }

            public PagebeanBean() {
            }

            protected PagebeanBean(Parcel in) {
                this.allPages = in.readInt();
                this.currentPage = in.readInt();
                this.allNum = in.readInt();
                this.maxResult = in.readInt();
                this.contentlist = new ArrayList<ContentlistBean>();
                in.readList(this.contentlist, ContentlistBean.class.getClassLoader());
            }

            public static final Creator<PagebeanBean> CREATOR = new Creator<PagebeanBean>() {
                @Override
                public PagebeanBean createFromParcel(Parcel source) {
                    return new PagebeanBean(source);
                }

                @Override
                public PagebeanBean[] newArray(int size) {
                    return new PagebeanBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.ret_code);
            dest.writeParcelable(this.pagebean, flags);
        }

        public ShowapiResBodyBean() {
        }

        protected ShowapiResBodyBean(Parcel in) {
            this.ret_code = in.readInt();
            this.pagebean = in.readParcelable(PagebeanBean.class.getClassLoader());
        }

        public static final Creator<ShowapiResBodyBean> CREATOR = new Creator<ShowapiResBodyBean>() {
            @Override
            public ShowapiResBodyBean createFromParcel(Parcel source) {
                return new ShowapiResBodyBean(source);
            }

            @Override
            public ShowapiResBodyBean[] newArray(int size) {
                return new ShowapiResBodyBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.showapi_res_code);
        dest.writeString(this.showapi_res_error);
        dest.writeParcelable(this.showapi_res_body, flags);
    }

    public TaoNvListBean() {
    }

    protected TaoNvListBean(Parcel in) {
        this.showapi_res_code = in.readInt();
        this.showapi_res_error = in.readString();
        this.showapi_res_body = in.readParcelable(ShowapiResBodyBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<TaoNvListBean> CREATOR = new Parcelable.Creator<TaoNvListBean>() {
        @Override
        public TaoNvListBean createFromParcel(Parcel source) {
            return new TaoNvListBean(source);
        }

        @Override
        public TaoNvListBean[] newArray(int size) {
            return new TaoNvListBean[size];
        }
    };
}
