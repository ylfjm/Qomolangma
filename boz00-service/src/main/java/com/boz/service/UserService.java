/*
 * Creation : 2016年8月2日
 */
package com.boz.service;

import com.boz.common.utils.CommonResult;
import com.boz.pojo.BozTUser;

public interface UserService {

    CommonResult checkData(String content, Integer type);

    CommonResult createUser(BozTUser user);

    CommonResult userLogin(String username, String password);

    CommonResult getUserByToken(String token);

}
