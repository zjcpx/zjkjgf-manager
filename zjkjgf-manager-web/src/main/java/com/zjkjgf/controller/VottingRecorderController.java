package com.zjkjgf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.pojo.Vottingrecorder;
import com.zjkjgf.service.IvottingRecorder;

@Controller
public class VottingRecorderController {
	
	@Autowired IvottingRecorder vottingRecoderServices;
	
	@RequestMapping( value = "/vottingRecorder/modify",method = RequestMethod.POST)
	@ResponseBody
	
	public TaotaoResult modifyVottingRecorder(String username, String votingproject,String scoreslist) {
		Vottingrecorder vottingrecorder = new Vottingrecorder();
		vottingrecorder.setUsername(username);
		vottingrecorder.setVotingproject(votingproject);
		vottingrecorder.setScoreslist(scoreslist);
		TaotaoResult result = vottingRecoderServices.checkUserAanProject(vottingrecorder);
		if (result.getData() == null){
			vottingRecoderServices.createVottingRecorder(vottingrecorder);
		}else {
			Vottingrecorder v2= (Vottingrecorder)result.getData();
			vottingrecorder.setId(v2.getId());
			vottingrecorder.setCreatetime(v2.getCreatetime());
			vottingRecoderServices.updateVottingRecorder(vottingrecorder);
		}
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value = "/vottedRecoder/getScores",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult getVottedRecorder(String vottingperson,String projectName) {
		Vottingrecorder vottingrecorder = new Vottingrecorder();
		vottingrecorder.setUsername(vottingperson);
		vottingrecorder.setVotingproject(projectName);
		String record = vottingRecoderServices.getVottedRecord(vottingrecorder);
		return TaotaoResult.ok(record);
	}
	

}
