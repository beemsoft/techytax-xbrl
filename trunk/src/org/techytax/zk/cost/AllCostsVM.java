/**
 * Copyright 2014 Hans Beemsterboer
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
package org.techytax.zk.cost;

import static org.techytax.domain.CostConstants.EXPENSE_CURRENT_ACCOUNT;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.INVESTMENT;
import static org.techytax.domain.CostConstants.INVESTMENT_MINIMUM_AMOUNT;
import static org.techytax.domain.CostConstants.INVESTMENT_OTHER_ACCOUNT;
import static org.techytax.helper.DutchAuditFileHelper.sendAuditFile;
import static org.techytax.log.AuditType.ENTER_COST;
import static org.techytax.log.AuditType.SPLIT_COST;
import static org.techytax.log.AuditType.UPDATE_COST;
import static org.techytax.util.DateHelper.getLatestVatPeriod;
import static org.techytax.util.DateHelper.getPeriodPreviousYear;
import static org.techytax.util.DateHelper.isTimeForUsingLatestYearPeriod;
import static org.zkoss.zk.ui.Executions.sendRedirect;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.techytax.cache.CostTypeCache;
import org.techytax.domain.Cost;
import org.techytax.domain.CostType;
import org.techytax.domain.FiscalPeriod;
import org.techytax.domain.VatPeriodType;
import org.techytax.helper.DutchAuditFileHelper;
import org.techytax.log.AuditLogger;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class AllCostsVM extends CostVM {

	private FiscalPeriod periode;
	private List<Cost> unhandledCosts = new ArrayList<>();
	private List<Cost> filteredCosts = new ArrayList<>();
	private boolean showUnhandledInvestments = false;
	private boolean filterCosts = false;
	private String searchString;

	private boolean fileuploaded = false;
	private AMedia fileContent;
	
	public AllCostsVM() throws Exception {
		super();
		if (user != null) {
			if (isTimeForUsingLatestYearPeriod()) {
				periode = getPeriodPreviousYear();
			} else {
				periode = getLatestVatPeriod(user.getVatPeriodType());
			}
			getCosts();
		} else {
			periode = getLatestVatPeriod(VatPeriodType.PER_QUARTER);
			sendRedirect("login.zul");
		}
	}
	
	@GlobalCommand
	@NotifyChange({ "costs", "selected" })
	public void refreshvalues(@BindingParam("returncost") Cost cost, @BindingParam("splitcost") Cost splitCost) throws Exception {
		Cost originalCost = (Cost) costDao.getEntity(cost, Long.valueOf(cost.getId()));
		cost.setUser(user);
		if (originalCost == null) {
			AuditLogger.log(ENTER_COST, user);
			cost.roundValues();
			costDao.persistEntity(cost);
			this.selected = cost;
		} else if (!cost.equals(originalCost)) {
			AuditLogger.log(UPDATE_COST, user);
			cost.roundValues();
			costDao.merge(cost);
		}
		if (splitCost != null) {
			AuditLogger.log(SPLIT_COST, user);
			costDao.insertSplitCost(cost, splitCost);
		}
	}		

	@Command
	public void audit() {
		sendAuditFile(user, periode);
	}

	@Command
	public void importXaf(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException, DatatypeConfigurationException {
		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			Messagebox.show("File Sucessfully uploaded");
			
			DutchAuditFileHelper.importAuditFile(media.getStreamData(), user);
			
			fileuploaded = true;
			
		}
	}

	public AMedia getFileContent() {
		return fileContent;
	}

	public void setFileContent(AMedia fileContent) {
		this.fileContent = fileContent;
	}

	public boolean isFileuploaded() {
		return fileuploaded;
	}

	public void setFileuploaded(boolean fileuploaded) {
		this.fileuploaded = fileuploaded;
	}
	
	public ListModelList<Cost> getCosts() throws Exception {
		if (user != null) {
			costCache.setBeginDatum(periode.getBeginDate());
			costCache.setEindDatum(periode.getEndDate());
			List<Cost> allCosts = costCache.getCosts();
			unhandledCosts = new ArrayList<>();
			filteredCosts = new ArrayList<>();
			for (Cost cost : allCosts) {
				if (filterCosts && StringUtils.isNotEmpty(searchString)) {
					if (cost.getDescription().toLowerCase().contains(searchString.toLowerCase())) {
						filteredCosts.add(cost);
					}
				} else {
					filterUnhandledCosts(cost);
				}
			}
			if (!showUnhandledInvestments && !filterCosts) {
				costs = new ListModelList<>(allCosts);
			} else if (showUnhandledInvestments) {
				costs = new ListModelList<>(unhandledCosts);
				showUnhandledInvestments = false;
			} else if (filterCosts) {
				costs = new ListModelList<>(filteredCosts);
				filterCosts = false;
			}
		} else {
			Executions.sendRedirect("login.zul");
		}
		return costs;
	}

	private void filterUnhandledCosts(Cost cost) {
		if (cost.getAmount().compareTo(new BigDecimal(INVESTMENT_MINIMUM_AMOUNT)) == 1) {
			if (!cost.getCostType().equals(INVESTMENT) && cost.getCostType() != INVESTMENT_OTHER_ACCOUNT) {
				if (cost.getCostType().equals(EXPENSE_CURRENT_ACCOUNT) || cost.getCostType().equals(EXPENSE_OTHER_ACCOUNT)) {
					unhandledCosts.add(cost);
				}
			}
		}
	}

	@NotifyChange("costs")
	@Command
	public void showUnhandledInvestments() throws Exception {
		showUnhandledInvestments = true;
	}

	public boolean isListWithUnhandledInvestments() throws Exception {
		if (unhandledCosts != null && !unhandledCosts.isEmpty()) {
			return true;
		}
		return false;
	}

	public ListModelList<Cost> getBusinessCosts() throws Exception {
		if (user != null && costs == null) {
			List<Cost> vatCosts = costDao.getCostsOnBusinessAccountInPeriod(periode);
			costs = new ListModelList<>(vatCosts);
		}
		return costs;
	}

	public ListModelList<CostType> getCostTypes() throws Exception {
		if (costTypes == null) {
			Collection<CostType> vatCostTypes = CostTypeCache.getCostTypes();
			costTypes = new ListModelList<>(vatCostTypes);
			selectedCostType = costTypes.get(0);
		}
		return costTypes;
	}

	@NotifyChange({ "costs", "listWithUnhandledInvestments" })
	public void setBeginDate(Date beginDate) {
		periode.setBeginDatum(beginDate);
	}

	public Date getBeginDate() {
		return periode.getBeginDate();
	}

	@NotifyChange({ "costs", "listWithUnhandledInvestments" })
	public void setEndDate(Date endDate) {
		periode.setEindDatum(endDate);
	}

	public Date getEndDate() {
		return periode.getEndDate();
	}

	@Command
	public void newCost() {
		Cost newCost = new Cost();
		newCost.setDate(new Date());
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("cost", newCost);
		String template = "edit-cost.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

	@Command
	public void onDoubleClicked() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("cost", selected);
		String template = "edit-cost.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

	@Command
	public void filterCosts() {
		doFilter(searchString);
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) throws Exception {
		this.searchString = searchString;
	}

	private void doFilter(String searchString) {
		this.searchString = searchString;
		if (searchString != null && searchString.length() > 1) {
			filterCosts = true;
			BindUtils.postNotifyChange("queueName", "desktop", this, "costs");
		} else {
			filterCosts = false;
			BindUtils.postNotifyChange("queueName", "desktop", this, "costs");
		}
	}
}
