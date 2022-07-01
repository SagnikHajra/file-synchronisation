import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;



public class Server implements Runnable{
    Socket client;

    public Server() {}
    public Server(Socket client){
        this.client = client;
    }

    public void run(){
        System.out.println("\n>> new request from "+ this.client.getInetAddress() + " " + "Port: " + this.client.getPort() + " has been accepted");
        // initiate the Scanner and PrintWriter objects
        Scanner receiver = null;
        try {
            receiver = new Scanner(this.client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PrintWriter sender = new PrintWriter(this.client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get actionTypes from the received data
        String line = receiver.nextLine();
        String actiontype = "";
        while(!line.equals("STOP")){
            System.out.print(this.client.getPort() + ">> " + line + Constants.CRLF);
            line = receiver.nextLine();
        }
        System.out.println("\n>> All requests from "+ this.client.getInetAddress() +" have been completed");
    }

    public static void main(String[] args){
        ServerSocket socket=null;
        Socket client=null;

        try{
            socket = new ServerSocket(Constants.SERVER_TCP_PORT);
        }catch(IOException exc){
            System.out.println("\n>> Unable to setup port and start the server...");
            System.exit(1);
        }
        System.out.println("\n>> Server is listening to port " + Constants.SERVER_TCP_PORT);
        // Create thread pool
        Thread[] clientThreads = new Thread [10];
        int idx, i;
        while (true){
            idx = -1;
            // Check if any thread slot is empty
            for(i=0; i<10; i++){
                if (clientThreads[i]==null || !clientThreads[i].isAlive()) {
                    idx = i;
                    break;
                }
            }
            // If no thread slot is empty then wait for 10 seconds and continue
            if(idx==-1){
                try{
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            // When there is at least one empty thread slot.
            // Go ahead and Accept client request
            try {
                client = socket.accept();
            } catch (IOException exc){
                System.out.println("\n>> Fail to listen to requests!");
                System.exit(1);
            }
            clientThreads[idx] = new Thread(new Server(client));
            clientThreads[idx].start();
        }
    }
}

