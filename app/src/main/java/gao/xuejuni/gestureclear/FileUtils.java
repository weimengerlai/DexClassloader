package gao.xuejuni.gestureclear;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 作    者：高学军
 * 时    间：2018/6/7
 * 描    述：
 * 修改时间：
 */
public class FileUtils {

    public static void copyFiles(Context context, String fileName, File desFile) {
        InputStream in = null;
        OutputStream out = null;
        try {

            in = context.getApplicationContext().getAssets().open(fileName);
            out = new FileOutputStream(desFile.getAbsolutePath());
            byte[] bytes = new byte[1024];
            int i;
            while ((i = in.read(bytes)) != -1)
                out.write(bytes, 0 , i);
                Log.d("当前路径",i+"");

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("当前的路径",e.toString());
        }finally {

            Log.d("当前的路径","最后执行这里");
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static boolean hasExternalStorage() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取缓存路径
     *
     * @param context
     * @return 返回缓存文件路径
     */
    public static File getCacheDir(Context context) {
        File cache;
        if (hasExternalStorage()) {
            cache = context.getExternalCacheDir();
        } else {
            cache = context.getCacheDir();
        }
        if (!cache.exists())
            cache.mkdirs();
        return cache;
    }
}
