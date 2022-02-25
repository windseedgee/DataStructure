package com.zhaipz.study.datastructure.service.userinfo;

import com.zhaipz.study.datastructure.dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class UserInfo2 implements IUserInfo{

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void insertUser() {
        Map<String,Object> map = new HashMap<>();
        map.put("ID","4");
        map.put("NAME","李四");
        map.put("MASTER_SERV_ID","1001");
        map.put("RUN_CODE","Q");
        userInfoMapper.insertUserInfo(map);
        throw new RuntimeException("人为引起的失败");
    }

    @Override
    public Map<String, Object> queryUser(int id) {
        return null;
    }
}
