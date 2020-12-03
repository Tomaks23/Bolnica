import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {


    final BufferedReader in;
    final PrintWriter out;
    final Socket s;
    public static int[] count = {0, 0, 0, 0};
  //  public static String odeljenja[] = {"Neurologija", "Traumatologija", "Oftamlogiuja", "Kovid"};
//public int rand = (int) ((Math.random() * 3) + 1);
    public ClientHandler(Socket s) throws IOException {
        this.s=s;
        in= new BufferedReader(new InputStreamReader(s.getInputStream()));
        out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())),true);
        start();
    }


    @Override
    public void run() {
        try {
            String odgovor = in.readLine();      /*Prjem naziva sobe od Client klase*/
            for (int i=0;i<4;i++){
                if (odgovor.contains(Client.odeljenja[i])){
                    if (count[i]<10) {
                        count[i]++;
                        out.println("Pacijent je smesten na odeljenje "+Client.odeljenja[i]);
                        System.out.println("Pacijent je smesten "+count[i]);

                    }else {
                        System.out.println("Soba je popunjena.Molimo vas sacekajte");
                    }
                }

            }
            System.out.println();
         //   Otpust os= new Otpust();
            in.close();
            out.close();
            s.close();

            } catch(IOException e){
                e.printStackTrace();
            }

    }
}
