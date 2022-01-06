package com.zhaipz.study.datastructure.service.gaopin;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author zhaipz
 * @ClassName: GaoPin
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2021/4/21 18:20
 */
@Slf4j
public class GaoPin {
    public static void main(String[] args) {
//        long res = solution(12,13,11);
//        log.info("吃的最多的那个人吃的数量:{}",res);

//        String resM = multiply("0","3");
//        log.info("乘积结果:{}",resM);

//        log.info("基本计算器结果:{}",calculate("(3 * (3+2) + (1+4))"));

        log.info("最长回文子串:{}",longestPalindrome("babad"));
    }

    /**
    * @Title: 吃葡萄
    * @Description: 有三种葡萄，每种分别有 a, b, c 颗，现在有三个人，第一个人只吃第一种和第二种葡萄，第二个人只吃第二种和第三种葡萄，第三个人只吃第一种和第三种葡萄。
    * @inParam 三种葡萄的数量
    * @return 吃的最多的人最少要吃多少颗葡萄。
    * @throws
    */
    public static long solution(long a, long b, long c){
        long[] nums = new long[]{a,b,c};
        Arrays.sort(nums);
        long sum = a + b + c;
        if(2 * (nums[0] + nums[1]) < nums[2]){
            return (nums[2] + 1)/2;
        }

        return (sum + 2)/3;
    }
    /**
     * @Title: 乘法
     * @Description: 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积
     * @inParam 非负整数 num1 和 num2
     * @return 字符串类型的乘积
     * @throws
     */
    public static String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        //乘积最多为m+n位
        int[] res = new int[m+n];

        for (int i = m-1; i >= 0 ; i--) {
            for (int j = n-1; j >= 0 ; j--) {
                int mul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                int p1 = i+j;
                int p2 = i+j+1;

                int sum = mul + res[p2];

                res[p2] = sum%10;
                res[p1] += sum/10;
            }
        }

        //结果可能存在以0开头的乘积
        int i = 0;
        while (i < res.length && res[i] == 0){
            i++;
        }

        StringBuilder str = new StringBuilder();
        for (;i<res.length;i++){
            str.append(res[i]);
        }

        return str.length() == 0?"0":String.valueOf(str);
    }

    /**
    * @Title: 基本计算器
    * @Description: 实现基本计算器  + - * / ( )
    * @inParam 字符串类型的表达式 有可能有空格
    * @return 计算结果
    * @throws
    */
    public static int calculate(String s) {
        return helper(s)[1];
    }

    public static int[] helper(String s){
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';
        int res = 0;
        int right = 0;
        int[] dp;

        for (int i = 0; i <s.length() ; i++) {
            char ch = s.charAt(i);
            //遇到数字
            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }
            //遇到左括号
            if(ch == '('){

                dp = helper(s.substring(i+1));
                i += dp[0];
                num = dp[1];

            }
            //遇到字符 忽略空格
            if((!Character.isDigit(ch) && ch != ' ') || i == s.length()-1){
                switch (sign){
                    case '+' :
                        stack.push(num);
                        break;
                    case '-' :
                        stack.push(-num);
                        break;
                    case '*' :
                        stack.push(stack.pop() * num);
                        break;
                    case '/' :
                        stack.push(stack.pop() / num);
                        break;
                }
                sign = ch;
                num = 0;
            }
            //遇到右括号 退出
            if(ch == ')'){
                right = i + 1;
                break;
            }
        }
        while (!stack.isEmpty()){
            res += stack.pop();
        }
        return new int[]{right,res};
    }

    /**
    * @Title: 最长回文子串
    * @Description: 给定一个字符串 s，找到 s 中最长的回文子串。
    * @inParam 字符串s
    * @return 最长回文子串
    * @throws
    */
    public static String longestPalindrome(String s) {

        int start = 0;
        int end = 0;
        for(int i = 0;i < s.length();i++){
            int len1 = palindrome(s,i,i);
            int len2 = palindrome(s,i,i+1);
            int len = Math.max(len1,len2);
            if(len > end - start){
                start = i - len/2;
                end = i + len/2;
            }
        }

        return s.substring(start,end+1);
    }

    public static int palindrome(String s,int l,int r){
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return r-l-1;
    }
}
