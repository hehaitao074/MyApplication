package gank.heht.com.mygankapplication.imgutils;

/**
 * Created by hehaitao01 on 2017/3/8.
 */

public class ImageRequestManager {

    public static final String type_Glide="Glide";
    public static final String type_XUtils="xUtils";
    public static final String type_default =type_XUtils;
    private ImageRequestManager(){
    }
    public static IimageListener getRequest(){
        return getRequest(type_default);
    }
    public static IimageListener getRequest(String type){
        switch (type){
            case type_XUtils:
                return new XutilsRequest();
            default:
                return new XutilsRequest();
        }
    }
}
