package com.zhaipz.study.datastructure.dto;

import lombok.Data;

/**
 * @author zhaipz
 * @ClassName: Student
 * @Description: 学生信息类
 * @date 2021/12/7 16:58
 */
@Data
public class Student {

    private String name;

    private int age;

    public Student(int age){
        this.age = age;
    }

    public Student(String name,int age){
        this.name = name;
        this.age = age;
    }
}
