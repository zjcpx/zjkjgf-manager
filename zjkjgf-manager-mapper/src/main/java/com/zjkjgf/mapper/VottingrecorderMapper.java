package com.zjkjgf.mapper;

import com.zjkjgf.pojo.Vottingrecorder;
import com.zjkjgf.pojo.VottingrecorderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VottingrecorderMapper {
    int countByExample(VottingrecorderExample example);

    int deleteByExample(VottingrecorderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Vottingrecorder record);

    int insertSelective(Vottingrecorder record);

    List<Vottingrecorder> selectByExample(VottingrecorderExample example);

    Vottingrecorder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Vottingrecorder record, @Param("example") VottingrecorderExample example);

    int updateByExample(@Param("record") Vottingrecorder record, @Param("example") VottingrecorderExample example);

    int updateByPrimaryKeySelective(Vottingrecorder record);

    int updateByPrimaryKey(Vottingrecorder record);
}