package com.zjkjgf.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Data;
import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.common.utils.IDUtils;
import com.zjkjgf.mapper.VottingrecorderMapper;
import com.zjkjgf.pojo.Vottingrecorder;
import com.zjkjgf.pojo.VottingrecorderExample;
import com.zjkjgf.pojo.VottingrecorderExample.Criteria;
import com.zjkjgf.service.IvottingRecorder;

@Service
public class VottingRecorderImpl implements IvottingRecorder {

	@Autowired
	private VottingrecorderMapper vottingMapper;
	
	@Override
	public TaotaoResult checkUserAanProject(Vottingrecorder vottingrecorder) {
		String userName = vottingrecorder.getUsername();
		String projectName = vottingrecorder.getVotingproject();
		
		VottingrecorderExample example = new VottingrecorderExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		criteria.andVotingprojectEqualTo(projectName);
		List<Vottingrecorder> list = vottingMapper.selectByExample(example);
		if (list.size() == 0) {
			return TaotaoResult.ok();
		}
		Vottingrecorder vottingrecorder2 = new Vottingrecorder();
		vottingrecorder2 = list.get(0);
		Long id = vottingrecorder2.getId();
		return TaotaoResult.ok(vottingrecorder2);
	}
	
	@Override
	public TaotaoResult createVottingRecorder(Vottingrecorder vottingrecorder) {
		vottingrecorder.setCreatetime(new Date());
		vottingrecorder.setModifytime(new Date());
		vottingrecorder.setId(IDUtils.genItemId());
		vottingMapper.insert(vottingrecorder);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateVottingRecorder(Vottingrecorder vottingrecorder) {
		Long id = vottingrecorder.getId();
		
		vottingrecorder.setModifytime(new Date());

		vottingMapper.updateByPrimaryKey(vottingrecorder);
		
		return TaotaoResult.ok();
	}

	//获取投票纪录
	@Override
	public String getVottedRecord(Vottingrecorder recorder) {
		String username = recorder.getUsername();
		String votingproject = recorder.getVotingproject();
		VottingrecorderExample example = new VottingrecorderExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andVotingprojectEqualTo(votingproject);
		List<Vottingrecorder> list = vottingMapper.selectByExample(example);
		if (list.size() == 1) {
			Vottingrecorder vottingrecorder = new Vottingrecorder();
			vottingrecorder = list.get(0);
			return vottingrecorder.getScoreslist();
		}else {
			
			return "error";
		}
	}
	

}
