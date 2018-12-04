package com.orange.base.service;

import com.alibaba.fastjson.JSON;
import com.orange.base.dao.IDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 处理一般的查询逻辑
 * @author ading
 */

@Service
public class JqueryCommonService {
	
	
	private Log log = LogFactory.getLog(JqueryCommonService.class);
	
	@Resource(name="JqueryCommonDao")
	private IDao<Map<String, Object>> dao;
	
	public String handle(String id, Map<String, Object> params)
			throws Exception {

		// 检测是否存在分页 以及排序字段
		Map<String, Object> paramsTmp = new HashMap<String, Object>();
		if(params != null){
			paramsTmp.putAll(params);
		}
		// 去除不必要的参数
		paramsFilter(paramsTmp);
		String orderBy = "";
		if (paramsTmp.containsKey("sortField") && paramsTmp.containsKey("sortOrder")) {
			orderBy = paramsTmp.get("sortField") + " " + paramsTmp.get("sortOrder");
			paramsTmp.remove("sortField");
			paramsTmp.remove("sortOrder");
		}
		int pageIndex = 0;
		int pageSize = -1;
		boolean isPaged = false;
		if (paramsTmp.containsKey("pageIndex") && paramsTmp.containsKey("pageSize")) {
			pageIndex = Integer.valueOf(paramsTmp.get("pageIndex").toString());
			pageSize = Integer.valueOf(paramsTmp.get("pageSize").toString());
			paramsTmp.remove("pageIndex");
			paramsTmp.remove("pageSize");
			isPaged = true;
		}
		
		log.debug("\nMapperId   : " + id
				+ "\nSQLParams  : " + paramsTmp.toString() 
				+ "\nPartition  : {isPaged:" + isPaged +", pageIndex:" + pageIndex + ", pageSize:" + pageSize + ", orderBy:" + orderBy + "}\n");
		if(isPaged){
			return JSON.toJSONString(dao.queryList(id, paramsTmp, pageIndex, pageSize, orderBy));
		}
		return JSON.toJSONString(dao.queryList(id, paramsTmp, pageIndex, pageSize, orderBy).get("data"));
	}
	
	/**
	 * 过滤参数 主要为了去除 size／length 的map 与 list 参数
	 * 传递到“ ”空格参数，暂无需过滤
	 * @param <E>
	 * @param <K>
	 * @param <V>
	 * @param params
	 */
	private <E, V, K> void paramsFilter(Map<String, Object> params){
		
		Map<String, Object> paramTmp = new HashMap<String, Object>();
		paramTmp.putAll(params);
		
		for(String key : paramTmp.keySet()){
			Object o = paramTmp.get(key);
			if(o == null){
				params.remove(key);
				log.debug("变量key:" + key + ";对应类型为null，不需要传递 此处remove。");
			}
			else if(o instanceof List){
				@SuppressWarnings("unchecked")
				List<E> l = (List<E>)o;
				if(l.size() == 0) {
					params.remove(key);
					log.debug("变量key:" + key + ";对应类型为List并且size为0，不需要传递 此处remove。");
				}
			}
			else if(o instanceof Map){
				@SuppressWarnings("unchecked")
				Map<K, V> m = (Map<K, V>)o;
				if(m.size() == 0) {
					params.remove(key);
					log.debug("变量key:" + key + ";对应类型为Map并且size为0，不需要传递 此处remove。");
				}
			}
			else if(o instanceof Object[]){
				Object[] arr = (Object[])o;
				if(arr.length == 0){
					params.remove(key);
					log.debug("变量key:" + key + ";对应类型为Array并且length为0，不需要传递 此处remove。");
				}
			}
			else if(o instanceof String){
				String str = (String)o;
				if("".equals(str)){
					params.remove(key);
					log.debug("变量key:" + key + ";对应类型为String并且值为空，不需要传递 此处remove。");
				}
			}
			// TODO 拓展其他类型参数
		}
	}

}
