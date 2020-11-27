public class Otpust extends Thread {
    private int count;
    private long min=10000,max=20000;
    public Otpust(int count) {
        this.count=count;
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("odeljenje broj "+(count+10));
                ClientHandler.count[count]--;
                System.out.println("Soba "+ClientHandler.odeljenja[count]+" sada");
                System.out.println("sada ima "+ClientHandler.count[count]);
                sleep((long) Math.random() * (max - min + 1) + min);
            }
            } catch(InterruptedException e){
                e.printStackTrace();
            }

    }
}
