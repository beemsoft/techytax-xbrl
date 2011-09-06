/**
 * Copyright 2011 Hans Beemsterboer
 * 
 * This file is part of the TechyTax program.
 *
 * TechyTax is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * TechyTax is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TechyTax; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.techytax.dao;

import java.util.HashMap;
import java.util.Map;

import org.techytax.util.IbatisUtil;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDao {
	
	protected SqlMapClient sqlMap;

	public BaseDao() {
		sqlMap = IbatisUtil.getSqlMapInstance();
	}
	
	protected Map<String, String> createMap(String beginDatum, String eindDatum, String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("beginDatum", beginDatum);
		map.put("eindDatum", eindDatum);
		map.put("userId", userId);
		return map;
	}
	
}
