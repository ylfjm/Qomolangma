/*
 * Creation : 2016年8月2日
 */
package com.boz.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boz.common.utils.CommonResult;
import com.boz.pojo.BozTUser;

public interface UserService {

    CommonResult checkData(String content, Integer type);

    CommonResult createUser(BozTUser user);

    CommonResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

    CommonResult getUserByToken(String token);

    CommonResult userLogout(String token, HttpServletRequest request, HttpServletResponse response);

}
