package com.jt.manage.mappper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jt.common.mapper.SysMapper;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;

public interface ItemMapper extends SysMapper<Item>{

	int findItemCount();
	
	/**
	 * 关于Mybatis中参数问题
	 * Mybatis中不允许进行多值传参
	 * 思路：将多值转化为单值
	 * 解决：1.使用对象封装数据 做新增/修改
	 * 		2.使用Map<k,v>进行数据封装  多条件查询时或者参数不是pojo的属性
	 * 		3.Array/List 
	 * @param start
	 * @param rows
	 * @return List<Item>
	 */
	List<Item> findItemListPage(@Param("start") int start,@Param("rows") Integer rows);

	@Select("select name from tb_item_cat where id=#{itemId}")
	String findItemCatNameById(Long itemId);
	
	void updatestatus(@Param("ids")Long[] ids, @Param("status")int status);
}
