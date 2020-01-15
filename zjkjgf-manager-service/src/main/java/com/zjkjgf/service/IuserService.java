package com.zjkjgf.service;

import java.util.List;

import com.zjkjgf.common.pojo.EUDataGridResult;
import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.pojo.User;

public interface IuserService {
	
	EUDataGridResult getUserList(int page,int rows);
	TaotaoResult CreateUser(User user);
	Boolean getUserName(String userName);
	TaotaoResult upDataUser(int role,int voting);
	TaotaoResult upDateUserInfo(User user);
	TaotaoResult deleteUser(long id);
	TaotaoResult userLogin(String userName,String password);
	TaotaoResult updataUserVottingInfo(Long id,String votingProject, String vottingRecord,Integer vottingPower);
	String getVottedRecorder(User user);

}
