package cz.boucnikd.multithreadingconcurrencyperformance.hackers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Vault {
    private final int password;
    private static final int MAX_PASSWORD = 9999;

    public boolean isCorrectPassword(int enteredPassword){
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return enteredPassword == password;
    }

    public static int getMaxPassword(){
        return MAX_PASSWORD;
    }
}
