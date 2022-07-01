import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;
import java.io.PrintWriter;
import java.net.*;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class Client {
    public boolean reader = true;
    String localDir = null;

    public static void main(String[] args) {
        String localDir = null;
        switch(args[0]) {
            case "1":
                localDir = Constants.CLIENT_ONE_PATH;
                break;
            case "2":
                localDir = Constants.CLIENT_TWO_PATH;
                break;
            default:
                localDir = Constants.CLIENT_ONE_PATH;
        }
        try{
            InetAddress serverIp=InetAddress.getByName("localhost");
            Socket tcpSocket = new Socket(serverIp, Constants.SERVER_TCP_PORT);

            Scanner receiver = new Scanner(tcpSocket.getInputStream());
            PrintWriter sender = new PrintWriter(tcpSocket.getOutputStream(), true);
            String[] line = "ajdgasdhkjha\nausdhkadkahs\nasdgjagdjasgdjhgas\nasdgjhasgdjahgsd\nahsdgjsgadkaskdjh\nSTOP".split("\n");
            for (String words : line){
                sender.println(words);
                // Sleep for 2 seconds
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
