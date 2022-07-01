//import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Helper {
    public static void deleteAllFiles(String dir){
        File directory = new File(dir);
        FileUtils.cleanDirectory(directory);
    }
}
