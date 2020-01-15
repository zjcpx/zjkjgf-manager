package com.zjkjgf.service;

import com.zjkjgf.common.pojo.EUDataGridResult;
import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.pojo.Project;


public interface IprojectService {

	EUDataGridResult getPorjectList(int page,int rows);
	Boolean getProjectName(String projectName);
	TaotaoResult createProject(Project project);
	TaotaoResult deleteProject(long id);
	TaotaoResult updataProject(Project project);
	TaotaoResult getPorjectList2();
}
