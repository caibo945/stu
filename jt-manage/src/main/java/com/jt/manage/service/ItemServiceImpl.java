package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mappper.ItemDescMapper;
import com.jt.manage.mappper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUIResult findItemByPage(Integer page, Integer rows) {
		//int total = itemMapper.findItemCount();
		int total = itemMapper.selectCount(null);
		//商品分页后的List集合
		/**
		 * SELECT * FROM tb_item LIMIT 0,20
		   SELECT * FROM tb_item LIMIT 20,20
		   SELECT * FROM tb_item LIMIT 40,20
		   SELECT * FROM tb_item LIMIT (page-1)*rows,20   
		 */
		int start=(page-1)*rows;
		//if(start<0)start=0;
		List<Item> itemList = itemMapper.findItemListPage(start,rows);
		
		return new EasyUIResult(total,itemList);
	}

	@Override
	public String findItemCatNameById(Long itemId) {
		
		return itemMapper.findItemCatNameById(itemId);
		 
	}
	//封装Item数据
	@Override
	public void saveItem(Item item,String desc) {
		item.setStatus(1);	//1，表示上架 正常
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insert(item);
		
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getId());	
		//item.getId() 返回结果为最大的Id数字 查询线程内最大的值然后返回
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	public void updateItem(Item item,String desc) {
		item.setUpdated(new Date());
		//动态更新
		itemMapper.updateByPrimaryKeySelective(item);
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getId());
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}

	@Override
	public void deleteItem(Long[] ids) {
		
		itemMapper.deleteByIDS(ids);		
	}

	@Override
	public void updatestatus(Long[] ids, int status) {
		itemMapper.updatestatus(ids, status);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemDescMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public Item findItemById(Long itemId) {
		
		return itemMapper.selectByPrimaryKey(itemId);
	}


	
}
