package gank.heht.com.mygankapplication.utils;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hehaitao01 on 2017/3/15.
 */

public class FileHelper {

    private static final String filePath = "";

    private FileHelper() {
        throw new AssertionError();
    }

    /**
     * 读取 assets 文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String readAssetData(Context context, String fileName) {
        InputStream inStream = null;
        String data = null;
        try {
            inStream = context.getAssets().open(fileName);     //打开assets目录中的文本文件
            byte[] bytes = new byte[inStream.available()];  //inStream.available()为文件中的总byte数
            inStream.read(bytes);
            inStream.close();
            data = new String(bytes, "utf-8");        //将bytes转为utf-8字符串
        } catch (IOException e) {
            Logger.e(e.toString());
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 往内存写文件
     * @param context
     * @param fileName
     * @param content
     * @return
     */
    public static boolean writeStringData(Context context, String fileName, String content) {
        String filePath = context.getFilesDir() + "/" + fileName;
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            Logger.e(e.toString());
            e.printStackTrace();
        }
        return  true;
    }

    /**
     * 从内存读文件
     * @param context
     * @param fileName
     * @return
     */
    public static String readData(Context context, String fileName) {
        String filePath = context.getFilesDir() + "/" + fileName;
        InputStream inStream = null;
        String data = null;
        try {
            inStream = new FileInputStream(filePath)  ;
            byte[] bytes = new byte[inStream.available()];  //inStream.available()为文件中的总byte数
            inStream.read(bytes);
            inStream.close();
            data = new String(bytes, "utf-8");        //将bytes转为utf-8字符串
        } catch (IOException e) {
            Logger.e(e.toString());
            e.printStackTrace();
        }
        return data;
    }


}
