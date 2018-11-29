package com.orange.base.dao;

import java.util.List;
import java.util.Map;


public interface IDao<T> {

	T queryOne(String id, Map<String, Object> params);
	List<T> queryList(String id, Map<String, Object> params);
	int delete(String id, Map<String, Object> params);
	int insert(String id, Map<String, Object> params);
	int update(String id, Map<String, Object> params);
	Map<String, Object> queryList(String id, Map<String, Object> params, int pageIndex, int pageSize, String orderBy);
}
