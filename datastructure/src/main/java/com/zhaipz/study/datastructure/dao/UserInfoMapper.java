package com.zhaipz.study.datastructure.dao;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhaipz
 * @Interface: IUserInfo
 * @Description: 用户信息操作
 * @date 2022/1/26 9:06
 */
@Component
public interface UserInfoMapper {
    Map<String,Object> queryUser(String id);
    void insertUserInfo(Map<String,Object> map);
}
