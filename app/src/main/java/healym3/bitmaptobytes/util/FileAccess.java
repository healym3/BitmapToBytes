package healym3.bitmaptobytes.util;
import android.content.Context;
import android.util.Log;

import java.io.File;

public class FileAccess {

    public static File openFile(Context context){
        String[] files = context.fileList();
        for (String s: files
        ) {
            Log.d("FileList", "openFile: " + s);
        }
        File directory = context.getFilesDir();
        File file = new File(directory.getPath() + "/test1.bmp");

        Log.d("TAG", "openFile: " + directory.getPath());
        Log.d("TAG", "openFile: " + directory.getAbsolutePath());
        Log.d("TAG", "openFile: " + directory.getParent());
        Log.d("TAG", "openFile: " + directory.toURI());
        Log.d("isFile", "openFile: " + file.isFile());
        Log.d("canRead", "openFile: " + file.toURI());

        return file;
    }
}
