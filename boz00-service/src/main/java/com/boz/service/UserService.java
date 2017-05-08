/*
 * Creation : 2016年8月2日
 */
package com.boz.service;

import com.boz.common.utils.CommonResult;
import com.boz.model.BozUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    CommonResult checkData(String content, Integer type);

    CommonResult createUser(BozUser user);

    CommonResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

    CommonResult getUserByToken(String token);

    CommonResult userLogout(String token, HttpServletRequest request, HttpServletResponse response);

}
