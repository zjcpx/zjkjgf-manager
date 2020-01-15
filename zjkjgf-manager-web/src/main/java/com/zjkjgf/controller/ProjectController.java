package com.zjkjgf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjkjgf.common.pojo.EUDataGridResult;
import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.pojo.Project;
import com.zjkjgf.service.IprojectService;

/**
 * 项目管理Controller
 * @author Mike
 *
 */
@Controller
public class ProjectController {

	@Autowired
	private IprojectService projectService;
	
	/**
	 * 获取项目列表
	 */
	
	@RequestMapping("/project/list")
	@ResponseBody
	public EUDataGridResult getProjectList(Integer page,Integer rows) {
		EUDataGridResult result = projectService.getPorjectList(page, rows);
		return result;
	}
	/**
	 * 获取项目列表
	 */
	
	@RequestMapping("/project/list2")
	@ResponseBody
	public TaotaoResult getProjectList() {
		TaotaoResult result = projectService.getPorjectList2();
		return result;
	}
	
	/**
	 * 判断项目名称是否已经存在
	 */
	@RequestMapping(value = "/user/projectNameCheck",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult checkProjectName(String projectName) {
		if(projectService.getProjectName(projectName)) {
			return TaotaoResult.ok();
		}
		return TaotaoResult.build(500,"项目名称已经存在");
	}
	
	/**
	 * 新增项目
	 */
	@RequestMapping("/project/save")
	@ResponseBody
	public TaotaoResult createProject(Project project) {
		TaotaoResult result = projectService.createProject(project);
		return result;
	}
	
	/**
	 * 删除比赛项目
	 */
	@RequestMapping(value = "/porject/delete",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteProject(Long id) {
		TaotaoResult result = projectService.deleteProject(id);
		return result;
	}
	
	@RequestMapping(value = "/project/update",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updataProject(Project project) {
		TaotaoResult result = projectService.updataProject(project);
		return result;
	}
}
