package com.zjkjgf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.pojo.Scores;
import com.zjkjgf.service.IscoresService;

@Controller
public class ScoresController<E> {

	@Autowired
	private IscoresService scoresService;
	
	@RequestMapping(value = "/scores/createHote",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createScores(String vottingperson, String projectMap) {
		//一部参赛项目: [3]/二部参赛项目: [1]/三部参赛项目: [1]/四部参赛项目: [1]/五部参赛项目: [1]/六部参赛项目: [1]/七部参赛项目: [1]/八部参赛项目: [1]/联队参赛项目: [1]
		Map<String, Integer> scoresMap = new HashMap<String, Integer>();
		
		String[] scoresArray = projectMap.split("/");
		for (int i = 0; i < scoresArray.length; i++) {
			//一部参赛项目: [3]
			String[] tempArray = scoresArray[i].split(": ");
			String valueString = tempArray[1].replace("[", "");
			valueString = valueString.replace("]", "");
			int a = Integer.parseInt(valueString);
			scoresMap.put(tempArray[0],a);
		}
		
		for(String key : scoresMap.keySet()) {
			Scores scores = new Scores();
			scores.setVottingperson(vottingperson);
			scores.setMatchproject(key);
			scores.setSpecialistscores(0);
			scores.setHotscores(scoresMap.get(key));
			Long userID = scoresService.checkUser(scores);
			if (userID == -1) {
				scoresService.createScores(scores);
			}else {
				scores.setId(userID);
				scoresService.updataScores(scores);
				
			}
		}
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value = "/scores/createSpecial",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createSpecialScores(String vottingperson, String projectName,String specialRecorder) {
		//将前台传递的投票成绩封装成一个Scores对象
		Scores scores = new Scores();
		scores.setVottingperson(vottingperson);
		scores.setMatchproject(projectName);
		scores.setSpecialistscores(Integer.parseInt(specialRecorder));
		scores.setHotscores(0);
		Long id = scoresService.checkUser(scores);
		if(id == -1) {
			scoresService.createScores(scores);
		}else{
			scores.setId(id);
			scoresService.updataScores(scores);
		};
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value = "/scores/getScores",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult getScors(String projectNameList,String type){
		String[] projects = projectNameList.split(",");
		List<Integer> array=new ArrayList<Integer>();
 		for (int i = 0,len = projects.length; i < len; i++ ) {
 			Integer result = scoresService.getScores(projects[i], type);
			array.add(result);
		}
		
			return TaotaoResult.ok(array);
		
	}
}
