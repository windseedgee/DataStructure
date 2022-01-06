package com.zhaipz.study.datastructure.dto;

import lombok.Data;

/**
 * @author zhaipz
 * @ClassName: ListNode
 * @Description: 单链表节点
 * @date 2021/8/27 14:53
 */
@Data
public class ListNode {

    int val;
    ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
