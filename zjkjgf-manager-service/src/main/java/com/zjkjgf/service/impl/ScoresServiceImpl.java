package com.zjkjgf.service.impl;

import java.util.Date;
import java.util.List;

import org.aspectj.weaver.ast.Var;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.common.utils.IDUtils;
import com.zjkjgf.mapper.ScoresMapper;
import com.zjkjgf.pojo.Scores;
import com.zjkjgf.pojo.ScoresExample;
import com.zjkjgf.pojo.ScoresExample.Criteria;
import com.zjkjgf.service.IscoresService;


@Service
public class ScoresServiceImpl implements IscoresService {

	@Autowired
	private ScoresMapper scoresMapper;
	@Override
	public TaotaoResult createScores(Scores scores) {
		scores.setId(IDUtils.genItemId());
		scores.setCreatetime(new Date());
		scores.setModifytime(new Date());
		scoresMapper.insert(scores);
		return TaotaoResult.ok();
	}
	
	@Override
	public Long checkUser(Scores scores) {
		String vottingperson  = scores.getVottingperson();
		String matchproject = scores.getMatchproject();
		ScoresExample example = new ScoresExample();
		Criteria criteria = example.createCriteria();
		criteria.andVottingpersonEqualTo(vottingperson);
		criteria.andMatchprojectEqualTo(matchproject);
		List<Scores> list = scoresMapper.selectByExample(example);
		if (list.size() == 0) {
			return (long) -1;
		}else {
			return list.get(0).getId();
			
		}
		
	}

	@Override
	public TaotaoResult updataScores(Scores scores) {
		long id = scores.getId();
		Scores scores2 = scoresMapper.selectByPrimaryKey(id);
		Date createtime = scores2.getCreatetime();
		scores.setCreatetime(createtime);
		scores.setModifytime(new Date());
		scoresMapper.updateByPrimaryKey(scores);
		return TaotaoResult.ok();
	}

	@Override
	public Integer getScores(String projectName,String type) {
		ScoresExample example = new ScoresExample();
		Criteria criteria = example.createCriteria();
		criteria.andMatchprojectEqualTo(projectName);
		List<Scores> list = scoresMapper.selectByExample(example);
		Scores scores = new Scores();
		int total = 0;
		if(type.equals("special")) {
			for (int i = 0,len = list.size(); i < len; i++) {
				scores = list.get(i);
				total += scores.getSpecialistscores();
			}
			
		}else {
			
			for (int i = 0,len = list.size(); i < len; i++) {
				scores = list.get(i);
				total += scores.getHotscores();
			}
		}
		return total;
		
	}

}
