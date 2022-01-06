package com.zhaipz.study.datastructure.dto;

import lombok.Data;

/**
 * @author zhaipz
 * @ClassName: Student
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2021/12/7 16:58
 */
@Data
public class Student {

    private int age;

    private String name;

    public Student(int age){
        this.age = age;
    }
}
