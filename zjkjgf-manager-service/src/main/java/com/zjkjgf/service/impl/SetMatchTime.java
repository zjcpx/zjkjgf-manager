package com.zjkjgf.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.mapper.StartEndtimeMapper;
import com.zjkjgf.pojo.StartEndtime;
import com.zjkjgf.pojo.StartEndtimeExample;
import com.zjkjgf.pojo.StartEndtimeExample.Criteria;
import com.zjkjgf.service.ImatchTimeService;

/**
 * @author Mike
 *	比赛开始时间Service
 */
@Service
public class SetMatchTime implements ImatchTimeService {
	
	@Autowired
	private StartEndtimeMapper matchMapper;
	
	

	/**
	 * 设置比赛时间
	 */
	@Override
	public TaotaoResult setMatchTime(Date startTime, Date endTime) {
		StartEndtime startEndtime = new StartEndtime();
		startEndtime.setId(100);
		startEndtime.setStarttime(startTime);
		startEndtime.setEndtime(endTime);
		matchMapper.insert(startEndtime);
		return TaotaoResult.ok();
	}

	/**
	 * 删除之前已经存在的时间
	 */
	@Override
	public TaotaoResult delMatchTIme() {
		if (matchMapper.selectByPrimaryKey(100) != null) {
			matchMapper.deleteByPrimaryKey(100);
			return TaotaoResult.ok();
		}
		return TaotaoResult.ok();
	}

	/**
	 * 获取设定的开始时间
	 */
	@Override
	public Date getStartTime() {
		StartEndtime startEndtime = matchMapper.selectByPrimaryKey(100);
		Date date = startEndtime.getStarttime();
		return date;
	}
	
	/**
	 * 获取设定的结束时间
	 */
	@Override
	public Date getEndTime() {
		StartEndtime startEndtime = matchMapper.selectByPrimaryKey(100);
		Date date = startEndtime.getEndtime();
		return date;
	}

}
