package com.zhaipz.study.datastructure.service;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author zhaipz
 * @ClassName: FreqStack
 * @Description: 最大频次栈  pop 弹出栈中次数最大，时间最新入栈的值
 * @date 2021/5/10 18:28
 */
public class FreqStack {

    HashMap<Integer,Integer> valToFreq = new HashMap<>();
    HashMap<Integer, Stack<Integer>> freqToVals= new HashMap<>();
    int maxFreq;

    public void push(int val){
        int freq = valToFreq.getOrDefault(val,0) + 1;
        valToFreq.put(val,freq);

        freqToVals.putIfAbsent(freq,new Stack<>());
        freqToVals.get(freq).push(val);

        maxFreq = Math.max(maxFreq,freq);
    }

    public int pop(){
        Stack<Integer> vals = freqToVals.get(maxFreq);
        int val = vals.pop();

        int freq = valToFreq.get(val)-1;
        valToFreq.put(val,freq);

        if(vals.isEmpty()){
            maxFreq--;
        }

        return val;
    }

}
