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
package org.techytax.zk.fiscal;

import java.util.Date;

import org.techytax.dao.SettlementDao;
import org.techytax.domain.Settlement;
import org.techytax.domain.User;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;

public class SettlementVM {

	private User user = UserCredentialManager.getUser();
	private SettlementDao settlementDao = new SettlementDao();
	private Settlement settlement = new Settlement();
	private Date startDate;
	
	@Init
	public void init() throws Exception {
		if (user != null) {
			settlement = settlementDao.getSettlement(user.getId());
			if (settlement != null) {
				startDate = DateHelper.stringToDate(settlement.getStartDate());
			} else {
				startDate = new Date();
			}
		} else {
			Executions.sendRedirect("login.zul");
		}
	}
	
	@Command
	public void save() throws Exception {
		settlement.setUser(user);
		settlement.setStartDate(DateHelper.getDate(startDate));
		settlementDao.updateSettlement(settlement);
	}	
	
	public String getAddress() {
		if (user == null) {
			Executions.sendRedirect("login.zul");
			return "";
		} else {
			return user.getCompanyAddress();
		}
	}

	public Settlement getSettlement() {
		return settlement;
	}

	public void setSettlement(Settlement settlement) {
		this.settlement = settlement;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
