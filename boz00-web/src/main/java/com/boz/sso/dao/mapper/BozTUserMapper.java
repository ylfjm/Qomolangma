package com.boz.sso.dao.mapper;

import com.boz.pojo.BozTUser;
import com.boz.pojo.BozTUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BozTUserMapper {
    int countByExample(BozTUserExample example);

    int deleteByExample(BozTUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BozTUser record);

    int insertSelective(BozTUser record);

    List<BozTUser> selectByExample(BozTUserExample example);

    BozTUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BozTUser record, @Param("example") BozTUserExample example);

    int updateByExample(@Param("record") BozTUser record, @Param("example") BozTUserExample example);

    int updateByPrimaryKeySelective(BozTUser record);

    int updateByPrimaryKey(BozTUser record);
}