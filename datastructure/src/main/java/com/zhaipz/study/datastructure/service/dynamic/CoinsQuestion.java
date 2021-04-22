package com.zhaipz.study.datastructure.service.dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaipz
 * @ClassName: CoinsQuestion
 * @Description: 动态规划背包问题变种——零钱兑换
 * @date 2021/3/2 10:36
 */
public class CoinsQuestion {

    public static void main(String[] args) {
        int[] coins = new int[]{1,2,5};
        int coins1 = coinChange(coins,11);
        System.out.println(coins1);
        int coins2 = change(5,coins);
        System.out.println(coins2);
    }

    /**
    * @Title: 0-1背包问题
    * @Description: 零钱兑换
    * @inParam 金币面值，数额
    * @return 可以凑成总金额所需的最少的硬币个数
    * @throws
    */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int i =0;i<dp.length;i++){
            for(int coin : coins){
                if(i - coin < 0)continue;
                dp[i] = Math.min(dp[i],dp[i-coin] + 1);
            }
        }

        return dp[amount]==amount+1?-1:dp[amount];

    }

    /**
     * @Title: 完全背包问题
     * @Description: 零钱兑换
     * @inParam 金币面值，数额
     * @return 可以凑成总金额的硬币组合数
     * @throws
     */
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coin) {
                    dp[j] = dp[j] + dp[j - coin];
                }
            }
        }
        return dp[amount];
    }

}
