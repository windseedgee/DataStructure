package com.zhaipz.study.datastructure.service;

import com.zhaipz.study.datastructure.dto.DoubleList;
import com.zhaipz.study.datastructure.dto.DoubleNode;

import java.util.HashMap;

/**
 * @author zhaipz
 * @ClassName: LRUCache
 * @Description: LRU缓存淘汰策略 优先删除最久未使用的缓存内容
 *  需求
 *  提供两个API get put 时间复杂度要保证为O(1)
 *  get方法获取key对应的val，key不存在则返回-1
 *  put方法存放缓存内容，若超出现有容量，则删除最久未使用的缓存内容
 * @date 2021/5/10 9:28
 *
 * 实现思路：
 * 1、显然cache中存放的内容要保证是有时序的，以区分最近使用和久未使用的缓存内容，容量满了后再存放内容则删除最久未使用的内容
 * 2、要保证在cache中可以快速获取key对应的val
 * 3、每次访问缓存中的key，都要将其变为最近使用的缓存，所以要支持快速删除和插入
 * 符合上述条件的数据结构：linkedHashMap
 * 本类是手动模拟实现LRU缓存淘汰算法 双向链表+哈希表
 */
public class LRUCache {
    HashMap<Integer, DoubleNode> map;
    DoubleList cache;
    int cap;

    public LRUCache(int capacity){
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key){
        if(!map.containsKey(key))return -1;

        DoubleNode node = map.get(key);
        makeRecently(key);
        return node.getVal();
    }

    public void put(int key,int val){
        if(map.containsKey(key)){
            deleteKey(key);
            addRecently(key,val);
            return;
        }
        if(cap == cache.size()){
            deleteLastKey();
        }
        addRecently(key, val);
    }
    /**
    * @Title: 将某个key提升为最近使用的
    * @Description: 从链表中删除key，再从链表尾部插入
    * @inParam
    * @return
    * @throws
    */
    public void makeRecently(int key){
        DoubleNode first = map.get(key);
        cache.remove(first);
        cache.addLast(first);
    }

    /**
     * @Title: 添加某个key
     * @Description: 从链表尾部插入
     * @inParam
     * @return
     * @throws
     */
    public void addRecently(int key,int val){
        DoubleNode lasted = new DoubleNode(key,val);
        cache.addLast(lasted);
        map.put(key,lasted);
    }

    /**
     * @Title: 删除某一个key
     * @Description: 从链表中删除key
     * @inParam
     * @return
     * @throws
     */
    public void deleteKey(int key){
        DoubleNode keyNode = map.get(key);
        cache.remove(keyNode);
        map.remove(key);
    }

    /**
     * @Title: 删除最久未使用的key
     * @Description: 删除链表头部节点
     * @inParam
     * @return
     * @throws
     */
    public void deleteLastKey(){
        DoubleNode node = cache.removeFirst();
        map.remove(node.getKey());
    }
}
