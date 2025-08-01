package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class MainTest1 {

    public static void main(String[] args) {
        MainTest1 obj = new MainTest1();
        obj.coinChange(new int[]{2}, 3);
    }


    public int coinChange(int[] coins, int amount) {

        int[] dpRem = new int[amount + 1];
        dpRem[0] = 0;

        for(int i = 1; i <= amount; i++){
            dpRem[i] = Integer.MAX_VALUE;
        }

        for(int a = 1; a <= amount; a++){
            for(int coin : coins){
                if(a - coin >= 0 && dpRem[a-coin] != Integer.MAX_VALUE){
                    dpRem[a] = Math.min(dpRem[a], 1 + dpRem[a-coin]);
                }
            }
        }
        return dpRem[amount]== Integer.MAX_VALUE ? -1 : dpRem[amount];
    }
}
