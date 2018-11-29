package com.orange.base.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orange.base.dao.impl.BaseDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *fastJSON 可处理 Clob字段 Blob 暂为处理
 * 
 * @author ading
 *
 */
@Repository("JqueryCommonDao")
public class JqueryCommonDao extends BaseDaoImpl {
	
	
	private final Log log = LogFactory.getLog(JqueryCommonDao.class);
	
	public Map<String, Object> queryOne(String id, Map<String, Object> params){
		return super.queryOne(id, params);
	}
	
	public List<Map<String, Object>> queryList(String id, Map<String, Object> params){
		return super.queryList(id, params);
	}
	
	/**
	 * @param pageIndex 0 开始
	 */
	public Map<String, Object> queryList(String id, Map<String, Object> params, int pageIndex, int pageSize, String orderBy){
		
		
		boolean isCount = pageSize != -1;
//		PageHelper.startPage((pageIndex + 1), pageSize, isCount, orderBy);
		long beginTime = System.currentTimeMillis();
		List<Map<String, Object>> dataList = this.queryList(id, params);
		long endTime = System.currentTimeMillis();
		PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(dataList);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("data", dataList);
		ret.put("pageIndex", pageIndex);
		ret.put("pageSize", pageSize);
		ret.put("total", page.getTotal());
		
		if(log.isDebugEnabled()){
			log.debug(  "\nMapperId   : " + id +""
		              + "\nSQLparams  : " + params.toString() + "" 
		              + "\nTime       : " + (endTime - beginTime) + "(ms)"
					  + "\nResult     : " + ret.toString() + "\n");
		}
		
		return ret;
	}
}
