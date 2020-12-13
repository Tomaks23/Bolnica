import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientHandler extends  Thread {


    final BufferedReader in;
    final PrintWriter out;
    final Socket s;


    public static AtomicInteger[] count = {new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0)};

    public ClientHandler(Socket s) throws IOException {
        this.s=s;
        in= new BufferedReader(new InputStreamReader(s.getInputStream()));
        out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())),true);
        start();
    }


    @Override
    public void run() {

        try {
            while (true) {


                String odgovor = in.readLine();      /*Prjem naziva sobe u kojoj je smesten novi pacijent */
                for (int i = 0; i < 4; i++) {
                    String odeljenje = Client.odeljenja[i];

                    if (odgovor.contains(odeljenje)) {// provera u koju sobu je smesten pacijent
                        if (count[i].get() < 10) { // provera da li ima slobono mesto u sobi
                            int nunOfPatients = count[i].incrementAndGet();//povecava broj pacijenata u sobi
                            out.println("Pacijent je smesten na odeljenje " + odeljenje + ". Ukupno: " + nunOfPatients);//šalje klijentu poruku o broju pacijenata u sobi
                            System.out.println("Pacijent je smesten na " + odeljenje + " ukupno pacijentata: "  + nunOfPatients);

                        } else {
                            System.out.println("Soba na odeljenju " + odeljenje + " je popunjena. Molimo sacekajte!");
                            out.println("Soba na odeljenju " + odeljenje + " je popunjena. Molimo sacekajte!"); // ako je soba popunjena ispisuje se ova poruka
                        }
                    }

                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println();
            try {
                in.close();
                out.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
