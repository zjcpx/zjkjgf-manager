package com.zjkjgf.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjkjgf.common.pojo.EUDataGridResult;
import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.common.utils.IDUtils;
import com.zjkjgf.mapper.ProjectMapper;
import com.zjkjgf.pojo.Project;
import com.zjkjgf.pojo.ProjectExample;
import com.zjkjgf.pojo.ProjectExample.Criteria;
import com.zjkjgf.service.IprojectService;

/**
 * 比赛项目管理Service
 * @author Mike
 *
 */

@Service
public class ProjectServiceImpl implements IprojectService {
	
	@Autowired
	private ProjectMapper projectMapper;
	/**
	 * 获取项目列表
	 */
	@Override
	public EUDataGridResult getPorjectList(int page, int rows) {
		ProjectExample example = new ProjectExample();
		List<Project> list = projectMapper.selectByExample(example);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		PageHelper.startPage(page, rows);
		PageInfo<Project> info = new PageInfo<Project>(list);
		result.setTotal(info.getTotal());
		return result;
	}
	
	/**
	 * 
	 * 判断项目名称是否已经存在
	 */
	@Override
	public Boolean getProjectName(String projectName) {
		ProjectExample example = new ProjectExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjectnameEqualTo(projectName);
		List<Project> list = projectMapper.selectByExample(example);
		if (list.size() != 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 新增项目
	 */

	@Override
	public TaotaoResult createProject(Project project) {
		Long id = IDUtils.genItemId();
		project.setId(id);
		project.setCreatetime(new Date());
		project.setModifytime(new Date());
		projectMapper.insert(project);
		return TaotaoResult.ok();
	}

	/**
	 * 删除参赛项目
	 */
	@Override
	public TaotaoResult deleteProject(long id) {
		projectMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}

	/**
	 * 更新比赛项目信息
	 */
	@Override
	public TaotaoResult updataProject(Project project) {
		long id = project.getId();
		Project project2 = projectMapper.selectByPrimaryKey(id);
		Date createTime = project2.getCreatetime();
		project.setCreatetime(createTime);
		project.setModifytime(new Date());
		projectMapper.updateByPrimaryKey(project);
		return TaotaoResult.ok();
	}

	/**
	 * 获取项目列表（无分页）
	 */
	@Override
	public TaotaoResult getPorjectList2() {
		ProjectExample example = new ProjectExample();
		List<Project> list = projectMapper.selectByExample(example);
		
		return TaotaoResult.ok(list);
	}

}
