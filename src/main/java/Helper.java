
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import utilities.Log;

public class Helper {
    private static final Logger logger = Log.getLogger("Helper");

    public static void deleteAllFiles(String... paths) throws IOException {
        for (String dir : paths) {
            File directory = new File(dir);
            try{
                assert directory.exists();
                File[] files = directory.listFiles();
                try {
                    assert files != null;
                    StringBuilder fileNames = new StringBuilder();
                    boolean res = false;
                    for (File file : files) {
                        res = file.delete();
                        if(res){fileNames.append(file);}
                    }
                    logger.info("Deletion complete");
                }catch (AssertionError e){
                    logger.info("directory "+ dir +" is empty no need to delete");
                }catch (Exception e){
                    logger.error("Unknown error", e);
                }
            }catch (AssertionError e){
                logger.fatal("directory "+ dir +" doesn't exist");
            }catch (Exception e){
                logger.error("Unknown error", e);
            }
        }
    }
}
