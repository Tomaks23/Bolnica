import java.util.Random;

public class Otpust extends Thread {
    private long min=10000,max=20000;
    private Random random = new Random();

    public Otpust(){start();}
    @Override
    public void run() {

        while (true) {

            try {
                // random broj od 5 do 10 (vreme za koje ce pacijent napustiti bolnicu)
                sleep(1000 * (random.nextInt((10 - 5) + 1) + 5));

                // rundom broj 0 and 4 (iz koje sobe izlazi pacijent)
                int count = random.nextInt(3 + 1);

                if (ClientHandler.count[count].get() > 0) {                                                             //proverava da li je soba prazna
                    System.out.println("Odeljenje broj " + (count + 1) + " (" + ClientHandler.odeljenja[count] + ")");
                    int numOfPatients = ClientHandler.count[count].decrementAndGet();                                   //novi ukupan broj pacijenata u sobi
                    System.out.println("Soba " + ClientHandler.odeljenja[count] + " se sada umanjuje za jednog pacijenta sada");
                    System.out.println("Soba " + ClientHandler.odeljenja[count] + " sada ima " + numOfPatients + " pacijenta");
                }

            } catch (InterruptedException e) {
            }
        }
    }
}
