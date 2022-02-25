package com.zhaipz.study.datastructure.service.userinfo;

import com.zhaipz.study.datastructure.dao.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaipz
 * @ClassName: UserInfo
 * @Description: 用户信息测试类
 * @date 2022/1/26 9:07
 */
@Service
@Slf4j
public class UserInfo implements IUserInfo{

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserInfo2 userInfo2;

    @Override
    public Map<String,Object> queryUser(int id) {
        Map<String,Object> map = userInfoMapper.queryUser(id);
        return map;
    }

    @Override
    @Transactional
    public void insertUser() {
        Map<String,Object> map = new HashMap<>();
        map.put("ID","3");
        map.put("NAME","张三");
        map.put("MASTER_SERV_ID","1001");
        map.put("RUN_CODE","A");
        userInfoMapper.insertUserInfo(map);
        try {
            userInfo2.insertUser();
        }catch (Exception e){
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info("出现异常情况：{}",e.getMessage());
        }
    }

}
