package gank.heht.com.mygankapplication.bean;

import java.io.Serializable;

public class HuaBanMeiziInfo implements Serializable
{


    private static final long serialVersionUID = 7605292322439480858L;
    private String thumb;

    private String title;

    private String url;

    private int type;

    public String getThumb()
    {

        return thumb;
    }

    public void setThumb(String thumb)
    {

        this.thumb = thumb;
    }

    public String getTitle()
    {

        return title;
    }

    public void setTitle(String title)
    {

        this.title = title;
    }

    public String getUrl()
    {

        return url;
    }

    public void setUrl(String url)
    {

        this.url = url;
    }

    public int getType()
    {

        return type;
    }

    public void setType(int type)
    {

        this.type = type;
    }
}
