package com.zhaipz.study.datastructure.dao;

import com.zhaipz.study.datastructure.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

/**
 * @author zhaipz
 * @Interface: IUserInfo
 * @Description: 用户信息操作
 * @date 2022/1/26 9:06
 */
//@Mapper
@Repository
public interface UserInfoMapper {
    Map<String,Object> queryUser(int id);
    void insertUserInfo(Map<String,Object> map);
    List<User> queryUserInfo(User user);
}
