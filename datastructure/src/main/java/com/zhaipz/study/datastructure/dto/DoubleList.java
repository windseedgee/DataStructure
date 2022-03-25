package com.zhaipz.study.datastructure.dto;

/**
 * @author zhaipz
 * @ClassName: DoubleList
 * @Description: 双向链表
 * @date 2021/5/10 10:35
 */
public class DoubleList {
    public DoubleNode head;
    public DoubleNode tail;
    public int size;

    public DoubleList() {
        head = new DoubleNode(0,0);
        tail = new DoubleNode(0,0);
        size = 0;
        head.next = tail;
        tail.prev = head;
    }

    public void addLast(DoubleNode x){
        x.prev = tail.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }

    public void remove(DoubleNode x){
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    public DoubleNode removeFirst(){
        if (head.next == tail)return null;
        DoubleNode first = head.next;
        remove(first);
        return first;
    }

    public int size(){
        return size;
    }
}
