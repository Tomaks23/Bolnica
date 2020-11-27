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
    public static int[] count={0,0,0,0};
    public static String odeljenja[]= {"Neurologija","Traumatologija","Oftamlogiuja","Kovid"};
    public int rand = (int) ((Math.random() * 3) + 1);;
    public ClientHandler(Socket s) throws IOException {
        this.s=s;
        in= new BufferedReader(new InputStreamReader(s.getInputStream()));
        out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())),true);
        start();
    }


    @Override
    public void run() {
        try {
//            while (true) {
//                String odgovor = in.readLine();      /*Prjem naziva sobe od Client klase*/
//                // System.out.println(odgovor);
//                String nov = "";
//
//                if (odgovor.contains("Traumatologija")) {
//                    tra++;
//                    if (tra <= 10) {
//                        nov = tra + "";
//                        out.println(nov);
//                    }  /*Salje se serveru koliko kreveta je zauzeto u sobi*/ else {
//                        out.println("Soba je popunjena. Sacekajte da se oslobodi mesto.");
//                        System.out.println("asd");
//                    }
//                } else if (odgovor.contains("Neurologija")) {
//                    neu++;
//                    nov = neu + "";
//                    out.println(nov);
//                    //   out.println(odgovor);
//                } else if (odgovor.contains("Oftamologija")) {
//                    oft++;
//                    nov = oft + "";
//                    out.println(nov);
//                } else if (odgovor.contains("Kovid")) {
//                    kov++;
//                    nov = kov + "";
//                    out.println(nov);
//                }
//                in.close();
//
//                out.close();
//                s.close();
//            }
            String odgovor = in.readLine();      /*Prjem naziva sobe od Client klase*/
//                // System.out.println(odgovor);
//                String nov = "";
            for (int i=0;i<4;i++){
                if (odgovor.contains(odeljenja[i])){
                    if (count[i]<10) {
                        count[i]++;
                        out.println("Pacijent je smesten na odeljenje "+odeljenja[i]);
                        System.out.println("Pacijent je smesten "+count[i]);

                    }else {
                        System.out.println("Soba je popunjena.Molimo vas sacekajte");
                    }
                }
            }
            Otpust os= new Otpust(rand);
            in.close();
            out.close();
            s.close();

            } catch(IOException e){
                e.printStackTrace();
            }

    }
}
