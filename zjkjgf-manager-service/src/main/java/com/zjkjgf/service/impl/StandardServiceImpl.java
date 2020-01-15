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
import com.zjkjgf.mapper.StandardMapper;
import com.zjkjgf.pojo.Standard;
import com.zjkjgf.pojo.StandardExample;
import com.zjkjgf.pojo.StandardExample.Criteria;
import com.zjkjgf.service.IstandardService;



/**
 * 
* @ClassName: StandardServiceImpl 
* @Description: TODO:评分管理Service
* @author zjcpx E_Mail:zjcpx@hotmail.com
* @date 2019年12月22日 下午1:32:16 
*
 */
@Service
public class StandardServiceImpl implements IstandardService {

	@Autowired
	private StandardMapper standardMapper;
	
	/**
	 * 获取项目列表
	 */
	@Override
	public EUDataGridResult getStandardList(int page, int rows) {
		StandardExample example = new StandardExample();
		 Criteria criteria = example.createCriteria();
		 PageHelper.startPage(page, rows);
		 List<Standard> list = standardMapper.selectByExample(example);
		 EUDataGridResult result = new EUDataGridResult();
		 result.setRows(list);
		 PageInfo<Standard> info = new PageInfo<Standard>(list);
		 result.setTotal(info.getTotal());
		 return result;
	}

	/**
	 * 判断标准名是否重复
	 */
	@Override
	public Boolean getStandardName(String standardName) {
		StandardExample example = new StandardExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(standardName);
		List<Standard> list = standardMapper.selectByExample(example);
		if (list.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 增加评分标准
	 */
	
	@Override
	public TaotaoResult createStandard(Standard standard) { 
		long id = IDUtils.genItemId();
		standard.setId(id);
		standard.setCreatetime(new Date());
		standard.setModifytime(new Date());
		standardMapper.insert(standard);
		return TaotaoResult.ok();
	}
	

	/**
	 * 删除评分标准
	 */

	@Override
	public TaotaoResult deleteStandard(long id) {
		standardMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}

	/**
	 * 更新评分标准
	 */
	@Override
	public TaotaoResult updataStandard(Standard standard) {
		long id = standard.getId();
		Standard standard2 = standardMapper.selectByPrimaryKey(id);
		Date createTime = standard2.getCreatetime();
		standard.setCreatetime(createTime);
		standard.setModifytime(new Date());
		standardMapper.updateByPrimaryKey(standard);
		return TaotaoResult.ok();
	}

	/**
	 * 获取评分标准（无分页）
	 */
	@Override
	public TaotaoResult getStandardList2() {
		StandardExample example = new StandardExample();
		List<Standard> list = standardMapper.selectByExample(example);
		return TaotaoResult.ok(list);
	}
	
	

}
