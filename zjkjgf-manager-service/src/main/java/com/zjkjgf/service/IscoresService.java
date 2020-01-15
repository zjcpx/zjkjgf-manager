package com.zjkjgf.service;

import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.pojo.Scores;

public interface IscoresService {

	TaotaoResult createScores(Scores scores);
	Long checkUser(Scores scores);
	TaotaoResult updataScores(Scores scores);
	Integer getScores(String projectName,String type);
}
