package com.dy.vam.tools;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.dy.vam.application.VAMApplication;

import java.io.File;

/**
 * 文件工具类
 */
public class FileUtil {
    public static final String ROOT = "DYDownLoad";
    public static final String DOWNLOAD = "download";
    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        }
        else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 检查文件是否存在
     */
    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    /**
     * 获取app下载的路径
     * @return
     */
    public static File getDownloadDir(){
        return getDir(DOWNLOAD);
    }

    public static File getDir(String cache) {
        StringBuilder path = new StringBuilder();
        if (isSDAvailable()) {
            path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            path.append(File.separator);// '/'
            path.append(ROOT);// /mnt/sdcard/GooglePlay
            path.append(File.separator);
            path.append(cache);// /mnt/sdcard/GooglePlay/cache
        }else{
            File filesDir = VAMApplication.getApplication().getCacheDir();    //  cache  getFileDir file
            path.append(filesDir.getAbsolutePath());// /data/data/com.itheima.googleplay/cache
            path.append(File.separator);///data/data/com.itheima.googleplay/cache/
            path.append(cache);///data/data/com.itheima.googleplay/cache/cache
        }
        String c=path.toString();
        File file = new File(c);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();// 创建文件夹
        }
        return file;
    }

    /**
     * 判断sd卡是否可用
     * @return
     */
    public static boolean isSDAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
