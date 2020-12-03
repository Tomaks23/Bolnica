public class Otpust extends Thread {
    private long min=10000,max=20000;
    public Otpust() {
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                int count = (int) ((Math.random() * 3));;
                System.out.println("Odeljenje broj "+(count+1));
                ClientHandler.count[count]--;
                System.out.println("Soba "+Client.odeljenja[count]+" se sada umanjuje za jednog pacijenta sada");
                System.out.println("Soba "+Client.odeljenja[count]+" sada ima "+ClientHandler.count[count]+" pacijenta");
                sleep((long) Math.random() * (max - min + 1) + min);
                System.out.println();
            }
        } catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
