package com.zjkjgf.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.service.ImatchTimeService;

@Controller
public class TimeController {

	@Autowired
	private ImatchTimeService timeService;
	
	@RequestMapping("/time/set")
	@ResponseBody
	public TaotaoResult setMatchTime(String startTime, String endTime) {
		//先删除之前设定的时间
		timeService.delMatchTIme();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = null;
	    Date end = null; 
	         try {
	        	start = df.parse(startTime);
	        	end = df.parse(endTime);
				
	        	timeService.setMatchTime(start, end);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
				return TaotaoResult.build(400, "时间设置失败");
			}
		return TaotaoResult.build(200, "时间设定成功");
	}
	
	@RequestMapping(value = "/time/isEffective",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult getStartTime(Date date) {
		Date startTime = timeService.getStartTime();
		Date endingTime = timeService.getEndTime();
		if(date.after(startTime) && date.before(endingTime)) {
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}
	
}
