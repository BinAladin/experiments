package cz.boucnikd.multithreadingconcurrencyperformance.hackers;

public class PoliceThread extends Thread{
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Game over for you hackers!");
        System.exit(0);
    }
}
