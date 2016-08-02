/*
 * Creation : 2016年8月2日
 */
package com.boz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import com.boz.common.utils.CommonResult;
import com.boz.common.utils.JsonUtils;
import com.boz.dao.redis.JedisClient;
import com.boz.mapper.BozTUserMapper;
import com.boz.pojo.BozTUser;
import com.boz.pojo.BozTUserExample;
import com.boz.pojo.BozTUserExample.Criteria;
import com.boz.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BozTUserMapper userMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private int SSO_SESSION_EXPIRE;

    @Override
    public CommonResult checkData(String content, Integer type) {
        // 创建查询条件
        BozTUserExample example = new BozTUserExample();
        Criteria criteria = example.createCriteria();
        // 对数据进行校验：1、2、3分别代表username、phone、email
        // 用户名校验
        if (1 == type) {
            criteria.andUsernameEqualTo(content);
            // 电话校验
        } else if (2 == type) {
            criteria.andPhoneEqualTo(content);
            // email校验
        } else {
            criteria.andEmailEqualTo(content);
        }
        // 执行查询
        List<BozTUser> userList = userMapper.selectByExample(example);
        if (userList == null || CollectionUtils.isEmpty(userList)) {
            return CommonResult.ok(true);
        }
        return CommonResult.ok(false);
    }

    @Override
    public CommonResult createUser(BozTUser user) {
        user.setUpdated(new Date());
        user.setCreated(new Date());
        // md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insert(user);
        return CommonResult.ok();
    }

    @Override
    public CommonResult userLogin(String username, String password) {
        BozTUserExample example = new BozTUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<BozTUser> userList = userMapper.selectByExample(example);
        // 如果没有此用户名
        if (null == userList || CollectionUtils.isEmpty(userList)) {
            return CommonResult.build(400, "用户名或密码错误");
        }
        BozTUser user = userList.get(0);
        // 比对密码
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return CommonResult.build(400, "用户名或密码错误");
        }
        // 生成token
        String token = UUID.randomUUID().toString();
        // 保存用户之前，把用户对象中的密码清空。
        user.setPassword(null);
        // 把用户信息写入redis
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
        // 设置session的过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // 返回token
        return CommonResult.ok(token);
    }

    @Override
    public CommonResult getUserByToken(String token) {
        // 根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        // 判断是否为空
        if (StringUtils.isBlank(json)) {
            return CommonResult.build(400, "此session已经过期，请重新登录");
        }
        // 更新过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // 返回用户信息
        return CommonResult.ok(JsonUtils.jsonToPojo(json, BozTUser.class));

    }

}
