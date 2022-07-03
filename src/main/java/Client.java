import org.apache.log4j.Logger;
import utilities.Log;

import java.io.File;
import java.io.FileInputStream;
import utilities.Log;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.io.PrintWriter;
import java.net.*;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class Client implements Runnable{
    public boolean reader = false;
    public boolean writer = false;
    public boolean sender = false;
    public static String localDir;
    public static String backupDir;
    private static final Logger logger = Log.getLogger("Client");
    public static Socket tcpSocket;
    public String threadName;

    public Client(String threadName){
        this.threadName = threadName;
    }

    @Override
    public void run(){
        if(this.threadName.equals("pollingAgent")){
            this.polling();
        }else if(this.threadName.equals("watcher")){
            try {
                this.watchOverFiles();
            }catch (Exception e){
                logger.error("Exception", e);
            }
        }
    }

    public void watchOverFiles() throws IOException, InterruptedException {

        WatchFolder watcher = new WatchFolder(localDir);
        logger.info("Watching directory for changes");
        // STEP4: Poll for events
        while (true) {
            watcher.watchFolder();
            if(watcher.newFile != null){
                logger.info("A new file is created : " + watcher.newFile);
                // Send the file over UDP
                watcher.newFile = null;
            }
            if(watcher.modifiedFile != null){
                logger.info("A new file is created : " + watcher.modifiedFile);
                // Send the modified blocks over UDP
                watcher.modifiedFile = null;
            }
            if(watcher.deletedFile != null){
                logger.info("A new file is created : " + watcher.deletedFile);
                // Send the modified blocks over UDP
                watcher.deletedFile = null;
            }
            TimeUnit.MILLISECONDS.sleep(100);
            boolean valid = watcher.watchKey.reset();
            if (!valid) {
                break;
            }
        }
    }
    public void polling(){
        logger.info("Polling the server started");
        logger.info("Polling the server Finished");
    }

    public void talkToServer(String command, Path filName){
        try {
            Scanner receiver = new Scanner(tcpSocket.getInputStream());
            PrintWriter sender = new PrintWriter(tcpSocket.getOutputStream(), true);
            String line = command+"#"+filName+"#STOP";
            // send the file name
            sender.println(line);
            // Print what the server said
            System.out.println(receiver.nextLine());
        }catch (Exception e){
            logger.error("Failed to chat", e);
        }
    }

    public static void main(String[] args) throws IOException {
        // Expectation is Client.class file will be executed with an identifier like 0, 1, 2 etc
        // And localDir will be decided using that client_no
        int clientNum = 1;// Integer.parseInt(args[0]);
        localDir = Constants.LOCAL_DIRS[0][clientNum];
        backupDir = Constants.LOCAL_DIRS[1][clientNum];
        // First cleanup the clients' local and backup dirs.
        Helper.deleteAllFiles(localDir, backupDir);
//        try{
//            InetAddress serverIp=InetAddress.getByName("localhost");
//            tcpSocket = new Socket(serverIp, Constants.SERVER_TCP_PORT);
//        }catch (Exception e){
//            logger.error("Failed to create client", e);
//        }
        Thread watcher = new Thread(new Client("watcher"));
        Thread pollingAgent = new Thread(new Client("pollingAgent"));

        watcher.start();
        pollingAgent.start();

    }

}
