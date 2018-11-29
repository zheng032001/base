package com.orange.base.dao.impl;

import com.orange.base.dao.IDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public abstract class BaseDaoImpl extends SqlSessionDaoSupport implements IDao<Map<String, Object>> {
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public Map<String, Object> queryOne(String id, Map<String, Object> params) {
		return this.getSqlSession().selectOne(id, params);
	}

	@Override
	public List<Map<String, Object>> queryList(String id, Map<String, Object> params) {
		
		return this.getSqlSession().selectList(id, params);
	}

	@Override
	public int delete(String id, Map<String, Object> params) {
		return this.getSqlSession().delete(id, params);
	}

	@Override
	public int insert(String id, Map<String, Object> params) {
		return this.getSqlSession().insert(id, params);
	}

	@Override
	public int update(String id, Map<String, Object> params) {
		return this.getSqlSession().update(id, params);
	}
}
