package team.bianming.javaapp.Util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by xiaopeng on 2017/9/14.
 */
public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+"/"+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
