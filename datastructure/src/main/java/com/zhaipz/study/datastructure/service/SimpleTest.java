package com.zhaipz.study.datastructure.service;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author zhaipz
 * @ClassName: SimpleTest
 * @Description: 用于简单测试
 * @date 2021/6/9 10:13
 */
@Slf4j
public class SimpleTest {


    public static void main(String[] args) throws InterruptedException {
//        int x = 10;
//        int y = 1;
//        int[] d = new int[]{2,1,0,2,1,0};
//        String xx = "/a//b////c/d//././/..";
//        String xxx = simplifyPath(xx);
//        int[][] test = new int[1][1];
//        test[0][0] = 1;
//        colorChoose(d);
//
//        log.info("结果{}",d);
        int i = 0;
        i++;
        boolean[] res = new boolean[2];
        int a = Integer.MAX_VALUE,b = Integer.MAX_VALUE;

        System.out.println(Double.parseDouble("23800"));
    }

    public static String simplifyPath(String path) {
        Deque<String> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        String[] str = path.split("/");
        for(String ch : str){
            if(!ch.equals(".") && !ch.equals("..") && !ch.equals("")){
                queue.offer(ch);
            }else if(ch.equals("..") && !queue.isEmpty()){
                queue.pollLast();

            }
        }
        if(queue.isEmpty())return "/";
        while(!queue.isEmpty()){
            sb.append("/").append(queue.poll());
        }
        return sb.toString();
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int[] ints : matrix) {
            if (ints[0] <= target && target <= ints[n - 1]) {
                int left = 0;
                int right = n - 1;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (ints[mid] == target) {
                        return true;
                    } else if (ints[mid] < target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }

        return false;
    }

    public static void colorChoose(int[] colors){
        int p0 = 0;
        int p1 = 0;

        for(int i = 0;i < colors.length;i++){
            if(colors[i] == 1){
                int temp = colors[i];
                colors[i] = colors[p1];
                colors[p1] = temp;
                p1++;
            }
            if(colors[i] == 0){
                int temp = colors[i];
                colors[i] = colors[p0];
                colors[p0] = temp;
                if(p0 < p1){
                    temp = colors[i];
                    colors[i] = colors[p1];
                    colors[p1] = temp;
                }
                p0++;
                p1++;
            }
        }
    }

    public static int searchInsert(int[] nums, int target){
        int l = 0,r = nums.length;

        while(l < r){
            int mid = l + (r-l)/2;
            if(nums[mid] >= target){
                r = mid;
            }else{
                l = mid+1;
            }
        }
        return l;
    }

    public static int reverse(int a, int b ){
        StringBuilder as = new StringBuilder(String.valueOf(a)).reverse();
        StringBuilder bs = new StringBuilder(String.valueOf(b)).reverse();

        long maxA = Math.max(Long.parseLong(String.valueOf(as)), a);
        long maxB = Math.max(Long.parseLong(String.valueOf(bs)), b);
        if(maxA+maxB > Integer.MAX_VALUE)return Integer.MAX_VALUE;
        return (int) (maxA+maxB);
    }
public static int reversea(int a, int b ){

    int maxA = Math.max(reverseOne(a), a);
    int maxB = Math.max(reverseOne(b), b);
    return maxA+maxB;
}

    public static int reverseOne(int x){
        int ans = 0;

        while(x != 0) {
            // 判断溢出
            if(ans > Integer.MAX_VALUE/10) {
                return Integer.MAX_VALUE;
            }
            // ans*10 没有溢出
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        return ans;
    }

}
