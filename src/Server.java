import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(8005);
        new Otpust();

        while (true) {

            Socket s = listener.accept();
            System.out.println("Klijent je konektovan:" + s);
            new ClientHandler(s);


        }


    }

}