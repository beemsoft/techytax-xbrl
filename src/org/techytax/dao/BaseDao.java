/**
 * Copyright 2013 Hans Beemsterboer
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

import org.jasypt.util.numeric.BasicDecimalNumberEncryptor;
import org.jasypt.util.numeric.BasicIntegerNumberEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.techytax.props.PropsFactory;
import org.techytax.util.IbatisUtil;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDao {

	protected SqlMapClient sqlMap;

	protected BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	protected BasicDecimalNumberEncryptor decimalEncryptor = new BasicDecimalNumberEncryptor();
	protected BasicIntegerNumberEncryptor intEncryptor = new BasicIntegerNumberEncryptor();

	public BaseDao() {
		sqlMap = IbatisUtil.getSqlMapInstance();
		try {
			String password = PropsFactory.getProperty("security.password");
			textEncryptor.setPassword(password);
			decimalEncryptor.setPassword(password);
			intEncryptor.setPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("TechyTax properties not found!");
		}
	}

	protected Map<String, String> createMap(String beginDatum,
			String eindDatum, String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("beginDatum", beginDatum);
		map.put("eindDatum", eindDatum);
		map.put("userId", userId);
		return map;
	}

}
