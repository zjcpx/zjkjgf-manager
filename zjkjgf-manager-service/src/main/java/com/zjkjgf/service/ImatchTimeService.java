package com.zjkjgf.service;


import java.util.Date;

import com.zjkjgf.common.pojo.TaotaoResult;

public interface ImatchTimeService {
	
	TaotaoResult setMatchTime(Date startTime,Date endTime);
	TaotaoResult delMatchTIme();
	Date getStartTime();
	Date getEndTime();

}
