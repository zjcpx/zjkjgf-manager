package com.zjkjgf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjkjgf.common.pojo.EUDataGridResult;
import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.pojo.User;
import com.zjkjgf.service.IuserService;

/**
 * 用户管理Controller
 * @author Mike
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private IuserService userService;
	
	/**
	 * 查询用户列表
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/user/list")
	@ResponseBody
	public EUDataGridResult getUserList(Integer page,Integer rows) {
		EUDataGridResult result = userService.getUserList(page, rows);
		return result;
	}
	
	/**
	 * 查询用户名是否已经被占用
	 */
	@RequestMapping("/user/usernameCheck")
	@ResponseBody
	public TaotaoResult ifUsername(String userName) {
		if(userService.getUserName(userName)) {
			return TaotaoResult.build(200, "");
		}else{
			return TaotaoResult.build(500, "用户名已经被注册");
		}
	}
	
	/**
	 * 增加用户
	 */
	@RequestMapping("/user/save")
	@ResponseBody
	public TaotaoResult createUser(User user) {
		TaotaoResult result = userService.CreateUser(user);
		return result;
	}
	
	/**
	 * 修改用户的投票权
	 * 
	 */
	@RequestMapping("/user/changeVotingPower")
	@ResponseBody
	public TaotaoResult changeVotingPower(Integer role,Integer votingPower) {
		TaotaoResult result = userService.upDataUser(role, votingPower);
		return result;
	}
	
	/**
	 * 更新用户信息
	 */
	@RequestMapping(value = "/user/changeInfo",method=RequestMethod.POST)
	@ResponseBody
	
	public TaotaoResult changeUserInfo(User user) {
		TaotaoResult result = userService.upDateUserInfo(user);
		return result;
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(value = "/user/delete",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteUser(Long id) {
		TaotaoResult result = userService.deleteUser(id);
		return result;
	}
	
	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/user/login",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult userLogin(String username,String password) {
		TaotaoResult result = userService.userLogin(username, password);
		return result;
	}
	
	/**
	 * 更新用户投票信息
	 */
	@RequestMapping(value = "/user/changeVottingInfo",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updataVottingInfo(Long id,String votingProject,String vottingRecord, int vottingPower) {
		TaotaoResult result = userService.updataUserVottingInfo(id, votingProject, vottingRecord,vottingPower);
		return result;
	}
	
	/**
	 * 获取专家投票记录
	 */
	@RequestMapping(value = "/user/getScores",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult getVottedRecorder(String vottingperson,String projectName){
		User user = new User();
		user.setUsername(vottingperson);
		user.setVotingproject(projectName);
		String recorder = userService.getVottedRecorder(user);
		return TaotaoResult.ok(recorder);
	}
	
}
