import org.apache.log4j.Logger;

import java.io.File;

public class Helper {
    public static void deleteAllFiles(Logger logger, String... paths){
        for (String dir : paths) {
            File directory = new File(dir);
            try{
                assert directory.exists();
                File[] files = directory.listFiles();
                try {
                    assert files != null;
                    StringBuilder fileNames = new StringBuilder();
                    for (File file : files) {
                        logger.info("Deleting "+ file.toString());
                        file.delete();
                    }
                }catch (AssertionError | NullPointerException e){
                    logger.info("directory "+ dir +" is empty no need to delete");
                } catch (Exception e){
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
