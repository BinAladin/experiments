package cz.boucnikd.multithreadingconcurrencyperformance.hackers;

public class DescendingHacker extends HackerThread {
    public DescendingHacker(Vault vault) {
        super(vault);
    }

    @Override
    public void run(){
        for (int currentPassword = getVault().getMaxPassword(); currentPassword >= 0; currentPassword--) {
            if(getVault().isCorrectPassword(currentPassword)){
                System.out.println("Hacked by " + getName() + " ! Password is:" + currentPassword);
            }
        }
    }
}
