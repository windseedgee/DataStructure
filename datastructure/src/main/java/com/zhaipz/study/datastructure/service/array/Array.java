package com.zhaipz.study.datastructure.service.array;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import javax.xml.soap.Node;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhaipz
 * @ClassName: Array
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2020/12/20 16:26
 */
@Service
public class Array implements BeanPostProcessor {

    public static void main(String[] args) {
        System.out.println("xxxx");
        Deque<Integer> xStack = new LinkedList<>();
        Node node;

    }

//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        // TODO Auto-generated method stub
//        System.out.println("postProcessBeforeInitialization..."+beanName+"=>"+bean);
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        // TODO Auto-generated method stub
//        System.out.println("postProcessAfterInitialization..."+beanName+"=>"+bean);
//        return bean;
//    }
}
