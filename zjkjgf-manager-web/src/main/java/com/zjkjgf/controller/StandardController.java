package com.zjkjgf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjkjgf.common.pojo.EUDataGridResult;
import com.zjkjgf.common.pojo.TaotaoResult;
import com.zjkjgf.pojo.Standard;
import com.zjkjgf.service.IstandardService;

/**
 * 
* @ClassName: StandardController 
* @Description: TODO(评分标准管理Controller) 
* @author zjcpx E_Mail:zjcpx@hotmail.com
* @date 2019年12月22日 下午1:38:06 
*
 */

@Controller
public class StandardController {

	@Autowired
	private IstandardService standardService;
	
	/**
	 * 
	* @Title: getStandardList 
	* @Description: TODO(获取评分标准列表) 
	* @param @param page
	* @param @param rows
	* @param @return    设定文件 
	* @return EUDataGridResult    返回类型 
	* @throws
	 */
	
	@RequestMapping("/standard/list")
	@ResponseBody
	public EUDataGridResult getStandardList(Integer page,Integer rows) {
		EUDataGridResult list = standardService.getStandardList(page, rows);
		return list;
	}
	
	/**
	 * 
	* @Title: standardCheck 
	* @Description: TODO(检查标准是否已经存在) 
	* @param @param standardName
	* @param @return    设定文件 
	* @return TaotaoResult    返回类型 
	* @throws
	 */

	@RequestMapping(value = "/standard/nameCheck",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult standardCheck(String standardName) { 
		if(standardService.getStandardName(standardName)) { 
			return TaotaoResult.ok(); 
		}
	 return TaotaoResult.build(500, "评分标准名称已经存在");
	}
	 
	

	/**
	 * 
	 * 
	* @Title: createStandard 
	* @Description: TODO(增加评分标准) 
	* @param @param standard
	* @param @return    设定文件 
	* @return TaotaoResult    返回类型 
	* @throws
	 */


	@RequestMapping(value = "/standard/save",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createStandard(Standard standard) {
		TaotaoResult result = standardService.createStandard(standard);
		return result;
	}

	/**
	 * 
	* @Title: deleteStandard 
	* @Description: TODO(删除评分标准) 
	* @param @param id
	* @param @return    设定文件 
	* @return TaotaoResult    返回类型 
	* @throws
	 */

	@RequestMapping(value = "/standard/delete",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteStandard(Long id) {
		standardService.deleteStandard(id);
		return TaotaoResult.ok();
	}
	
	
	@RequestMapping(value = "/standard/update",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updataStandard(Standard standard) {
		TaotaoResult result = standardService.updataStandard(standard);
		return result;
	}
	 
	/**
	 * 获取无分页的评分列表
	 */
	@RequestMapping("/standard/list2")
	@ResponseBody
	public TaotaoResult getStandardList2() {
		TaotaoResult result = standardService.getStandardList2();
		return result;
	}
}
