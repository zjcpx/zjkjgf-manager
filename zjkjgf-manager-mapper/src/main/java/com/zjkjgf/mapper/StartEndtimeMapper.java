package com.zjkjgf.mapper;

import com.zjkjgf.pojo.StartEndtime;
import com.zjkjgf.pojo.StartEndtimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StartEndtimeMapper {
    int countByExample(StartEndtimeExample example);

    int deleteByExample(StartEndtimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StartEndtime record);

    int insertSelective(StartEndtime record);

    List<StartEndtime> selectByExample(StartEndtimeExample example);

    StartEndtime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StartEndtime record, @Param("example") StartEndtimeExample example);

    int updateByExample(@Param("record") StartEndtime record, @Param("example") StartEndtimeExample example);

    int updateByPrimaryKeySelective(StartEndtime record);

    int updateByPrimaryKey(StartEndtime record);
}