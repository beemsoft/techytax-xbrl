package org.techytax.dao;

import java.util.HashMap;
import java.util.Map;

import org.techytax.util.IbatisUtil;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDao {
	
	protected SqlMapClient sqlMap;

	public BaseDao() {
		IbatisUtil ibatisUtil = new IbatisUtil();
		sqlMap = ibatisUtil.getSqlMapInstance();
	}
	
	protected Map<String, String> createMap(String beginDatum, String eindDatum) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("beginDatum", beginDatum);
		map.put("eindDatum", eindDatum);
		return map;
	}
	
}
