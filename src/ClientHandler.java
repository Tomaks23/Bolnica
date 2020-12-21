import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class ClientHandler extends Thread {


    final BufferedReader in;
    final PrintWriter out;
    final Socket s;
    static AtomicInteger[] count = {new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0)};
    public static String odeljenja[] = {"Neurologija", "Traumatologija", "Oftamologija", "Kovid"};


    public ClientHandler(Socket s) throws IOException {
        this.s = s;
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
        start();
    }


    @Override
    public void run() {


        try {
            while (true) {


                String odgovor = in.readLine();      /*Prjem naziva sobe u kojoj je smesten novi pacijent */
                int index = 0;
                while (index < odeljenja.length) {
                    if (odgovor.equals(odeljenja[index])) {
                        break;
                    }
                    index++;
                }
                if(index<odeljenja.length) {

                    String text = "";
                    if (count[index].get() < 10) {
                        int nunOfPatients = count[index].incrementAndGet();
                        text += "Pacijent je smesten! \n";
                        System.out.println("Pacijent je smesten na " + odgovor + " ukupno pacijentata: " + nunOfPatients);

                    } else {
                        text += "Soba je vec puna! \n";
                        System.out.println("Soba na odeljenju " + odgovor + " je popunjena. Molimo sacekajte!");
                    }

                    text += listaParova();
                    out.println(text);
                }else {

                    out.println(listaParova());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

    private String listaParova() {
        String result = "";
        for (int i = 0; i < odeljenja.length; i++) {
            result += odeljenja[i] + "- " + count[i].get();
            result += ", ";
        }
        return result;
    }

}
