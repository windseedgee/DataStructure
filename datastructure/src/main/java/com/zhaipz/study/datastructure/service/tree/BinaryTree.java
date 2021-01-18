package com.zhaipz.study.datastructure.service.tree;

import com.zhaipz.study.datastructure.dao.TreeNode;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhaipz
 * @ClassName: BinaryTree
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2021/1/11 10:01
 */
@Service
public class BinaryTree {
    public static  List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traverse(root,list);
        return list;
    }

    /**
    * @Title: 二叉树遍历框架
    * @Description: 二叉树遍历框架（递归解法）帮助我们隐式的调用了一个栈
    * @inParam root
    * @return list
    * @throws
    */
    public static void traverse(TreeNode root,List<Integer> list){
        if(root == null){
            return;
        }
        //前序遍历位置
        list.add(root.getVal());
        traverse(root.getLeft(),list);
        //中序遍历位置
        traverse(root.getRight(),list);
        //后序遍历位置
    }
    /**
     * @Title: 二叉树遍历
     * @Description: 二叉树前序遍历（迭代解法）显示，自己手动维护一个栈
     * @inParam root
     * @return list
     * @throws
     */
    public static  List<Integer> firstPreorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            while (root != null){
                res.add(root.getVal());
                stack.push(root);
                root = root.getLeft();
            }
            root = stack.pop();
            root = root.getRight();
        }
        return res;
    }
    /**
     * @Title: 二叉树遍历
     * @Description: 二叉树中序遍历（迭代解法）显示，自己手动维护一个栈
     * @inParam root
     * @return list
     * @throws
     */
    public static  List<Integer> midPreorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.getLeft();
            }
            root = stack.pop();
            res.add(root.getVal());
            root = root.getRight();
        }
        return res;
    }
    /**
     * @Title: 二叉树遍历
     * @Description: 二叉树后序遍历（迭代解法）显示，自己手动维护一个栈
     * @inParam root
     * @return list
     * @throws
     */
    public static  List<Integer> lastPreorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while(!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.getLeft();
            }
            root = stack.pop();
            if(root.getRight() == null || root.getRight() == prev){
                res.add(root.getVal());
                prev = root;
                root = null;
            }else {
                stack.push(root);
                root = root.getRight();
            }
        }
        return res;
    }
    /**
     * @Title: 二叉树层序遍历
     * @Description: 二叉树层序遍历框架，锯齿形遍历判断层数即可
     * @inParam root
     * @return list
     * @throws
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);

        while(!nodes.isEmpty()){
            int count = nodes.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0;i<count;i++){
                TreeNode node = nodes.poll();
                if(node.getLeft() != null){
                    nodes.offer(node.getLeft());
                }
                if(node.getRight() != null){
                    nodes.offer(node.getRight());
                }
                temp.add(node.getVal());
            }
            //锯齿形遍历
//            if(res.size()%2 == 1){
//                Collections.reverse(temp);
//            }
            res.add(temp);
        }
        return res;
        //从下往上遍历
        //return Collections.reverse(res);
    }
    /**
     * @Title: 构造二叉树
     * @Description: 根据前序、中序（前序、后序 || 后序、中序）构造二叉树
     * @inParam root
     * @return list
     * @throws
     */
    public static Map<Integer,Integer> midIndex = new HashMap<>();
    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i = 0;i < inorder.length;i++){
            midIndex.put(inorder[i],i);
        }
        return buildTree(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }
    public static TreeNode buildTree(int[] preorder, int[] inorder,int pl,int pr,int il,int ir){
        if(il > ir){
            return null;
        }
        int rootVal = preorder[pl];
        int inRoot = midIndex.get(rootVal);
        int size = inRoot - il;
        TreeNode root = new TreeNode(rootVal);
        root.setLeft(buildTree(preorder,inorder,pl+1,pr-size,il,inRoot-1));
        root.setRight(buildTree(preorder,inorder,pl+size+1,pr,inRoot+1,ir));
        return root;
    }
    /**
     * @Title: 输出二叉树
     * @Description: 二叉树的序列化和反序列化，
     * @inParam root
     * @return list
     * @throws
     */
    public static String NULL = "null";
    public static String SEQ = ",";
    public static String serializer(TreeNode root){
        StringBuilder sb = new StringBuilder();
        perorder(root,sb);
        return sb.toString();
    }
    public static void perorder(TreeNode root,StringBuilder sb){
        if(root == null){
            sb.append(NULL).append(SEQ);
            return ;
        }
        sb.append(root.getVal()).append(SEQ);
        perorder(root.getLeft(),sb);
        perorder(root.getRight(),sb);

    }
    public static LinkedList<String> deList = new LinkedList<>();
    public static TreeNode deserializer(String data){
        deList.addAll(Arrays.asList(data.split(",")));
        return deserializer(deList);
    }

    public static TreeNode deserializer(LinkedList<String> deList){
        if(deList.isEmpty()){
            return null;
        }
        String first = deList.removeFirst();
        if(NULL.equals(first)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.setLeft(deserializer(deList));
        root.setRight(deserializer(deList));
        return root;
    }

    /**
     * @Title: 验证二叉树
     * @Description: 判断二叉树是否相同
     * @inParam root
     * @return list
     * @throws
     */
    public static boolean vaild(TreeNode p,TreeNode q){
        if(p == null && q == null){
            return true;
        }else if(p == null || q == null){
            return false;
        }
        return p.getVal() == q.getVal()&&vaild(p.getLeft(),q.getLeft())&&vaild(p.getRight(),q.getRight());
    }
    /**
     * @Title: 验证二叉树
     * @Description: 判断二叉树是否对称
     * @inParam root
     * @return list
     * @throws
     */
    public static boolean isSymmetric(TreeNode root){
        return check(root,root);
    }

    public static boolean check(TreeNode p,TreeNode q){
        if(p == null && q == null){
            return true;
        }else if(p == null || q == null){
            return false;
        }
        return p.getVal() == q.getVal()&&check(p.getLeft(),q.getRight())&&check(p.getRight(),q.getLeft());
    }
    /**
     * @Title: 验证二叉树
     * @Description: 判断二叉树是否平衡 任意左右节点深度差不大于1
     * @inParam root
     * @return list
     * @throws
     */
    public static boolean isBalance(TreeNode root){
        return getbalance(root) != -1;
    }

    public static int getbalance(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = getbalance(root.getLeft());
        int right = getbalance(root.getRight());

        if(left == -1 || right == -1 || Math.abs(left-right) > 1){
            return -1;
        }

        return Math.max(left,right)+1;
    }

    /**
     * @Title: 验证二叉树
     * @Description: 验证是否为二叉搜索树  左<中<右
     * @inParam root
     * @return list
     * @throws
     */
    public static boolean isBalaceSerachTree(TreeNode root){
        return isBalaceSerachTree(root,null,null);
    }

    public static boolean isBalaceSerachTree(TreeNode root,TreeNode min,TreeNode max){
        if(root == null){
            return true;
        }
        if(min != null && root.getVal() <= min.getVal())return false;
        if(max != null && root.getVal() >= max.getVal())return false;

        return isBalaceSerachTree(root.getLeft(),min,root)&&isBalaceSerachTree(root.getRight(),root,max);
    }
    /**
     * @Title: 二叉树翻转
     * @Description: 翻转二叉树  镜像翻转
     * @inParam root
     * @return list
     * @throws
     */
    public static TreeNode invertTree(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode temp = root.getLeft();
        root.setLeft(root.getRight());
        root.setRight(temp);

        invertTree(root.getLeft());
        invertTree(root.getRight());

        return root;
    }
    /**
     * @Title: 二叉树路径
     * @Description: 二叉树所有路径
     * @inParam root
     * @return list
     * @throws
     */
    public static List<String> res = new ArrayList<>();
    public static List<String> binaryTreePaths(TreeNode root) {
        if(root == null)return res;
        getRes(root,new StringBuilder());
        return res;
    }
    public static void getRes(TreeNode root,StringBuilder sb){
        if(root == null)return;
        sb.append(root.getVal());
        if(root.getRight() == null && root.getLeft() == null){
            res.add(sb.toString());
            return;
        }
        sb.append("->");
        getRes(root.getLeft(),new StringBuilder(sb));
        getRes(root.getRight(),new StringBuilder(sb));
    }
    /**
     * @Title: 二叉树路径总和
     * @Description: 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * @inParam root
     * @return list
     * @throws
     */
    public static boolean hasSumPath(TreeNode root,int sum){
        if(root == null)return false;
        if(root.getLeft() == null && root.getRight() == null)return root.getVal() == sum;
        return hasSumPath(root.getLeft(),sum-root.getVal())||hasSumPath(root.getRight(),sum-root.getVal());
    }
    /**
     * @Title: 二叉树路径总和
     * @Description: 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     * @inParam root
     * @return list
     * @throws
     */
    public static List<List<Integer>> result = new ArrayList<>();
    public static Deque<Integer> temp = new LinkedList<>();
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root,sum);
        return result;
    }
    public static void dfs(TreeNode root,int sum){
        if(root == null)return;

        temp.offerLast(root.getVal());
        sum -= root.getVal();
        if(root.getLeft() == null&&root.getRight() == null&&sum == 0){
            result.add(new ArrayList<>(temp));
        }
        dfs(root.getLeft(),sum);
        dfs(root.getRight(),sum);
        temp.pollLast();
    }
    /**
     * @Title: 二叉树深度
     * @Description: 二叉树的最大深度和最小深度
     * @inParam root
     * @return list
     * @throws
     */
    public static int treeMaxDeepth(TreeNode root){
        if(root == null)return 0;

        int left = treeMaxDeepth(root.getLeft());
        int right = treeMaxDeepth(root.getRight());
        return Math.max(left,right)+1;
    }

    public static int treeMinDeepth(TreeNode root){
        if(root == null)return 0;

        if(root.getLeft() == null&&root.getRight() == null){
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if(root.getLeft() != null){
            min = Math.min(treeMinDeepth(root.getLeft()),min);
        }
        if(root.getRight() != null){
            min = Math.min(treeMinDeepth(root.getRight()),min);
        }

        return min+1;
    }


    /**
     * @Title: 二叉树最近公共祖先
     * @Description: 二叉树的最近公共祖先和二叉搜索树的最近公共祖先
     * @inParam root
     * @return list
     * @throws
     */
    public static TreeNode lastCommon(TreeNode root,TreeNode p,TreeNode q){
        if(root == null)return null;
        if(root.equals(q) || root.equals(p))return root;

        TreeNode left = lastCommon(root.getLeft(),p,q);
        TreeNode right = lastCommon(root.getRight(),p,q);

        if(left == null&&right == null)return null;
        if(left != null&&right != null)return root;
        return left ==null?right:left;
    }

    public static TreeNode lastCommonSerach(TreeNode root,TreeNode p,TreeNode q){
        while (true){
            if(root.getVal() < p.getVal() && root.getVal() < q.getVal()){
                root = root.getRight();
            }else if(root.getVal() > p.getVal() && root.getVal() > q.getVal()){
                root = root.getLeft();
            }else {
                break;
            }
        }
        return root;
    }
    public static void main(String[] args) {
        //遍历二叉树
//        TreeNode root = new TreeNode(1);
//        TreeNode left = new TreeNode(3,new TreeNode(5),new TreeNode(4));
//        TreeNode right = new TreeNode(2,new TreeNode(6),new TreeNode(7));
//        root.setLeft(left);
//        root.setRight(right);
//        List<List<Integer>> list = levelOrder(root);
//        System.out.println(list);
//        //构造二叉树
//        int[] preorder = {3,9,20,15,7};
//        int[] inorder = {9,3,15,20,7};
//        TreeNode root = buildTree(preorder,inorder);
//        System.out.println(root);
        //序列化二叉树
//        String se = serializer(root);
//        System.out.println(se);
//        TreeNode de = deserializer(se);
//        System.out.println(preorderTraversal(de));
        //验证二叉树
//        TreeNode root1 = new TreeNode(1);
//        TreeNode left1 = new TreeNode(2);
//        TreeNode right1 = new TreeNode(2,new TreeNode(4,new TreeNode(5),new TreeNode(5)),new TreeNode(3));
//        root1.setLeft(left1);
//        root1.setRight(right1);
//        System.out.println(isBalance(root1));
        //最近公共祖先
//        TreeNode left2 = new TreeNode(2,new TreeNode(0),new TreeNode(4));
//        TreeNode right2 = new TreeNode(8,new TreeNode(7),new TreeNode(9));
//        TreeNode root2 = new TreeNode(6,left2,right2);
//        System.out.println(lastCommonSerach(root2,new TreeNode(2),new TreeNode(8)));
        //二叉树路径和
//        TreeNode left2 = new TreeNode(2,new TreeNode(0),new TreeNode(4));
//        TreeNode right2 = new TreeNode(8,new TreeNode(7),new TreeNode(9));
//        TreeNode root2 = new TreeNode(6,left2,right2);
//        System.out.println(binaryTreePaths(root2));
        //判断路径是否有目标值
        TreeNode left3 = new TreeNode(2,new TreeNode(0),new TreeNode(4));
        TreeNode right3 = new TreeNode(8,new TreeNode(7),new TreeNode(9));
        TreeNode root3 = new TreeNode(6,left3,right3);
        System.out.println(hasSumPath(root3,12));
        System.out.println(pathSum(root3,12));
    }
}
