package com.zhaipz.study.datastructure.service.userinfo;

import java.util.Map;

/**
 * @author zhaipz
 * @Interface: IUserInfo
 * @Description: 用户信息操作接口
 * @date 2022/1/26 14:55
 */
public interface IUserInfo {
    void insertUser();
    Map<String,Object> queryUser(int id);
}
