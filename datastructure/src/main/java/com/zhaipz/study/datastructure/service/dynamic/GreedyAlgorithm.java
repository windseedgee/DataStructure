package com.zhaipz.study.datastructure.service.dynamic;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * @author zhaipz
 * @ClassName: GreedyAlgorithm
 * @Description: 贪心算法
 * @date 2021/4/23 16:05
 */
@Slf4j
public class GreedyAlgorithm {
    public static void main(String[] args) {
//        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//        Date date = new Date();
//        log.info("当前时间：{}",df.format(date).substring(0,4));
//        int[][] ins =  new int[][]{{1,2},{2,3},{3,4},{1,3}};
//        log.info("可移除区间的最小数量：{}",intervalSchedule(ins));

//        int[] nums = new int[]{3,2,1,0,4};
//        log.info("跳跃到最后一个位置：{}",canJump(nums));

        int[] nums = new int[]{2,3,1,1,4};
        log.info("最少跳跃次数：{}",Jump(nums));
    }

    /**
    * @Title: 区间调度问题
    * @Description: 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
    * @inParam 区间集合
    * @return 移除区间的最小数量
    * @throws
    */
    public static int intervalSchedule(int[][] intvs){
        if(intvs.length == 0)return 0;

        Arrays.sort(intvs, Comparator.comparingInt(o -> o[1]));

        int x_end = intvs[0][1];
        int count = 1;
        for (int[] intv : intvs){
            int start = intv[0];
            if(start >= x_end){
                count++;
                x_end = intv[1];
            }
        }
        return intvs.length-count;
    }

    /**
    * @Title: 跳跃问题
    * @Description: 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标。判断是否可到达最后一个下标
    * @inParam 数组nums
    * @return 能否到达最后一下下标
    * @throws
    */
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int fast = 0;
        for (int i = 0; i <n-1 ; i++) {
            fast = Math.max(fast,i + nums[i]);
            if(fast <= i){
                return false;
            }
        }
        return fast >= n-1;
    }

    /**
     * @Title: 跳跃问题
     * @Description: 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标。判断最少跳跃次数
     * @inParam 数组nums  假设给定数组总能跳到最后一个下标
     * @return 能否到达最后一下下标
     * @throws
     */
    public static int Jump(int[] nums) {
        int n = nums.length;
        int fast = 0;
        int end = 0;
        int jumps = 0;
        for (int i = 0; i <n-1 ; i++) {
            fast = Math.max(fast,i + nums[i]);
            if(end == i){
                jumps++;
                end = fast;
            }
        }
        return jumps;
    }
}
