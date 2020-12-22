import java.util.Random;

public class Otpust extends Thread {
    private long min = 10000, max = 20000;
    private Random random = new Random();

    public Otpust() {
        start();
    }

    @Override
    public void run() {

        while (true) {

            try {
                sleep(1000 * (random.nextInt((10 - 5) + 1) + 5));

                int count = random.nextInt(3 + 1);

                if (ClientHandler.count[count].get() > 0) {
                    System.out.println("Odeljenje broj " + (count + 1) + " (" + ClientHandler.odeljenja[count] + ")");
                    int numOfPatients = ClientHandler.count[count].decrementAndGet();
                    System.out.println("Soba " + ClientHandler.odeljenja[count] + " se sada umanjuje za jednog pacijenta sada");
                    System.out.println("Soba " + ClientHandler.odeljenja[count] + " sada ima " + numOfPatients + " pacijenta");
                }

            } catch (InterruptedException e) {
            }
        }
    }
}
