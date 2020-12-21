import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Server{


    public static void main(String[] args) throws IOException{
        ServerSocket listener = new ServerSocket(8005);
        new Otpust();

        while (true) {

            Socket s = listener.accept();   //provera konekcije Servera sa Klijentom
            System.out.println("Klijent je konektovan:" + s);
            new ClientHandler(s);



        }


    }

}