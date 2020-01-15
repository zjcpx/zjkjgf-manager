package com.zjkjgf.mapper;

import com.zjkjgf.pojo.Scores;
import com.zjkjgf.pojo.ScoresExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScoresMapper {
    int countByExample(ScoresExample example);

    int deleteByExample(ScoresExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Scores record);

    int insertSelective(Scores record);

    List<Scores> selectByExample(ScoresExample example);

    Scores selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Scores record, @Param("example") ScoresExample example);

    int updateByExample(@Param("record") Scores record, @Param("example") ScoresExample example);

    int updateByPrimaryKeySelective(Scores record);

    int updateByPrimaryKey(Scores record);
}