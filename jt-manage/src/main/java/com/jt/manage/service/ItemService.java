package com.jt.manage.service;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

public interface ItemService {

	EasyUIResult findItemByPage(Integer page, Integer rows);

	String findItemCatNameById(Long itemId);

	void saveItem(Item item, String desc);

	void updateItem(Item item,String desc);

	void deleteItem(Long[] ids);

	void updatestatus(@Param("ids")Long[] ids, @Param("status")int status);

	ItemDesc findItemDescById(Long itemId);

	Item findItemById(Long itemId);

}
