package com.zjkjgf.service;

import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.pojo.Vottingrecorder;

public interface IvottingRecorder {

	TaotaoResult checkUserAanProject(Vottingrecorder vottingrecorder);
	TaotaoResult createVottingRecorder(Vottingrecorder vottingrecorder);
	TaotaoResult updateVottingRecorder(Vottingrecorder vottingrecorder);
	String getVottedRecord(Vottingrecorder recorder);
	
}
