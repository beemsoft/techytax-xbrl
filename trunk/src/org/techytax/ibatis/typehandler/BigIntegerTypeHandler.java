/**
 * Copyright 2012 Hans Beemsterboer
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
package org.techytax.ibatis.typehandler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Types;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class BigIntegerTypeHandler implements TypeHandlerCallback {

	public Object getResult(ResultGetter getter) throws SQLException {
		if (getter.wasNull()) {
			return null;
		}

		Object o = getter.getObject();
		if (o instanceof BigDecimal) {
			BigDecimal bd = (BigDecimal) o;
			return bd.toBigInteger();
		} else if (o instanceof BigInteger) {
			return (BigInteger) o;
		} else {
			return o;
		}
	}

	public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
		if (parameter == null) {
			setter.setNull(Types.BIGINT);
		} else {
			BigInteger i = (BigInteger) parameter;
			setter.setBigDecimal(new BigDecimal(i));
		}
	}

	public Object valueOf(String s) {
		return s;
	}
}
