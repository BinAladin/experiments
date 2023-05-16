package cz.boucnikd.multithreadingconcurrencyperformance.hackers;

public class AscendingHacker extends HackerThread {
    public AscendingHacker(Vault vault) {
        super(vault);
    }

    @Override
    public void run(){
        for (int currentPassword = 0; currentPassword < getVault().getMaxPassword(); currentPassword++) {
            if(getVault().isCorrectPassword(currentPassword)){
                System.out.println("Hacked by " + getName() + " ! Password is:" + currentPassword);
            }
        }
    }
}
