package com.jt.manage.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.service.ItemCatService;
import com.jt.manage.service.ItemService;
import com.jt.manage.vo.EasyUITree;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

//	private static final Logger logger= Logger.getLogger(ItemCatController.class);
	@Autowired
	private ItemCatService itemCatService;

	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITree> findItemCatList(
			@RequestParam(defaultValue="0",//默认值,
			required=true,//true 参数必须要有，
			value="id"	//
			) Long parentId){
		//查询一级商品分类目录
//		Long parentId=0L;
		List<EasyUITree> treeList= itemCatService.findItemCatCache(parentId);
		return treeList;
	}
	

	
}
