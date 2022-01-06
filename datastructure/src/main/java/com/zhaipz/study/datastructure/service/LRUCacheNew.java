package com.zhaipz.study.datastructure.service;

import java.util.LinkedHashMap;

/**
 * @author zhaipz
 * @ClassName: LRUCacheNew
 * @Description: LRU缓存淘汰策略 使用java提供的LinkedHashMap实现
 * @date 2021/5/10 18:30
 */
public class LRUCacheNew {

    int cap;
    LinkedHashMap<Integer,Integer> cache = new LinkedHashMap<>();

    public LRUCacheNew(int capacity){
        this.cap = capacity;
    }

    public int get(int key){
        if(!cache.containsKey(key))return -1;
        moveKey(key);
        return cache.get(key);
    }

    public void put(int key,int val){
        if(cache.containsKey(key)){
            cache.put(key,val);
            moveKey(key);
            return;
        }

        if(this.cap <= cache.size()){
            int lastKey = cache.keySet().iterator().next();
            cache.remove(lastKey);
        }

        cache.put(key,val);
    }

    public void moveKey(int key){
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key,val);
    }
}
