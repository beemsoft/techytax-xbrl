package org.techytax.ibatis.typehandler;

import java.sql.SQLException;
import java.sql.Types;

import org.techytax.domain.AccountType;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class AccountTypeHandler implements TypeHandlerCallback {
	public Object getResult(ResultGetter getter) throws SQLException {
		int value = getter.getInt();
		if (getter.wasNull()) {
			return null;
		}
		AccountType accountType = AccountType.getInstance(value);

		return accountType;
	}

	public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
		if (parameter == null) {
			setter.setNull(Types.INTEGER);
		} else {
			AccountType accountType = (AccountType) parameter;
			setter.setInt(accountType.ordinal());
		}
	}

	public Object valueOf(String s) {
		return s;
	}
}