package com.zhaipz.study.datastructure.service.dynamic;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaipz
 * @ClassName: ClassicsDynamic
 * @Description: 经典动态规划问题
 * @date 2021/4/13 16:29
 */
@Slf4j
public class ClassicsDynamic {
    public static void main(String[] args) {
        String s = "aaa";
        String p = "a*a";
        boolean res = dp(s,0,p,0);
        System.out.println(res);
    }

    /**
    * @Title: 正则表达式
    * @Description: 正则表达式匹配 .匹配任意字符 *匹配零个或多个字符
    * @inParam s字符串 p匹配串
    * @return p是否可以匹配s boolean
    * @throws
    */
    public static Map<String,Boolean> memo = new HashMap<>();
    public static boolean dp(String s,int i,String p,int j){

        int m = s.length();
        int n = p.length();

        if(j == n){
            return i == m;
        }
        if(i == m){
            if((n-j)%2 == 1)return false;
            for(;j+1<n;j+=2){
                if(p.charAt(j+1) != '*'){
                    return false;
                }
            }
            return true;
        }
        //备忘录  记录每次索引组合的状态
        String key = i + "," + j;
        if(memo.containsKey(key))return memo.get(key);
        boolean res = false;

        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
            if(j<n-1 && p.charAt(j+1) == '*'){
                res = dp(s,i,p,j+2) || dp(s,i+1,p,j);
            }else{
                res = dp(s,i+1,p,j+1);
            }
        }else{
            if(j<n-1 && p.charAt(j+1) == '*'){
                res =  dp(s,i,p,j+2);
            }
        }

        memo.put(key,res);
        return res;
    }

    /**
     * @Title: 122. 买卖股票的最佳时机 I
     * @Description:
     * @inParam 第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * @return
     * @throws
     */
    public int maxProfit(int[] prices) {
        int dp0 = 0;
        int dp1 = Integer.MIN_VALUE;

        for (int price : prices) {
            dp0 = Math.max(dp0, dp1 + price);
            dp1 = Math.max(dp1, -price);
        }
        return dp0;
    }

    public int maxProfit(int[] prices,int fee) {
        int dp0 = 0;
        int dp1 = Integer.MIN_VALUE;

        for (int price : prices) {
            dp0 = Math.max(dp0, dp1 + price);
            dp1 = Math.max(dp1, dp0-fee-price);
        }
        return dp0;
    }

    /**
     * @Title: 198. 打家劫舍 I
     * @Description: 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * @inParam
     * @return
     * @throws
     */
    public int rob(int[] nums) {
        int dp = 0;
        int dp_1 = 0;
        int dp_2 = 0;

        for(int num : nums){
            dp = Math.max(dp_2+num,dp_1);
            dp_2 = dp_1;
            dp_1 = dp;
        }

        return dp;
    }
}
