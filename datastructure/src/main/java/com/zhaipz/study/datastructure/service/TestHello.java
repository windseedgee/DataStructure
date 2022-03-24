package com.zhaipz.study.datastructure.service;

import com.zhaipz.study.datastructure.dto.ListNode;
import com.zhaipz.study.datastructure.dto.Student;
import com.zhaipz.study.datastructure.dto.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaipz
 * @ClassName: TestHello
 * @Description: 日常测试类
 *  *  * @date 2021/4/29 16:58
 */
@Slf4j
@Component
public class TestHello {

//        String x = "xx";
//        TreeNode treeNode = new TreeNode(1,new TreeNode(2),new TreeNode(3));
//        log.info("测试assert:{}",treeNode.getRight().getRight());
//        ArrayList<Object> objects = new ArrayList<>();
//
//        LRUCache lRUCache = new LRUCache(2);
//        lRUCache.put(1, 1); // 缓存是 {1=1}
//        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//        log.info("LRU获取缓存：{}",lRUCache.get(1));    // 返回 1
//        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//        log.info("LRU获取缓存：{}",lRUCache.get(2));    // 返回 -1 (未找到)
//        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//        log.info("LRU获取缓存：{}",lRUCache.get(1));    // 返回 -1 (未找到)
//        log.info("LRU获取缓存：{}",lRUCache.get(3));    // 返回 3
//        log.info("LRU获取缓存：{}",lRUCache.get(4));    // 返回 4
//
//        // cnt(x) = 键 x 的使用计数
//        // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
//        LFUCache lFUCache = new LFUCache(2);
//        lFUCache.put(1, 1);   // cache=[1,_], cnt(1)=1
//        lFUCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
//        log.info("LFU获取缓存：{}",lFUCache.get(1));      // 返回 1 cache=[1,2], cnt(2)=1, cnt(1)=2
//        lFUCache.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小 cache=[3,1], cnt(3)=1, cnt(1)=2
//        log.info("LFU获取缓存：{}",lFUCache.get(2));       // 返回 -1（未找到）
//        log.info("LFU获取缓存：{}",lFUCache.get(3));       // 返回 3 cache=[3,1], cnt(3)=2, cnt(1)=2
//        lFUCache.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用 cache=[4,3], cnt(4)=1, cnt(3)=2
//        log.info("LFU获取缓存：{}",lFUCache.get(1));       // 返回 -1（未找到）
//        log.info("LFU获取缓存：{}",lFUCache.get(3));       // 返回 3  cache=[3,4], cnt(4)=1, cnt(3)=3
//        log.info("LFU获取缓存：{}",lFUCache.get(4));       // 返回 4  cache=[3,4], cnt(4)=2, cnt(3)=3

//        int[] nums1 = new int[]{1,2};
//        int[] nums2 = new int[]{3,4};
//        log.info("中位数：{}",findMedianSortedArrays(nums1,nums2));

//        String r = String.valueOf(-121);
//
//        StringBuilder n = new StringBuilder(r);
//        log.info("中位数：{}",n);
//        StringBuilder m = n.reverse();
//        log.info("中位数：{}",m);
//        log.info("结果：{}", m.equals(r));
//
//        int[] nums = new int[]{3,4,-1,1};
//        log.info("结果：{}", firstMissingPositive(nums));
//        int[][] res = generateMatrix(3);
//        log.info("结果：{}", jump(new int[]{2,3,1,1,4}));
//        LFUCache cache = new LFUCache(2);
//        cache.put(1,1);
//        cache.put(2,2);
//        System.out.println(cache.get(1));
//        new ConcurrentHashMap<>();
//        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
//        List<List<String>> list = groupAnagrams(strs);
//        log.info("字母异位词分组：{}",list);
//
//        Student s1 = new Student(1);
//        Student s2 = new Student(2);
//
//        swap(s1,s2);
//
//        log.info("main s1 : {}",s1.getAge());
//        log.info("main s2 : {}",s2.getAge());


