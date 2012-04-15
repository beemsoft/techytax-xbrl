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
package org.techytax.dao;

import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;
import org.techytax.domain.Settlement;

public class SettlementDao extends BaseDao {

	private void encrypt(Settlement settlement) {
		settlement.setDescription(textEncryptor.encrypt(settlement.getDescription()));
	}

	private void decrypt(Settlement settlement) {
		if (settlement != null) {
			if (StringUtils.isNotEmpty(settlement.getDescription())) {
				settlement.setDescription(textEncryptor.decrypt(settlement.getDescription()));
			}
		}
	}

	public void insertSettlement(Settlement settlement) throws Exception {
		encrypt(settlement);
		sqlMap.insert("insertSettlement", settlement);
	}

	public void updateSettlement(Settlement settlement) throws Exception {
		encrypt(settlement);
		sqlMap.insert("updateSettlement", settlement);
	}

	@SuppressWarnings("unchecked")
	public Settlement getSettlement(long userId) throws Exception {
		Settlement settlement = (Settlement) sqlMap.queryForObject("getSettlement", userId);
		decrypt(settlement);
		return settlement;
	}
	
	public long getPercentage(long userId) throws Exception {
		Settlement settlement = getSettlement(userId);
		long percentage = 0;
		if (settlement != null && settlement.getNofSquareMetersPrivate() != 0) {
			percentage = Math.round((double)settlement.getNofSquareMetersBusiness()/settlement.getNofSquareMetersPrivate() * 100);
		}
		return percentage;
	}

}
