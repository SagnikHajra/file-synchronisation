import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;
import java.io.PrintWriter;
import java.net.*;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class Client {

    public static void main(String[] args) {
        try{
            InetAddress serverIp=InetAddress.getByName("localhost");
            Socket tcpSocket = new Socket(serverIp, Constants.SERVER_TCP_PORT);

            Scanner receiver = new Scanner(tcpSocket.getInputStream());
            PrintWriter sender = new PrintWriter(tcpSocket.getOutputStream(), true);
            String[] line = "ajdgasdhkjha\nausdhkadkahs\nasdgjagdjasgdjhgas\nasdgjhasgdjahgsd\nahsdgjsgadkaskdjh\nSTOP".split("\n");
            for (String words : line){
                sender.println(words);
                // Sleep for 10 seconds
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
