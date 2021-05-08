package com.zhaipz.study.datastructure.service;

import com.zhaipz.study.datastructure.dao.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author zhaipz
 * @ClassName: TestHello
 * @Description: 日常测试类
 *  *  * @date 2021/4/29 16:58
 */
@Slf4j
public class TestHello {
    public static void main(String[] args) {
        String x = new String("xx");
        TreeNode treeNode = new TreeNode(1,new TreeNode(2),new TreeNode(3));
        log.info("测试assert:{}",treeNode.getRight().getRight());
        ArrayList<Object> objects = new ArrayList<>();
    }
}