    /**
     * 根据日期获取 星期 （2019-05-06 ——> 星期一）
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = f.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //一周的第几天
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static void swap(Student x,Student y){
        int temp = x.getAge();
        x.setAge(y.getAge());
        y.setAge(temp);

        log.info("swap x : {}",x.getAge());
        log.info("swap y : {}",y.getAge());
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = 0,n2 = 0;
        int sum = nums1.length + nums2.length;
        int[] res = new int[sum];
        int i = 0;
        while(i <= sum/2){
            if(n1 >= nums1.length ){
                res[i] = nums2[n2];
                n2++;
                i++;
                continue;
            }
            if(n2 >= nums2.length){
                res[i] = nums1[n1];
                n1++;
                i++;
                continue;
            }
            if(nums1[n1] < nums2[n2]){
                res[i] = nums1[n1];
                n1++;
            }else{
                res[i] = nums2[n2];
                n2++;
            }
            i++;
        }
        if(sum%2 != 0){
            return res[(sum/2)];
        }else{
            return (double) (res[sum / 2] + res[(sum / 2) - 1]) / 2;
        }
    }

    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for(int i = 0;i < n;i++){
            if(nums[i] <= 0){
                nums[i] = n+1;
            }
        }

        for(int i = 0;i < n;i++){
            int num = Math.abs(nums[i]);
            if(num <= n){
                nums[num-1] = -Math.abs(nums[num-1]);
            }
        }

        for(int i = 0;i < n;i++){
            if(nums[i] > 0){
                return i+1;
            }
        }

        return n+1;
    }

    public static int[][] merge(int[][] intervals) {
        if(intervals.length == 0){
            return new int[0][2];
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> res = new ArrayList<>();
        for (int[] interval : intervals) {
            int L = interval[0], R = interval[1];
            if (res.isEmpty() || res.get(res.size() - 1)[1] < L) {
                res.add(new int[]{L, R});
            } else {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1],R);
            }
        }

        return res.toArray(new int[0][]);
    }

    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if(n == 1){
            res[0][0] = 1;
            return res;
        }
        int left = 0;
        int right = n-1;
        int top = 0;
        int bottom = n-1;
        int sum = n * n;
        int num = 1;
        while(num <= sum){
            for(int i = left;i<=right;i++){
                res[top][i] = num;
                num++;
            }
            top++;
            for(int i = top;i<=bottom;i++){
                res[i][right] = num;
                num++;
            }
            right--;
            for(int i = right;i>=left;i--){
                res[bottom][i] = num;
                num++;
            }
            bottom--;
            for(int i = bottom;i>=top;i--){
                res[i][left] = num;
                num++;
            }
            left++;
        }
        return res;
    }

    public static int myAtoi(String s) {

        s = s.trim();
        if(s.equals(""))return 0;

        char one = s.charAt(0);
        if( (one - '9'> 9 || one - '0' < 0) && (one != '+' && one != '-') )return 0;
        StringBuilder res = new StringBuilder();
        res.append(one);

        for(int i = 1;i < s.length();i++){
            if(s.charAt(i) - '9' > 9 || s.charAt(i) - '0' < 0 ){
                break;
            }
            res.append(s.charAt(i));
        }

        if(res.length() == 1 && (one == '+' || one == '-'))return 0;

        long r = Long.parseLong(res.toString());
        if(r > Integer.MAX_VALUE)return Integer.MAX_VALUE;
        if(r < Integer.MIN_VALUE)return Integer.MIN_VALUE;

        return (int) r;
    }

    public static int divide(int dividend, int divisor) {
        if(divisor == 0)return 0;
        if(divisor == 1)return dividend;
        if(divisor == -1){
            if(dividend > Integer.MIN_VALUE)return -dividend;
            return Integer.MAX_VALUE;
        }

        int sign = 0;
        if((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)){
            sign = -1;
        }

        long a = dividend;
        long b = divisor;
        a = a > 0? a : -a;
        b = b > 0? b : -b;

        long res = div(a,b);
        if(sign == 0)return Math.min((int) res, Integer.MAX_VALUE);
        return (int)-res;
    }

    public static long div(long a, long b){
        if(a < b)return 0;
        long count = 1;
        long temp = b;

        while((temp+temp) <= a){
            count = count  + count;
            temp = temp  + temp;
        }

        return count + div(a-temp,b);
    }

    public static int jump(int[] nums){
        int n = nums.length;
        int fast = 0,end = 0,jumps = 0;

        for(int i = 0;i < n-1; i++){
            fast = Math.max(fast, i + nums[i]);
            if(end == i){
                jumps++;
                end = fast;
            }
        }
        return jumps;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String,ArrayList<String>> map = new HashMap<>();

        for(String str : strs){
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            log.info("分组前的排序：{}", Arrays.hashCode(temp));
            String s = new String(temp);

            ArrayList<String> list = map.getOrDefault(s,new ArrayList<String>());
            list.add(str);
            map.put(s,list);
        }

        return new ArrayList<>(map.values());
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        for(int[] ma : matrix){
            if(ma[0] <= target && target <= ma[ma.length-1]){
                int l = 0,r = ma.length-1;
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



}
