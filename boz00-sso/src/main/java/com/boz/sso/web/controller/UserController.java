/*
 * Creation : 2016年8月2日
 */
/**
 * 
 */
package com.boz.sso.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boz.common.utils.CommonResult;
import com.boz.common.utils.ExceptionUtil;
import com.boz.pojo.BozTUser;
import com.boz.service.UserService;

/**
 * UserController.
 * 
 * @author E470756
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
        CommonResult result = null;
        // 参数有效性校验
        if (StringUtils.isBlank(param)) {
            result = CommonResult.build(400, "校验内容不能为空");
        }
        if (type == null) {
            result = CommonResult.build(400, "校验内容类型不能为空");
        }
        if (type != 1 && type != 2 && type != 3) {
            result = CommonResult.build(400, "校验内容类型错误");
        }
        // 校验出错
        if (null != result) {
            if (null != callback) {
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }
            return result;
        }
        // 调用服务
        try {
            result = userService.checkData(param, type);
        } catch (Exception e) {
            result = CommonResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        if (null != callback) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return result;
    }

    @RequestMapping("/user/register")
    public CommonResult createUser(BozTUser user) {
        try {
            return userService.createUser(user);
        } catch (Exception e) {
            return CommonResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult userLogin(String username, String password) {
        try {
            return userService.userLogin(username, password);
        } catch (Exception e) {
            return CommonResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/user/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback) {
        CommonResult result = null;
        try {
            result = userService.getUserByToken(token);
        } catch (Exception e) {
            result = CommonResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        // 判断是否为jsonp调用
        if (StringUtils.isBlank(callback)) {
            return result;
        }
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;

    }

}
