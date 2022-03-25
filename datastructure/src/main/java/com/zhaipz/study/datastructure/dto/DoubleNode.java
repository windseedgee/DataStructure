package com.zhaipz.study.datastructure.dto;

import lombok.Data;

/**
 * @author zhaipz
 * @ClassName: LRUNode
 * @Description: 双向链表节点
 * @date 2021/5/10 9:29
 */
@Data
public class DoubleNode {

    public DoubleNode next;
    public DoubleNode prev;
    public int key;
    public int val;

    public DoubleNode(int key, int val){
        this.key = key;
        this.val = val;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("key : " + key + "val : " + val + "is daying");
    }
}
