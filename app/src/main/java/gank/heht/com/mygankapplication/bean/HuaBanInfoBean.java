package gank.heht.com.mygankapplication.bean;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by hehaitao01 on 2017/3/13.
 */

public class HuaBanInfoBean  implements Serializable{
    private static final long serialVersionUID = -4027409272054727007L;
    @SerializedName("showapi_res_code")
    public int code;

    @SerializedName("showapi_res_error")
    public String error;

    @SerializedName("showapi_res_body")
    public JsonObject list;

    public List<HuaBanMeiziInfo> infos;



    /**
     * 解析json返回的数据 拼接为集合
     *
     * @param json
     * @return
     */
    public static HuaBanInfoBean createFromJson(String json)
    {

        HuaBanInfoBean result = new Gson().fromJson(json, HuaBanInfoBean.class);
        Iterator<Map.Entry<String,JsonElement>> iterator = result.list.entrySet().iterator();
        if (result.infos == null)
        {
            result.infos = new ArrayList<>();
        }
        while (iterator.hasNext())
        {
            Map.Entry<String,JsonElement> element = iterator.next();
            try
            {
                result.infos.add(new Gson().fromJson(element.getValue(), HuaBanMeiziInfo.class));
            } catch (Exception e)
            {

            }
        }

        result.list = null;

        return result;
    }
}
