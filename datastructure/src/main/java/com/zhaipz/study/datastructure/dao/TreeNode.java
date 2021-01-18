package com.zhaipz.study.datastructure.dao;

import lombok.Data;

/**
 * @author zhaipz
 * @ClassName: TreeNode
 * @Description: Definition for a binary tree node.
 * @date 2021/1/11 9:50
 */
@Data
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public boolean equals(TreeNode compare){
        return this.getVal() == compare.getVal();
    }
}
