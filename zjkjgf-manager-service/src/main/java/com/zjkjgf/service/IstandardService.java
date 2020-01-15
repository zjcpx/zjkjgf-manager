package com.zjkjgf.service;

import com.zjkjgf.common.pojo.EUDataGridResult;
import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.pojo.Standard;



public interface IstandardService {

	EUDataGridResult getStandardList(int page,int rows);
	Boolean getStandardName(String standardName);
	TaotaoResult createStandard(Standard standard);
	TaotaoResult deleteStandard(long id);
	TaotaoResult updataStandard(Standard standard);
	TaotaoResult getStandardList2();

	
}
