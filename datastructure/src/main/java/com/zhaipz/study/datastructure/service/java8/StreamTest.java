package com.zhaipz.study.datastructure.service.java8;

import com.zhaipz.study.datastructure.dto.Student;
import com.zhaipz.study.datastructure.service.throughtwork.UAV;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhaipz
 * @ClassName: streamTest
 * @Description: JDK1.8  lambda表达式（函数式编程） Stream流的使用
 * @date 2022/2/28 15:29
 */

@Slf4j
public class StreamTest {

    public static void main(String[] args) {
        /*
        https://www.jianshu.com/p/11c925cdba50
        参考地址
        Stream流  Stream的操作符大体上分为两种：中间操作符和终止操作符  必须要有终止操作符且只能使用一次，否则不执行中间操作，流不能分隔开，不能断开。
         */

        //中间操作符
        List<String> list  = Arrays.asList("abc", "def", "gkh", "abc","dada","ddd","hfajh","dd","wet","cac","ljoi","mcai");
        Stream<String> stringStream = list.stream();

        //stringStream.map(s -> s+"aaa").forEach(log::info);
        //stringStream.filter("abc"::equals).forEach(log::info);
        //stringStream.flatMap(s -> Stream.of(s.split("b"))).forEach(log::info);
        //stringStream.limit(2).forEach(log::info);
        //stringStream.distinct().forEach(log::info);
        //stringStream.skip(2).forEach(log::info);

        Student student1 = new Student("zhang",11);
        Student student2 = new Student("li",12);
        Student student3 = new Student("li",10);
        Stream<Student> studentStream = Stream.of(student1,student2,student3);
        //studentStream.peek(s->s.setName(s.getName()+"p")).forEach(s->log.info(s.toString()));
        //studentStream.sorted(Comparator.comparingInt(Student::getAge)).forEach(s->log.info(s.toString()));

        //终止操作符
//        Set<String> set = stringStream.collect(Collectors.toSet());
//        log.info(set.toString());

//        long count = stringStream.count();
//        log.info(String.valueOf(count));

//        log.info(String.valueOf(stringStream.findFirst()));
        
//        Optional<String> optionalS =  list.parallelStream().findAny();
//        optionalS.ifPresent(log::info);

        boolean no = stringStream.noneMatch("a"::startsWith);
        log.info(String.valueOf(no));






    }
}
