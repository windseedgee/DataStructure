package com.zhaipz.study.datastructure.service.gaopin;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

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

        String resM = multiply("0","3");
        log.info("乘积结果:{}",resM);

        log.info("ddd:{}",'2'*'3');
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


}
