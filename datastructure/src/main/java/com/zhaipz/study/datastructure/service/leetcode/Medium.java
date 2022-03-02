package com.zhaipz.study.datastructure.service.leetcode;


import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhaipz
 * @ClassName: Medium
 * @Description: LeetCode中等题合集
 * @date 2022/3/2 16:26
 */
public class Medium {

    /**
     * @Title: 19. 删除链表的倒数第 N 个结点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = new ListNode();
        temp.next = head;
        ListNode fast = head;
        ListNode slow = temp;

        while(n != 0){
            fast = fast.next;
            n--;
        }

        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return temp.next;
    }

    /**
     * @Title: 22. 括号生成
     */
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        getParenthesis(n,n,new StringBuilder());
        return res;
    }

    public void getParenthesis(int l,int r,StringBuilder path){
        if(l == 0 && r == 0){
            res.add(new String(path));
            return;
        }
        if(l < 0 || l > r)return;

        path.append("(");
        getParenthesis(l-1,r,path);
        path.deleteCharAt(path.length()-1);

        path.append(")");
        getParenthesis(l,r-1,path);
        path.deleteCharAt(path.length()-1);
    }

    /**
     * @Title: 24. 两两交换链表中的节点
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)return head;

        ListNode temp = head.next;
        head.next = swapPairs(temp.next);
        temp.next = head;

        return temp;
    }

    /**
     * @Title: 31. 下一个排列
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length-2;
        while(i >= 0 && nums[i] >= nums[i+1]){
            i--;
        }
        int j = nums.length-1;
        if(i >= 0){
            while(j >= 0 && nums[j] <= nums[i]){
                j--;
            }
            swap(nums,i,j);
        }
        reverse(nums,i+1);
    }

    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void reverse(int[] nums,int l){
        int r = nums.length-1;
        while(l < r){
            swap(nums,l,r);
            l++;
            r--;
        }
    }

    /**
     * @Title: 33. 搜索旋转排序数组
     */
    public int searchArray(int[] nums, int target) {
        int l = 0,r = nums.length-1;

        while(l <= r){
            int mid = l + (r-l)/2;
            if(nums[mid] == target)return mid;

            if(nums[l] <= nums[mid]){
                if(nums[l] <= target && target < nums[mid]){
                    r = mid-1;
                }else {
                    l = mid+1;
                }
            }else {
                if(nums[mid] < target && target <= nums[r]){
                    l = mid+1;
                }else {
                    r = mid-1;
                }
            }
        }
        return -1;
    }

    /**
     * @Title: 33. 搜索出现的第一个位置和最后一个位置
     */
    public int[] searchRange(int[] nums, int target) {
        int l = search(nums,target);
        int r = search(nums,target+1);

        if(l == nums.length || nums[l] != target)return new int[]{-1,-1};

        return new int[]{l,r-1};
    }
    public int search(int[] nums,int target){
        int l = 0,r = nums.length;

        while(l < r){
            int mid = l + (r-l)/2;
            if(nums[mid] < target){
                l = mid+1;
            }else {
                r = mid;
            }
        }
        return l;
    }

    /**
     * @Title: 45.跳跃游戏Ⅱ  最少的跳跃次数到达数组的最后一个位置。
     */
    public int jump(int[] nums) {
        int len = 0;
        int jumps = 0;
        int end = 0;

        for (int i = 0; i < nums.length-1; i++) {
            len = Math.max(len,i+nums[i]);
            if(i == end){
                jumps++;
                end = len;
            }
        }

        return jumps;
    }

    /**
     * @Title: 46.全排列
     */
    List<List<Integer>> p = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        getPermute(nums,new ArrayList<>());
        return p;
    }
    public void getPermute(int[] nums,List<Integer> path){
        if(path.size() == nums.length){
            p.add(new ArrayList<Integer>(path));
            return;
        }

        for (int num : nums) {
            if (path.contains(num)) continue;

            path.add(num);
            getPermute(nums, path);
            path.remove(path.size() - 1);
        }
    }

    /**
     * @Title: 47.全排列Ⅱ 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     */
    boolean[] visit;
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        visit = new boolean[nums.length];
        getPermuteUnique(nums,new ArrayList<>());
        return p;
    }
    public void getPermuteUnique(int[] nums,List<Integer> path){
        if(path.size() == nums.length){
            p.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0;i < nums.length;i++) {
            if(visit[i] || (i > 0 && nums[i-1] == nums[i] && !visit[i-1]))continue;

            path.add(nums[i]);
            visit[i] = true;
            getPermuteUnique(nums, path);
            visit[i] = false;
            path.remove(path.size() - 1);
        }
    }

    /**
     * @Title: 48. 旋转图像
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for(int i = 0;i < n/2;i++){
            for(int j = 0;j < (n+1)/2;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = temp;
            }
        }
    }

    /**
     * @Title: 55. 跳跃游戏  是否能够到达最后一个下标
     */
    public boolean canJump(int[] nums) {
        int len = 0;
        for(int i = 0;i < nums.length-1;i++){
            len = Math.max(len,i+nums[i]);
            if(i >= len){
                return false;
            }
        }
        return true;
    }

    /**
     * @Title: 56. 合并区间
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
        List<int[]> res = new ArrayList<>();

        int i = 0;
        while(i < intervals.length){
            int l = intervals[i][0];
            int r = intervals[i][1];

            while(i < intervals.length-1 && intervals[i+1][0] <= r){
                r = Math.max(r,intervals[i+1][1]);
                i++;
            }
            res.add(new int[]{l,r});
            i++;
        }

        return res.toArray(new int[0][]);
    }

    /**
     * @Title: 57. 插入区间
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int l = newInterval[0];
        int r = newInterval[1];
        boolean flag = false;
        List<int[]> res = new ArrayList<>();

        for(int[] ints : intervals){
            if(ints[1] < l){
                res.add(ints);
            }else if(ints[0] > r){
                if(!flag){
                    res.add(new int[]{l,r});
                    flag = true;
                }
                res.add(ints);
            }else {
                l = Math.min(l,ints[0]);
                r = Math.max(r,ints[1]);
            }
        }

        if(!flag)res.add(new int[]{l,r});

        return res.toArray(new int[0][]);
    }

    /**
     * @Title: 61. 旋转链表
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null)return head;
        int len = 1;
        ListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
            len++;
        }
        temp.next = head;

        ListNode res = head;
        for(int i = 0;i < len-1-k%len;i++){
            res = res.next;
        }
        ListNode newHead = res.next;
        res.next = null;

        return newHead;
    }

    /**
     * @Title: 62. 不同路径
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0;i < m;i++){
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for(int i = 1;i < m;i++){
            for(int j = 1;j < n;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * @Title: 62. 不同路径Ⅱ
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        obstacleGrid[0][0] = obstacleGrid[0][0]==1?0:1;
        for(int i = 1;i < m;i++){
            obstacleGrid[i][0] = obstacleGrid[i-1][0]==0?0:obstacleGrid[i][0]==1?0:1;
        }
        for(int i = 1;i < n;i++){
            obstacleGrid[0][i] = obstacleGrid[0][i-1]==0?0:obstacleGrid[0][i]==1?0:1;
        }

        for(int i = 1;i < m;i++){
            for(int j = 1;j < n;j++){
                obstacleGrid[i][j] = obstacleGrid[i][j]==1?0:obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
            }
        }
        return obstacleGrid[m-1][n-1];
    }

    /**
     * @Title: 73. 矩阵置零
     */
    public void setZeroes(int[][] matrix) {
        boolean flag = false;

        for(int i = 0;i < matrix.length;i++){
            if(matrix[i][0] == 0 && !flag)flag = true;

            for(int j = 1;j < matrix[0].length;j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for(int i = matrix.length-1;i >= 0;i--){
            for(int j = 1;j < matrix[0].length;j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0)matrix[i][j] = 0;
            }
            if(flag)matrix[i][0] = 0;
        }
    }

    /**
     * @Title: 74. 搜索二维矩阵
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for(int[] ma : matrix){
            if(ma[0] <= target && target <= ma[n-1]){
                int l = 0,r = n-1;
                while(l <= r){
                    int mid = l + (r-l)/2;
                    if(ma[mid] == target)return true;
                    if(ma[mid] < target){
                        l = mid+1;
                    }else {
                        r = mid-1;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @Title: 75. 颜色分类
     */
    public void sortColors(int[] nums) {
        int p0 = 0,p1 = 0;

        for(int i = 0;i < nums.length;i++){
            if(nums[i] == 1){
                swap(nums,i,p1);
                p1++;
            }
            if(nums[i] == 0){
                swap(nums,i,p0);
                if(p0 < p1){
                    swap(nums,i,p1);
                }
                p0++;
                p1++;
            }
        }
    }

    /**
     * @Title: 80. 删除有序数组中的重复项 II
     */
    public int removeDuplicates(int[] nums) {
        int count = 1;
        int i = 0;

        for(int j = 1;j < nums.length;j++){
            if(nums[i] == nums[j] && count < 2){
                count++;
                i++;
                nums[i] = nums[j];
            }else if(nums[i] != nums[j]){
                count = 1;
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

    /**
     * @Title: 82. 删除排序链表中的重复元素 II
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode res = new ListNode();
        res.next = head;
        ListNode temp = res;

        while(temp.next != null && temp.next.next != null){
            if(temp.next.val == temp.next.next.val){
                int x = temp.next.val;
                while(temp.next != null && temp.next.val == x){
                    temp.next = temp.next.next;
                }
            }else temp = temp.next;

        }
        return res.next;
    }

    /**
     * @Title: 92. 反转链表
     */


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
