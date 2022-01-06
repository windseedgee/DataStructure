package com.zhaipz.study.datastructure.service;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author zhaipz
 * @ClassName: LFUCache
 * @Description: LFU缓存淘汰策略 优先删除使用次数最少的缓存，若存在多个最少次数相同的缓存，则删除最少次数最旧（最久未使用）的缓存
 * @date 2021/5/10 17:32
 */
public class LFUCache {

    class Node{
        int key;
        int val;
        int freq;
        Node(int key,int val,int freq){
            this.key = key;
            this.val = val;
            this.freq = freq;
        }
    }
    int capacity;
    int minFreq;
    Map<Integer,Node> keyMap;
    Map<Integer, LinkedList<Node>> freqMap;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        keyMap = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = keyMap.get(key);
        if(capacity == 0 || node == null)return -1;
        addFreqNode(node,node.key,node.val);
        return node.val;
    }

    public void put(int key, int value) {
        if(capacity == 0)return;
        Node node = keyMap.get(key);
        if(!keyMap.containsKey(key)){
            if(capacity == keyMap.size()) {
                Node minNode = freqMap.get(minFreq).pollLast();
                keyMap.remove(minNode.key);
                if (freqMap.get(minFreq).size() == 0) {
                    freqMap.remove(minFreq);
                }
            }
            LinkedList<Node> list = freqMap.getOrDefault(1,new LinkedList<Node>());
            list.offerFirst(new Node(key,value,1));
            freqMap.put(1,list);
            keyMap.put(key,freqMap.get(1).peekFirst());
            minFreq = 1;
        }else{
            addFreqNode(node,key,value);
        }
    }

    public void addFreqNode(Node node,int key,int value){
        int freq = node.freq;
        int val = node.val;
        freqMap.get(freq).remove(node);
        if(freqMap.get(freq).size() == 0){
            freqMap.remove(freq);
            if(minFreq == freq){
                minFreq+=1;
            }
        }
        LinkedList<Node> list = freqMap.getOrDefault(freq+1,new LinkedList<Node>());
        list.offerFirst(new Node(key,value,freq+1));
        freqMap.put(freq+1,list);
        keyMap.put(key,freqMap.get(freq+1).peekFirst());
    }

//    HashMap<Integer,Integer> keyToVal;//key->val映射
//    HashMap<Integer,Integer> keyToFreq;//key->freq映射
//    HashMap<Integer, LinkedHashSet<Integer>> freqToKeyList;//freq->keys映射  相同次数的key存放在一个LinkedHashSet（有序，保证时序）
//    int cap;
//    int minFreq;
//
//    public LFUCache(int capacity){
//        keyToVal = new HashMap<>();
//        keyToFreq = new HashMap<>();
//        freqToKeyList = new HashMap<>();
//        this.cap = capacity;
//        this.minFreq = 0;
//    }
//
//    public int get(int key){
//        if(!keyToVal.containsKey(key))return -1;
//
//        insertFreq(key);
//        return keyToVal.get(key);
//
//    }
//
//    public void put(int key,int val){
//        if (this.cap == 0)return;
//        if(keyToVal.containsKey(key)){
//            keyToVal.put(key,val);
//            insertFreq(key);
//            return;
//        }
//
//        if(this.cap <= keyToVal.size()){
//            removeMinFreqKey();
//        }
//
//        keyToVal.put(key,val);
//        keyToFreq.put(key,1);
//        freqToKeyList.putIfAbsent(1,new LinkedHashSet<>());
//        freqToKeyList.get(1).add(key);
//        this.minFreq = 1;
//    }
//
//    public void insertFreq(int key){
//
//        int freq = keyToFreq.get(key);
//        keyToFreq.put(key,freq+1);
//        freqToKeyList.get(freq).remove(key);
//        freqToKeyList.putIfAbsent(freq+1,new LinkedHashSet<>());
//        freqToKeyList.get(freq+1).add(key);
//
//        if(freqToKeyList.get(freq).isEmpty()){
//            freqToKeyList.remove(freq);
//            if(freq == this.minFreq){
//                this.minFreq++;
//            }
//        }
//    }
//
//    public void removeMinFreqKey(){
//        LinkedHashSet<Integer> list = freqToKeyList.get(this.minFreq);
//        int minKey = list.iterator().next();
//
//        list.remove(minKey);
//        if(list.isEmpty()){
//            freqToKeyList.remove(this.minFreq);
//        }
//
//        keyToFreq.remove(minKey);
//        keyToVal.remove(minKey);
//    }
}
