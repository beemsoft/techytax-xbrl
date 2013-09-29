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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.util.DateHelper;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class ModelWindowVM {
	@Wire("#resultWin")
	private Window win;
	private BookValue bookValue;
	private ListModelList<BalanceType> balanceTypes;
	private BalanceType selectedBalanceType;
	private List<Integer> yearsList;
	private Integer selectedYear;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("bookValue") BookValue bookValue) {
		Selectors.wireComponents(view, this, false);
		this.bookValue = bookValue;
		this.yearsList = DateHelper.getLatestSevenYears();
	}

	public ListModelList<BalanceType> getBalanceTypes() throws Exception {
		if (balanceTypes == null) {
			BalanceType[] balanceTypeArray = BalanceType.values();
			return new ListModelList<BalanceType>(balanceTypeArray);
		}
		return null;
	}

	public BalanceType getSelectedBalanceType() {
		return selectedBalanceType;
	}

	public void setSelectedBalanceType(BalanceType selected) {
		this.selectedBalanceType = selected;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void save() {
		Map args = new HashMap();
		bookValue.setBalanceType(selectedBalanceType);
		bookValue.setJaar(selectedYear);
		args.put("returnbookvalue", this.bookValue);
		BindUtils.postGlobalCommand("queueName", null, "refreshvalues", args);
		win.detach();
	}

	@Command
	public void cancel() {
		win.detach();
	}

	@Command
	public void closeThis() {
		win.detach();
	}

	public BookValue getBookValue() {
		return bookValue;
	}

	public void setBookValue(BookValue bookValue) {
		this.bookValue = bookValue;
	}

	public List<Integer> getYearsList() {
		return yearsList;
	}

	public Integer getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(Integer selectedYear) {
		this.selectedYear = selectedYear;
	}

}
