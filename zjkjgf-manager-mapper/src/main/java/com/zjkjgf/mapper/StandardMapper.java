package com.zjkjgf.mapper;

import com.zjkjgf.pojo.Standard;
import com.zjkjgf.pojo.StandardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StandardMapper {
    int countByExample(StandardExample example);

    int deleteByExample(StandardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Standard record);

    int insertSelective(Standard record);

    List<Standard> selectByExample(StandardExample example);

    Standard selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Standard record, @Param("example") StandardExample example);

    int updateByExample(@Param("record") Standard record, @Param("example") StandardExample example);

    int updateByPrimaryKeySelective(Standard record);

    int updateByPrimaryKey(Standard record);
}