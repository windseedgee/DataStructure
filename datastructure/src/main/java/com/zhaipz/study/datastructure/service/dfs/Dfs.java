package com.zhaipz.study.datastructure.service.dfs;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author zhaipz
 * @ClassName: Dfs
 * @Description: 回溯算法（深度优先搜索）
 * @date 2021/4/13 17:43
 */
@Slf4j
public class Dfs {
    public static void main(String[] args) {
        List<List<Integer>> res = permute(new int[]{1,2,3,4});
        String x = res.get(8).toString().replace(",", "");

        log.info("全排列：{}", x);
//        List<List<String>> resQ = solveNQueens(4);
//        log.info("N皇后：{}",resQ.size());
//
        List<List<Integer>> resS = subsets(new int[]{1,2,2});
        log.info("无重复子集：{}",resS);
//
//        List<List<Integer>> resC = combine(4,2);
//        log.info("无重复组合：{}",resC);

//        List<String> resP = generateParenthesis(3);
//        log.info("有效括号：{}",resP);
    }

    /**
    * @Title: 全排列
    * @Description: 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
    * @inParam 数字序列
    * @return 无重复全排列
    * @throws
    */
    static List<List<Integer>> res = new LinkedList<>();

    public static List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        getTrack(nums,track);
        return res;
    }

    public static void getTrack(int[] nums,LinkedList<Integer> track){
        //递归结束条件 找到一种合法情况
        if(nums.length == track.size()){
            res.add(new LinkedList<>(track));
            return;
        }
        //遍历根路径
        for (int num : nums) {
            //去掉不合法的选择
            if (track.contains(num)) {
                continue;
            }
            //添加路径
            track.add(num);
            //继续往下走
            getTrack(nums, track);
            //重置路径
            track.removeLast();
        }
    }

    /**
    * @Title: N皇后
    * @Description: 将 n 个皇后放置在 n * n 的棋盘上，并且使皇后彼此之间不能相互攻击。
    * @inParam n 整数个皇后
    * @return 所有不同的 n 皇后问题 的解决方案。
    * @throws
    */
    static List<List<String>> resQ = new LinkedList<>();
    public static List<List<String>> solveNQueens(int n) {
        //初始化棋盘
        char[][] borad = new char[n][n];
        for (char[] bo : borad){
            Arrays.fill(bo,'.');
        }
//        LinkedList<String> borad = new LinkedList<String>();
//        StringBuilder test = new StringBuilder();
//        //初始化棋盘 初始化n列
//        for (int i = 0;i<n;i++){
//            test.append(".");
//        }
//        //初始化n行
//        for (int i = 0;i<n;i++){
//            borad.add(test.toString());
//        }

        getNQueens(borad,n,0);
        return resQ;
    }

    public static void getNQueens(char[][] borad,int n ,int row){
        if(row == borad.length){
            ArrayList<String> arrayList = new ArrayList<>();
            for (char[] line : borad){
                arrayList.add(String.valueOf(line));
            }
            resQ.add(arrayList);
            return;
        }

        for (int col = 0;col < n;col++){
            if(!isVaild(borad,n,row,col)){
                continue;
            }
            borad[row][col] = 'Q';
//            StringBuilder builder = new StringBuilder(borad.get(row));
//            builder.setCharAt(col,'Q');
//            borad.set(row,builder.toString());

            getNQueens(borad,n,row+1);

            borad[row][col] = '.';
//            StringBuilder builder2 = new StringBuilder(borad.get(row));
//            builder2.setCharAt(col,'.');
//            borad.set(row,builder2.toString());
        }
    }

    public static boolean isVaild(char[][] borad,int n,int row,int col){
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < n; i++) {
            if(borad[i][col] == 'Q'){
                return false;
            }
        }

        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {
            if (borad[i][j] == 'Q')
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (borad[i][j] == 'Q')
                return false;
        }
        return true;
    }

    /**
    * @Title: 子集
    * @Description: 给定一个整数数组 nums ，数组中的元素互不相同 。返回该数组所有可能的子集（幂集）。
    * @inParam 整数数组
    * @return 不重复的所有可能子集
    * @throws
    */
    static List<List<Integer>> resS = new LinkedList<>();
    public static List<List<Integer>> subsets(int[] nums) {
        //记录走过的路径
        LinkedList<Integer> borad = new LinkedList<>();
        getSets(nums,0,borad);
        return resS;
    }

    public static void getSets(int[] nums,int start,LinkedList<Integer> borad){
        resS.add(new ArrayList<>(borad));

        for (int i = start; i < nums.length; i++) {
            borad.add(nums[i]);
            getSets(nums,i+1,borad);
            borad.removeLast();
        }
    }
    /**
     * @Title: 组合
     * @Description: 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     * @inParam n 树的宽度 k 树的深度
     * @return 不重复的组合集合
     * @throws
     */
    static List<List<Integer>> resC = new LinkedList<>();
    public static List<List<Integer>> combine(int n, int k) {
        LinkedList<Integer> borad = new LinkedList<>();
        getCombines(n,k,1,borad);
        return resC;
    }

    public static void getCombines(int n,int k,int start,LinkedList<Integer> borad){
        if(k == borad.size()){
            resC.add(new ArrayList<>(borad));
            return;
        }

        for (int i = start; i <= n; i++) {
            borad.add(i);
            getCombines(n, k, i+1, borad);
            borad.removeLast();
        }
    }

    /**
     * @Title: 解数独
     * @Description: 9x9宫格数独
     * @inParam 9x9宫格数独
     * @return 其中的一种解
     * @throws
     */
    public static void solveSudoku(char[][] board){
        getSudo(board,0,0);
        log.info("解数独：{}", Arrays.deepToString(board));
    }

    public static boolean getSudo(char[][] board,int i,int j){
        //9宫格
        int m = 9;
        int n = 9;

        //9x9 穷举结束，找到一个可行解
        if(i == m)return true;
        //一行穷举结束，进入下一行
        if(j == n)return getSudo(board,i+1,0);
        //遇到预设字符，跳过
        if(board[i][j] != '.')return getSudo(board,i,j+1);

        //穷举
        for (char ch = '1';ch <= '9' ; ch++){
            //重复字符，跳过
            if(!isVaildSuDo(board,i,j,ch))continue;
            //选择路径
            board[i][j] = ch;
            //若找到可行解，返回
            if(getSudo(board,i,j+1))return true;
            //撤销选择
            board[i][j] = '.';
        }

        return false;
    }

    public static boolean isVaildSuDo(char[][] board,int row,int col,char ch){
        for (int i = 0;i<9;i++){
            //列是否有重复
            if(board[i][col] == ch)return false;
            //行是否有重复
            if(board[row][i] == ch)return false;
            //3x3宫格内是否有重复
            if(board[(row/3)*3 + i/3][(col/3)*3 + i%3] == ch)return false;
        }
        return true;
    }

    /**
    * @Title: 括号生成
    * @Description: 数字 n 代表生成括号的对数，用于能够生成所有可能的并且 有效的 括号组合。
    * @inParam 括号对数
    * @return 有效括号组合
    * @throws
    */
    static List<String> resP = new LinkedList<>();
    public static List<String> generateParenthesis(int n) {
        if(n == 0 || n == 1){
            resP.add("{}");
            return resP;
        }

        StringBuilder track = new StringBuilder();
        getParenthesis(n,n,track);
        return resP;
    }

    public static void getParenthesis(int left,int right,StringBuilder track){
        if (right < left)return;
        if (left < 0)return;
        if (left == 0 && right == 0){
            resP.add(track.toString());
            return;
        }

        track.append("(");
        getParenthesis(left-1,right,track);
        track.deleteCharAt(track.length()-1);

        track.append(")");
        getParenthesis(left,right-1,track);
        track.deleteCharAt(track.length()-1);
    }
}
