//import java.io.IOException;
//import java.nio.file.*;
//
//public class WatchService {
////    public String targetDir = null;
////
////    public WatchService(String dir){
////        this.targetDir = dir;
////    }
//    // Code reference https://www.baeldung.com/java-nio2-watchservice
////    public void watchDir() throws IOException, InterruptedException {
////        WatchService watchService = (WatchService) FileSystems.getDefault().newWatchService();
////
////        Path path = Paths.get(System.getProperty(this.targetDir));
////
////        path.register(
////                (java.nio.file.WatchService) watchService,
////                StandardWatchEventKinds.ENTRY_CREATE,
////                StandardWatchEventKinds.ENTRY_DELETE,
////                StandardWatchEventKinds.ENTRY_MODIFY);
////
////        WatchKey key;
////        while ((key = ((java.nio.file.WatchService) watchService).take()) != null) {
////            for (WatchEvent<?> event : key.pollEvents()) {
////                System.out.println(
////                        "Event kind:" + event.kind()
////                                + ". File affected: " + event.context() + ".");
////            }
////            key.reset();
////        }
////    }
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        WatchService watchService = FileSystems.getDefault().newWatchService();
//
//        Path path = Paths.get(System.getProperty("C:\\Users\\Sagnik Hajra\\Desktop\\PPT\\New folder"));
//
//        path.register(
//                (java.nio.file.WatchService) watchService,
//                StandardWatchEventKinds.ENTRY_CREATE,
//                StandardWatchEventKinds.ENTRY_DELETE,
//                StandardWatchEventKinds.ENTRY_MODIFY);
//
//        WatchKey key;
//        while ((key = ((java.nio.file.WatchService) watchService).take()) != null) {
//            for (WatchEvent<?> event : key.pollEvents()) {
//                System.out.println(
//                        "Event kind:" + event.kind()
//                                + ". File affected: " + event.context() + ".");
//            }
//            key.reset();
//        }
//    }
////        WatchService srv = new WatchService("C:\\Users\\Sagnik Hajra\\Desktop\\PPT");
////        try {
////            srv.watchDir();
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//
//}

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import utilities.Log;

public class WatchFolder {
    public Path newFile=null;
    public Path modifiedFile=null;
    public Path deletedFile=null;
    public final WatchKey watchKey;
    public final WatchService watchService;
    public final Path directory;
    private static final Logger logger = Log.getLogger("Client");


    public WatchFolder(String dir) throws IOException {
        // STEP1: Get the path of the directory which you want to monitor.
        this.directory = Path.of(dir);
        // STEP2: Create a watch service
        this.watchService = FileSystems.getDefault().newWatchService();
        // STEP3: Register the directory with the watch service
        this.watchKey = directory.register(this.watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
    }
    public void watchFolder() {

        try {
//            while (true) {fileName
                for (WatchEvent<?> event : this.watchKey.pollEvents()) {
                    // STEP5: Get file name from even context
                    WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;

                    Path fileName = pathEvent.context();

                    // STEP6: Check type of event.
                    WatchEvent.Kind<?> kind = event.kind();

                    // STEP7: Perform necessary action with the event
                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        this.newFile = fileName;
                    }

                    if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                        this.deletedFile = fileName;
                    }
                    if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        this.modifiedFile = fileName;
                    }
                    TimeUnit.MILLISECONDS.sleep(5000);
                }
//                TimeUnit.MILLISECONDS.sleep(50);

                // STEP8: Reset the watch key everytime for continuing to use it for further event polling
//                boolean valid = watchKey.reset();
//                if (!valid) {
//                    break;
//                }

//            }

        } catch (Exception e) {
            logger.error("Unknown error", e);
        }

    }
//    public static void main(String[] args){
//        WatchFolder obj = new WatchFolder("C:\\Users\\Sagnik Hajra\\Desktop\\PPT\\New folder");
//        obj.watchFolder();
//    }

}