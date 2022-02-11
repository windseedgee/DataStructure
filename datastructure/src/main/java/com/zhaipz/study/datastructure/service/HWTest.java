package com.zhaipz.study.datastructure.service;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author zhaipz
 * @ClassName: TestHello
 * @Description: 日常测试类
 *  *  * @date 2021/4/29 16:58
 */
@Slf4j
public class HWTest {
    public static void main(String[] args) {

        System.out.println(numGame());

    }

    public static String MyTest(){
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        System.out.println(a);
        return String.valueOf(a);
    }

    public String largestNumber(int[] nums) {
        PriorityQueue<String> heap = new PriorityQueue<>((x, y) -> (y + x).compareTo(x + y));
        for(int x: nums) heap.offer(String.valueOf(x));
        StringBuilder res = new StringBuilder();
        while(heap.size() > 0) res.append(heap.poll());
        if(res.charAt(0) == '0') return "0";
        return res.toString();
    }

    public static int findTheLongestSubstring(String s) {
        int n = s.length();
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                status ^= (1 << 0);
            } else if (ch == 'e') {
                status ^= (1 << 1);
            } else if (ch == 'i') {
                status ^= (1 << 2);
            } else if (ch == 'o') {
                status ^= (1 << 3);
            } else if (ch == 'u') {
                status ^= (1 << 4);
            }
            if (pos[status] >= 0) {
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                pos[status] = i + 1;
            }
        }
        return ans;
    }

    Set<String> res = new HashSet<>();
    int max = 1;
    public int maxUniqueSplit(String s) {
        dfs(0,s);
        return max;
    }

    public void dfs(int index,String s){
        if(index == s.length()){
            max = Math.max(max,res.size());
            return;
        }

        for(int i = index+1;i < s.length();i++){
            String temp = s.substring(index,i);
            if(res.contains(temp)){
                continue;
            }

            res.add(temp);
            dfs(i,s);
            res.remove(temp);
        }
    }

    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        boolean[][] vis = new boolean[m][n];
        int ans = 1;
        vis[0][0] = true;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i == 0 && j == 0) || get(i) + get(j) > k) {
                    continue;
                }
                // 边界判断
                if (i - 1 >= 0) {
                    vis[i][j] |= vis[i - 1][j];
                }
                if (j - 1 >= 0) {
                    vis[i][j] |= vis[i][j - 1];
                }
                ans += vis[i][j] ? 1 : 0;
            }
        }
        return ans;
    }

    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }

    //1.已知连续正整数数列{K}=K1,K2,K3...Ki的各个数相加之和为S，i=N (0<S<100000, 0<N<100000), 求此数列K。
    //S = ((K1 + Kn)*n)/2   Kn = K1 + n - 1;
    public List<Integer> lianXuShuLie(int s,int n){
        List<Integer> res = new ArrayList<>();

        if(s*2 % n != 0 || (s*2/n-n)%2 == 0){
            res.add(-1);
            return res;
        }

        int k1 = (s*2/n-n+1)/2;
        for(int i = 0;i < n;i++){
            res.add(k1+i);
        }

        return res;
    }

    //2.查找众数及中位数
    public static int searchMidNum(){
        Scanner in = new Scanner(System.in);
        String temp = in.nextLine();
        String[] temps = temp.split(" ");
        int[] nums = new int[temps.length];
        for(int i = 0;i < temps.length;i++){
            nums[i] = Integer.parseInt(temps[i]);
        }

        Map<Integer,Integer> map = new HashMap<>(16);
        List<Integer> list = new ArrayList<>();
        int max = 0;
        for(int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);
            max = Math.max(max,map.get(num));
        }

        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(entry.getValue() == max){
                list.add(entry.getKey());
            }
        }

        Collections.sort(list);
        if(list.size()%2 == 0){
            return (list.get(list.size()/2-1)+list.get(list.size()/2))/2;
        }

        return list.get(list.size()/2);
    }

    //3.寻找相同子串
    public static int searchIndex(){
        Scanner in = new Scanner(System.in);
        String t = in.nextLine();
        String p = in.nextLine();
        int m = t.length()-1;
        int n = p.length()-1;
        //t.indexOf(p);
        for(int i = 0;i < m;i++){
            char ct = t.charAt(i);
            if(ct == p.charAt(0)){
                for(int k = i+1,j = 1;j <= n;k++,j++){
                    if(j == n)return i+1;
                    if(t.charAt(k) != p.charAt(j))break;
                }
            }
        }

        return -1;
    }

    //4.字符串统计
    public static String stringCount(){
        Scanner in = new Scanner(System.in);
        String temp = in.nextLine();
        String[] preAndNext = temp.split("@");
        if(preAndNext.length <= 1)return preAndNext[0];

        String[] pre = preAndNext[0].split(",");
        String[] end = preAndNext[1].split(",");
        Map<String,Integer> map = new LinkedHashMap<>();

        for(String p : pre){
            String[] kv = p.split(":");
            map.put(kv[0],Integer.parseInt(kv[1]));
        }

        for (String e : end){
            String[] kv = e.split(":");
            map.put(kv[0],map.get(kv[0])-Integer.parseInt(kv[1]));
        }

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(entry.getValue() > 0)sb.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
        }
        if (sb.length() > 0)sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    //5.磁盘容量排序
    public static List<String> sizeSort(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Map<Integer,String> map = new HashMap<>();
        for(int i = 0;i < n;i++){
            String s = in.next();
            int num = sort(s);
            if(map.containsKey(num)){
                map.put(num+1,s);
            }else {
                map.put(num,s);
            }

        }

        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        List<String> list1 = new ArrayList<>();
        for(int num : list){
            list1.add(map.get(num));
        }

        return list1;
    }

    public static int sort(String s){
        int res = 0;
        int count = 0;
        for(int i = 0;i < s.length();i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                res = res*10 + ch-'0';
            }else if(ch == 'T'){
                res = res * 1024 * 1024;
                count += res;
                res = 0;
            }else if(ch == 'G'){
                res = res * 1024;
                count += res;
                res = 0;
            }else {
                count += res;
                res = 0;
            }
        }

        return count;
    }

    //6.太阳能板最大面积
    public static int maxArea(){
        Scanner in = new Scanner(System.in);
        String temp = in.nextLine();
        String[] nums = temp.split(",");

        int l = 0;
        int r = nums.length-1;
        int res = 0;

        while(l < r){
            res = Math.max(res,Math.min(Integer.parseInt(nums[l]),Integer.parseInt(nums[r]))*(r-l));
            if(Integer.parseInt(nums[l]) <= Integer.parseInt(nums[r])){
                l++;
            }else {
                r--;
            }
        }
        return res;
    }

    //7.靠谱的车
    //n进制化十进制：按位乘n的i次方后相加，小数点左边第一位0次方，第二位1次方，以此类推，比如n进制数字1234，换成10进制后为4×n^0+3×n^1+2×n^2+1×n^3
    //十进制化n进制：反复除n取余数，除n的得数再取余数，直到得数为0，把余数按顺序从低位到高位写出即可，比如1234换八进制，第1次除8得154余2，154除8得19余2，19除8得2余3，2除8得0余2，所以最后得到2322
    public static int taxiFee(){
        Scanner in = new Scanner(System.in);
        String n = in.nextLine();
//        int res = n,temp = 0,k = 0,j = 1;
//
//        while(n > 0){
//            if(n % 10 > 4){
//                temp += (n%10-1)*k+j;
//            }else {
//                temp += (n%10)*k;
//            }
//            k = k*9+j;
//            j *= 10;
//            n /= 10;
//        }
        int res = 0;
        for(int i = n.length()-1;i >= 0;i--){
            int a = n.charAt(i)-'0';
            int b = n.length()-i-1;
            res += a * Math.pow(2,b);
        }
        //return Integer.parseInt(n,9);
        return res;
    }

    //8.判断字符串子序列
    public static int searchLastIndex(){
        Scanner in = new Scanner(System.in);
        String t = in.nextLine();
        String s = in.nextLine();
        int m = t.length();
        int n = s.length();
        int index = n;
        Map<Character,Integer> map = new LinkedHashMap<>();

        if(m > 0 && n > 0){
            for(int j = m-1;j >=0;j--){
                for(int i = n-1;i >= 0;i--){
                    if(t.charAt(j) == s.charAt(i) && index > i){
                        index = i;
                        map.put(t.charAt(j),index);
                        break;
                    }
                }
            }

            int ros = 0;
            for(int j = m-1;j >=0;j--){
                if(!map.containsKey(t.charAt(j))){
                    ros = -1;
                    break;
                }
            }
            if(ros == 0)return map.get(t.charAt(0));
        }
        return -1;
    }

    //9.按身高和体重排队
    static class User implements Comparable<User>{
        int index;
        int h;
        int w;

        User(int i,int h,int w){
            this.index = i;
            this.h = h;
            this.w = w;
        }


        @Override
        public int compareTo(User o1) {
            if(this.h != o1.h){
                return this.h-o1.h;
            }
            if(this.w != o1.w){
                return this.w-o1.w;
            }
            return this.index-o1.index;
        }

        public String toString(){
            return String.valueOf(index);
        }
    }

    public static User[] sortUsers(){
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        User[] res = new User[num];
        int[] heights = new int[num];
        int[] wigeths = new int[num];

        for(int i = 0;i < num;i++){
            heights[i] = in.nextInt();
        }
        for(int i = 0;i < num;i++){
            wigeths[i] = in.nextInt();
        }

        for(int i = 0;i < num;i++){
            res[i] = new User(i+1,heights[i],wigeths[i]);
        }

        Arrays.sort(res);
        return res;
    }

    //10.快递运输
    public static int maxNums(){
        Scanner in = new Scanner(System.in);
        String temp = in.nextLine();
        int car = in.nextInt();
        int res = 0;

        String[] nums = temp.split(",");
        int[] temp1 = new int[nums.length];
        for(int i = 0;i < nums.length;i++){
            temp1[i] = Integer.parseInt(nums[i]);
        }

        Arrays.sort(temp1);
        for(int num : temp1){
            if(car > num){
                car -= num;
                res++;
            }else break;
        }

        return res;
    }

    //11.TLV解码
    public static String tlv(){
        Scanner in = new Scanner(System.in);
        String key = in.nextLine();
        String temp = in.nextLine();
        String[] tlvs = temp.replaceAll("[a-z]","").split("[ ]");
        int length;
        String tag;
        StringBuilder value;

        for(int i = 0;i < tlvs.length;){
            tag = tlvs[i];
            value = new StringBuilder();
            length = Integer.parseInt(tlvs[i+2]+tlvs[i+1],16);
            for(int j = 1;j <= length;j++){
                value.append(tlvs[i + 2 + j]).append(" ");
            }
            if(tag.equals(key)){
                return value.toString().trim();
            }
            i = i+2+length+1;
        }

        return "null";
    }

    //12.字符串分割
    public static String stringDiv(){
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        String temp = in.next();

        String[] old = temp.split("-");
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i < old.length;i++){
            sb.append(old[i]);
        }
        int index = 0;
        List<String> res = new ArrayList<>();
        for(int i = k;i < sb.length()+k;i+=k){
            if(i >= sb.length())i = sb.length();
            String str = sb.substring(index,i);
            res.add(format(str));
            index = i;
        }

        return old[0]+"-"+String.join("-",res);
    }

    public static String format(String str){
        char[] res = str.toCharArray();
        int low = 0;
        int up = 0;
        for(char ch : res){
            if(Character.isUpperCase(ch))up++;
            if(Character.isLowerCase(ch))low++;
        }
        if(low > up){
            str = str.toLowerCase();
        }
        if(low < up){
            str = str.toUpperCase();
        }

        return str;
    }

    //13.组成最大数
    public static String maxNum(){
        PriorityQueue<String> queue = new PriorityQueue<>((x,y)->(y+x).compareTo(x+y));
        Scanner in = new Scanner(System.in);
        String temp = in.nextLine();
        String[] temps = temp.split(",");
        for(String str : temps){
            queue.offer(str);
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            sb.append(queue.poll());
        }

        return sb.toString();
    }

    //14.高矮个子排队
    public static String highAndLowSort(){
        Scanner in = new Scanner(System.in);
        String temps = in.nextLine();
        List<Integer> list = new ArrayList<>();

        for(String str : temps.split(" ")){
            try{
                list.add(Integer.valueOf(str));
            }catch(Exception e){
                return "[ ]";
            }

        }

        for(int i = 0;i < list.size()-1;i++){
            if((i%2 == 0 && list.get(i) < list.get(i+1)) || (i%2 == 1 && list.get(i) > list.get(i+1))){
                int temp = list.get(i);
                list.set(i,list.get(i+1));
                list.set(i+1,temp);
            }
        }

        return list.toString();
    }

    //15.猴子爬山
    public static int monkeyClimb(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        for(int i = 4;i <= n;i++){
            dp[i] = dp[i-1] + dp[i-3];

        }

        return dp[n];
    }

    //16.分糖果
    public static int divideCandy(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        int count = 0;
        for(int i = n;i != 1;i/=2,count++){
            if(i == 3){
                return count + 2;
            }

            if(i%2 != 0){
                if((i+1)/2%2 == 0)i++;
                else i--;
                count++;
            }
        }

        return count;
    }

    //17.报数游戏
    public static List<Integer> numGame(){
        List<Integer> res = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int keyNum = in.nextInt();
        if(keyNum < 1 || keyNum > 100)return res;
        Node head = new Node(1),pre = head;
        for(int i = 2;i <= 100;i++){
            pre.next = new Node(i);
            pre = pre.next;
        }
        pre.next = head;

        int num = 100,k = 1;
        while(true){
            k++;
            pre = pre.next;
            head = head.next;
            if(k == keyNum){
                pre.next = head.next;
                head = pre.next;
                k = 1;
                num--;
            }
            if(num < keyNum){
                pre = head;
                do{
                    res.add(head.val);
                    head = head.next;
                }while (pre != head);
                break;
            }
        }

        Collections.sort(res);
        return res;
    }

    static class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
        }
    }

    //18.消消乐游戏

}
