package cz.boucnikd.multithreadingconcurrencyperformance.hackers;

import java.util.Random;

public class RandomHacker extends HackerThread {
    public RandomHacker(Vault vault) {
        super(vault);
    }

    @Override
    public void run(){
        var random = new Random(1);
        int currentPassword;
        do{
            currentPassword = random.nextInt(getVault().getMaxPassword());
        }
        while(!getVault().isCorrectPassword(currentPassword));
        System.out.println("Hacked by " + getName() + " ! Password is:" + currentPassword);
    }
}
