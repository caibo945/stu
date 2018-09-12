package com.jt.manage.service;

import java.util.List;

import com.jt.common.vo.ItemCatResult;
import com.jt.manage.vo.EasyUITree;

public interface ItemCatService {

	List<EasyUITree> findItemCatList(Long parentId);

	List<EasyUITree> findItemCatCache(Long parentId);

	ItemCatResult findItemCatAll();

	ItemCatResult findItemCatAllcache();

}
