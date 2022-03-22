package com.zhaipz.study.datastructure.entity;

import lombok.Data;

/**
 * @author zhaipz
 * @ClassName: User
 * @Description:
 * @date 2022/3/22 10:41
 */

@Data
public class User {
    private int id;

    private String name;

    private int masterServId;

    private String runCode;
}
