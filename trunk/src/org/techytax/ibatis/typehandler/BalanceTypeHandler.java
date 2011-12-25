package org.techytax.ibatis.typehandler;

import java.sql.SQLException;
import java.sql.Types;

import org.techytax.domain.BalanceType;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class BalanceTypeHandler implements TypeHandlerCallback {
	public Object getResult(ResultGetter getter) throws SQLException {
		int value = getter.getInt();
		if (getter.wasNull()) {
			return null;
		}
		BalanceType balanceType = BalanceType.getInstance(value);

		return balanceType;
	}

	public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
		if (parameter == null) {
			setter.setNull(Types.INTEGER);
		} else {
			BalanceType balanceType = (BalanceType) parameter;
			setter.setInt(balanceType.ordinal());
		}
	}

	public Object valueOf(String s) {
		return s;
	}
}