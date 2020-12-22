import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client extends JFrame {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8005;
    public static Socket socket;

    static {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);


    Client() throws IOException {
    }

    JLabel ime = new JLabel("Ime:");
    JTextField unosIme = new JTextField(15);
    JLabel prezime = new JLabel("Prezime: ");
    JTextField unosPrezime = new JTextField(15);
    JLabel brknjizice = new JLabel("Broj Knjizice: ");
    JTextField unosbrknjizice = new JTextField(11);
    JLabel svestan = new JLabel("Svestan: ");
    JRadioButton da = new JRadioButton("Da");
    JRadioButton ne = new JRadioButton("Ne");
    ButtonGroup group = new ButtonGroup();
    JButton dugme = new JButton("Posalji");
    JLabel soba = new JLabel("Izaberite odeljenje:");
    JComboBox odeljenje = new JComboBox(ClientHandler.odeljenja);
    JTextArea server = new JTextArea();
    JButton proveraStanja = new JButton("Stanje na odeljenjima");

    public static void main(String[] args) throws IOException {
        new Client().Prozor();

    }

    public void Prozor() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setTitle("Bolnica");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        c.insets = new Insets(10, 5, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(ime, c);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(unosIme, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(prezime, c);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(unosPrezime, c);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(brknjizice, c);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(unosbrknjizice, c);

        group.add(da);
        group.add(ne);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(svestan, c);
        c.gridx = 1;
        c.gridy = 3;
        panel.add(da, c);
        c.gridx = 2;
        c.gridy = 3;
        panel.add(ne, c);

        c.gridx = 0;
        c.gridy = 4;
        panel.add(soba, c);
        c.gridx = 1;
        c.gridy = 4;
        panel.add(odeljenje, c);

        c.gridx = 1;
        c.gridy = 5;
        panel.add(dugme, c);

        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 3;
        server.setPreferredSize(new Dimension(350, 40));
        server.setEditable(false);
        panel.add(server, c);

        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        panel.add(proveraStanja, c);


        add(panel);


        dugme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == dugme) {
                    String sobastr = (String) odeljenje.getItemAt(odeljenje.getSelectedIndex());
                    out.println(sobastr);

                    unosIme.setText("");
                    unosPrezime.setText("");
                    unosbrknjizice.setText("");
                    group.clearSelection();

                }

                try {
                    String res1 = in.readLine();
                    String res2 = in.readLine();
                    server.setText(res1 + "\n" + res2);


                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        proveraStanja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == proveraStanja) {
                    out.println("Osvezi");
                    try {
                        server.setText(in.readLine());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }


            }
        });
        setVisible(true);
    }

}
