/**
 * Copyright 2009 Hans Beemsterboer
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
package org.techytax.util;

import java.io.Reader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.ibatis.common.jdbc.SimpleDataSource;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class IbatisUtil {
	private static SqlMapClient sqlMap;
	static {
		try {
			String resource = "sql-map-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e) {
			// If you get an error at this point, it doesn�t matter what it was.
			// It is going to be
			// unrecoverable and we will want the app to blow up hard so we are
			// aware of the
			// problem. You should always log such errors and re-throw them in
			// such a way that
			// you can be made immediately aware of the problem.
			e.printStackTrace();
			throw new RuntimeException(
					"Error initializing MyAppSqlConfig class. Cause: " + e);
		}
	}

	public SqlMapClient getSqlMapInstance() {
		return sqlMap;
	}

	public static void changeUserConnection(String username, String password,
			String host, String catalog) throws Exception {
		sqlMap.setUserConnection(null);
		Map<String, String> props = new HashMap<String, String>();
		props.put("JDBC.Driver", "com.mysql.jdbc.Driver");
		props
				.put(
						"JDBC.ConnectionURL",
						"jdbc:mysql://"
								+ host
								+ "/"
								+ catalog
								+ "?enableDeprecatedAutoreconnect=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
		props.put("JDBC.Username", username);
		props.put("JDBC.Password", password);
		props.put("JDBC.DefaultAutoCommit", "true");
		props.put("Pool.MaximumActiveConnections", "10");
		props.put("Pool.MaximumIdleConnections", "5");
		props.put("Pool.MaximumCheckoutTime", "600000");
		props.put("Pool.TimeToWait", "500");
		props.put("Pool.PingQuery", "select 1 from " + catalog + ".boekwaarde");
		props.put("Pool.PingConnectionsOlderThan", "600000");
		props.put("Pool.PingEnabled", "true");
		props.put("Pool.PingConnectionsNotUsedFor", "600000");

		DataSource dataSource = new SimpleDataSource(props);
		Connection userConnection = dataSource.getConnection();
		sqlMap.setUserConnection(userConnection);
	}

	public void setUserConnection(Connection userConnection) {
		try {
			sqlMap.setUserConnection(userConnection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ConnectionInfo getInfo() {
		try {
			if (sqlMap.getDataSource() != null) {
				ConnectionInfo connectionInfo = new ConnectionInfo();
				Connection connection = sqlMap.getDataSource().getConnection();
				String username = connection.getMetaData().getUserName();
				username = username.replaceFirst("@.*", "");
				String url = connection.getMetaData().getURL();
				String host = url.replaceFirst(".*//", "");
				host = host.replaceFirst(":.*", "");
				String catalog = connection.getMetaData().getURL();
				catalog = catalog.replaceAll(".*/", "");
				catalog = catalog.replaceFirst("\\?.*", "");
				connectionInfo.setUsername(username);
				connectionInfo.setHost(host);
				connectionInfo.setCatalog(catalog);
				connection.close();
				return connectionInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}