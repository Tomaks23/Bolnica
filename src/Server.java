import javax.swing.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, InvocationTargetException, InterruptedException {
        ServerSocket listener= new ServerSocket(100);


        while(true){
            Socket s = listener.accept();
            System.out.println("Klijent je konektovan:"+s);
            //SwingUtilities.invokeAndWait(new ClientHandler(s));
            ClientHandler t= new ClientHandler(s);

        }
    }

}
