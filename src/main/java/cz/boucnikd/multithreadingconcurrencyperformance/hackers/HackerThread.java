package cz.boucnikd.multithreadingconcurrencyperformance.hackers;

public class HackerThread extends Thread {

    private final Vault vault;

    public HackerThread(Vault vault) {
        this.vault = vault;
        this.setName(getClass().getSimpleName());
        this.setPriority(MAX_PRIORITY);
    }

    @Override
    public void start(){
        System.out.println(Thread.currentThread().getName() + " is starting");
        super.start();
    }

    protected Vault getVault() {
        return vault;
    }
}
