package com.zhaipz.study.datastructure.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhaipz
 * @ClassName: DoublePointer
 * @Description: 双指针技巧
 * @date 2021/1/19 14:28
 */
@Service
@Slf4j
public class DoublePointer {
    public static void main(String[] args) {
//        int[] nums = new int[]{0,1,2};
//        int x = threeSumClosest(nums,3);
//        System.out.println(x);
//        String s = "abcabcbb";
//        int l = lengthOfLongestSubstring(s);
//        System.out.println(x);

        log.info("最小覆盖子串：{}",minWindow("ADOBECODEBANC","ABC"));
        log.info("最小覆盖子串：{}",minWindow("ab","a"));
    }

    /**
    * @Title: 双指针
    * @Description: 两数之和
    * @inParam 数组 目标值
    * @return 下标
    * @throws
    */
    public static int[] twoSum(int[] nums,int target){
        Map<Integer,Integer> temp = new HashMap<>();
        int index;
        for (int i = 0;i<nums.length;i++){
            index = target - nums[i];
            if(temp.containsKey(index)){
                return new int[]{i,temp.get(index)};
            }
            temp.put(nums[i],i);
        }
        return new int[]{};
    }

    /**
     * @Title: 双指针
     * @Description: 两数之和
     * @inParam 有序数组 目标值
     * @return 下标
     * @throws
     */
    public static int[] twoSumOrder(int[] nums,int target){

        int left = 0;
        int right = nums.length-1;
        int sum;
        while(left < right){
            sum = nums[left] + nums[right];
            if(sum == target){
                return new int[]{left,right};
            }else  if (sum < target){
                ++left;
            }else {
                --right;
            }
        }
        return new int[]{};
    }
    /**
     * @Title: 双指针
     * @Description: 两数之和 找到符合的数对
     * @inParam 有序数组 目标值
     * @return 所有结果集
     * @throws
     */
    public static List<List<Integer>> pairSums(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length-1;
        int sum;
        while (left < right){
            sum = nums[left] + nums[right];
            if(sum == target){
                res.add(Arrays.asList(nums[left],nums[right]));
                ++left;
                --right;
            }else if (sum < target){
                ++left;
            }else {
                --right;
            }
        }
        return res;
    }
    /**
     * @Title: 双指针
     * @Description: 三数之和
     * @inParam 有序数组 目标值
     * @return 所有结果集
     * @throws
     */
    public static List<List<Integer>> threeSum(int[] nums,int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        int sum;
        for (int first = 0;first < n; first++){
            if(first > 0 && nums[first] == nums[first-1]){
                continue;
            }
            int second = first+1;
            int third = n-1;
            while (second < third){
                sum = nums[first] + nums[second] + nums[third];
                if(sum == target){
                    res.add(Arrays.asList(nums[first],nums[second],nums[third]));
                    while (second < third && nums[second] == nums[second+1])++second;
                    while (second < third && nums[third] == nums[third-1])--third;
                    ++second;
                    --third;
                }else if (sum < target){
                    ++second;
                }else {
                    --third;
                }
            }
        }
        return res;
    }
    /**
     * @Title: 双指针
     * @Description: 三数之和
     * @inParam 有序数组 目标值
     * @return 最接近目标值的和
     * @throws
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        int n = nums.length;
        int sum;
        for (int first = 0;first < n; first++){
            if(first > 0 && nums[first] == nums[first-1]){
                continue;
            }
            int second = first+1;
            int third = n-1;
            while (second < third){
                sum = nums[first] + nums[second] + nums[third];
                if(Math.abs(target-sum) < Math.abs(target - res)){
                    res = sum;
                }else if (sum < target){
                    ++second;
                }else if (sum > target){
                    --third;
                }else {
                    return res;
                }
            }
        }
        return res;
    }
    /**
     * @Title: 双指针
     * @Description: 接雨水
     * @inParam 柱子高度数组
     * @return 容量
     * @throws
     */
    public static int trap(int[] height) {
        int left = 0,right = height.length-1;
        int res = 0;
        int left_max = 0,right_max = 0;
        while (left < right){
            if(height[left] < height[right]){
                if(height[left] > left_max){
                    left_max = height[left];
                }else {
                    res += left_max-height[left];
                }
                ++left;
            }else {
                if (height[right] > right_max){
                    right_max = height[right];
                }else {
                    res += right_max-height[right];
                }
                --right;
            }
        }
        return res;
    }

    /**
     * @Title: 动态编程
     * @Description: 接雨水
     * @inParam 柱子高度数组
     * @return 容量
     * @throws
     */
    public static int trap2(int[] height) {
        if (height==null || height.length==0)return 0;
        int size = height.length;
        int[] left = new int[size];
        int[] right = new int[size];
        left[0] = height[0];
        right[size-1] = height[size-1];
        int res = 0;
        for (int i = 1;i<size;++i){
            left[i] = Math.max(height[i],left[i-1]);
        }
        for (int i = size-2;i>=0;--i){
            right[i] = Math.max(height[i],right[i+1]);
        }
        for (int i = 0;i<size;i++){
            res += Math.min(left[i],right[i])-height[i];
        }
        return res;
    }
    /**
     * @Title: 单调栈
     * @Description: 接雨水
     * @inParam 柱子高度数组
     * @return 容量
     * @throws
     */
    public static int trap3(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int res = 0,index = 0;
        while (index < height.length){
            while (!stack.isEmpty() && height[index] > height[stack.peek()]){
                int bottom = stack.pop();
                if (stack.isEmpty())break;
                int w = index-stack.peek()-1;
                int h = Math.min(height[index],height[stack.peek()]) - height[bottom];
                res += w*h;
            }
            stack.push(index++);
        }
        return res;
    }

    /**
     * @Title: 滑动窗口
     * @Description: 无重复字符的最长子串
     * @inParam 字符串
     * @return 无重复字符的最长子串长度
     * @throws
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> window = new HashMap<Character,Integer>();

        int left = 0,right = 0;
        int res = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            right++;
            window.put(c,window.getOrDefault(c, 0) + 1);
            
            while(window.get(c) > 1){
                char d = s.charAt(left);
                left++;
                window.put(d,window.getOrDefault(d, 0) - 1);
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    /**
     * @Title: 最小覆盖子串
     * @Description: 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * @inParam 字符串 s 、字符串 t
     * @return s覆盖t的最小子串  如果 s 中存在这样的子串，入参保证它是唯一的答案。
     * @throws
     */
    public static String minWindow(String s, String t) {

        Map<Character,Integer> window = new HashMap<Character,Integer>();
        Map<Character,Integer> need = new HashMap<Character,Integer>();
        for(char c : t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }
        int left = 0,right = 0;
        int vaild = 0;
        int start = 0,len = Integer.MAX_VALUE;
        while(right < s.length()){
            char c = s.charAt(right);
            right++;
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c).equals(need.get(c)))
                    vaild++;
            }

            while(vaild == need.size()){
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d)))
                        vaild--;
                        window.put(d,window.getOrDefault(d,0)-1);
                }
            }
        }
        log.info("开始索引：{}",start);
        log.info("长度：{}",len);
        return len == Integer.MAX_VALUE?"":s.substring(start,start + len);
    }
}
