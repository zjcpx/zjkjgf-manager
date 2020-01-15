package com.zjkjgf.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjkjgf.common.pojo.EUDataGridResult;
import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.common.utils.IDUtils;
import com.zjkjgf.mapper.UserMapper;
import com.zjkjgf.pojo.User;
import com.zjkjgf.pojo.UserExample;
import com.zjkjgf.pojo.UserExample.Criteria;
import com.zjkjgf.service.IuserService;
import org.springframework.util.DigestUtils;
/**
 * 用户管理Service
 * @author Mike
 *
 */

@Service
public class UserServiceImpl implements IuserService {
	
	@Autowired
	private UserMapper userMapper;

	/**
	 * 获取用户列表
	 */
	
	@Override
	public EUDataGridResult getUserList(int page, int rows) {
		UserExample userExample = new UserExample();
		PageHelper.startPage(page, rows);
		List<User> list = userMapper.selectByExample(userExample);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	/**
	 * 添加用户
	 */

	@Override
	public TaotaoResult CreateUser(User user) {
		Long id = IDUtils.genItemId();
		user.setId(id);
		user.setCreatetime(new Date());
		user.setModifytime(new Date());
		//md5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
		return TaotaoResult.ok();
	}

	/**
	 * 根据用户名查找用户
	 */
	@Override
	public Boolean getUserName(String userName) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		List<User> list = userMapper.selectByExample(example);
		if (list.size() != 0 ) {
			return false;
		}else {
			return true;
		}
	}

	/**
	 * 更新用户的角色和投票权
	 */
	@Override
	public TaotaoResult upDataUser(int role, int voting) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleEqualTo(role);
		List<User> list = userMapper.selectByExample(example);
		for (User user : list) {
			user.setVotingpower(voting);
			user.setModifytime(new Date());
			userMapper.updateByPrimaryKey(user);
		}
		return TaotaoResult.ok();
	}

	/**
	 * 更新用户信息
	 */
	@Override
	public TaotaoResult upDateUserInfo(User user) {
		Long id = user.getId();
		User tempUser = userMapper.selectByPrimaryKey(id);
		Date createtime = tempUser.getCreatetime();
		user.setCreatetime(createtime);
		user.setModifytime(new Date());

		userMapper.updateByPrimaryKey(user);
		return TaotaoResult.ok();
	}

	/**
	 * 删除用户
	 */
	@Override
	public TaotaoResult deleteUser(long id) {
		userMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}

	/**
	 * 用户登录
	 */
	@Override
	public TaotaoResult userLogin(String userName, String password) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		criteria.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
		List<User> list = userMapper.selectByExample(example);
		if (list.size() != 1) {
			return TaotaoResult.ok(false);
		}else {
			return TaotaoResult.ok(list);
		}
		
	}

	/**
	 * 更新投票情况
	 */
	@Override
	public TaotaoResult updataUserVottingInfo(Long id,String votingProject, String vottingRecord,Integer vottingPower) {
		User user = userMapper.selectByPrimaryKey(id);
		user.setVotingrecoder(vottingRecord);
		user.setVotingproject(votingProject);
		user.setVotingpower(vottingPower);
		user.setModifytime(new Date());
		userMapper.updateByPrimaryKey(user);
		return TaotaoResult.ok();
	}

	@Override
	public String getVottedRecorder(User user) {
		String username = user.getUsername();
		String votingproject = user.getVotingproject();
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andVotingprojectEqualTo(votingproject);
		List<User> list = userMapper.selectByExample(userExample);
		if(list.size() == 1) {
			User user2 = new User();
			return user2.getVotingrecoder();
		}else {
			return "error";
		}
		
	}

}
