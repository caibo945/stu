package com.jt.manage.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

//	private static final Logger logger= Logger.getLogger(ItemCatController.class);
	@Autowired
	private ItemService itemService;
	
	//http://localhost:8091/item/query?page=1&rows=20
	@RequestMapping("/query")
	@ResponseBody	//将我的对象转化为JSON串
	public EasyUIResult findItemByPage(Integer page,Integer rows){
		EasyUIResult easyUIResult = itemService.findItemByPage(page,rows);
		return easyUIResult;
	}
	/**
	 * @ResponseBody解析说明 如果解析的是对象，则默认使用UTF-8编码
	 * 如果解析的是String字符串，则默认使用的ISO-8859-1编码
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/cat/queryItemName",produces="html/text;charset=utf-8")
	@ResponseBody
	public String findItemNameById(Long itemId){
		return itemService.findItemCatNameById(itemId);
	}
	
//	public void findItemNameById(Long itemId,HttpServletResponse response){//通过response处理乱码问题
//		String name = itemService.findItemCatNameById(itemId);
//		response.setContentType("html/text;charset=utf-8");
//		try {
//			response.getWriter().write(name);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveItem(Item item,String desc){
		try {
			itemService.saveItem(item,desc);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SysResult.build(201, "新增商品失败");
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc){
		try {
			itemService.updateItem(item,desc);
			//logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("记录程序执行状态");
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SysResult.build(201, "修改商品失败");
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteItem(Long[] ids){
		try {
			itemService.deleteItem(ids);
		
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "删除商品失败");
	}
	
	@RequestMapping("/instock")
	@ResponseBody
	public SysResult instock(Long[] ids){
		try {
			int status=2;
			itemService.updatestatus(ids,status);
			
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "下架失败");
	}
	@RequestMapping("/reshelf")
	@ResponseBody
	public SysResult reshelf(Long[] ids){
		try {
			int status=1;
			itemService.updatestatus(ids,status);
			
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "上架失败");
	}
	
	@RequestMapping("/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult findItemDescById(@PathVariable Long itemId){
		try {
			ItemDesc itemDesc = itemService.findItemDescById(itemId);
			return SysResult.oK(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品详细查询失败~");
	}
}
