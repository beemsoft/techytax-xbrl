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
package org.techytax.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.techytax.dao.SettlementDao;
import org.techytax.domain.Settlement;
import org.techytax.domain.User;
import org.techytax.struts.form.SettlementForm;

public class UpdateSettlementAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SettlementForm settlementForm = (SettlementForm) form;
		Settlement settlement = new Settlement();

		User user = (User) request.getSession().getAttribute("user");

		settlement.setUserId(user.getId());
		settlement.setAddress(settlementForm.getAddress());
		settlement.setDescription(settlementForm.getDescription());
		settlement.setStartDate(settlementForm.getStartDate());
		settlement.setPurchasePrice(settlementForm.getPurchasePrice());
		settlement.setStartupCosts(settlementForm.getStartupCosts());
		settlement.setNofSquareMetersBusiness(settlementForm.getNofSquareMetersBusiness());
		settlement.setNofSquareMetersPrivate(settlementForm.getNofSquareMetersPrivate());
		settlement.setNofYearsForDepreciation(settlementForm.getNofYearsForDepreciation());

		SettlementDao settlementDao = new SettlementDao();

		Settlement currentSettlement = settlementDao.getSettlement(user.getId());
		if (currentSettlement == null) {
			settlementDao.insertSettlement(settlement);
		} else {
			settlementDao.updateSettlement(settlement);
		}
		
		return mapping.findForward("success");

	}
}