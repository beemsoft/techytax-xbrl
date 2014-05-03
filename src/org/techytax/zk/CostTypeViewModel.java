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
package org.techytax.zk;

import static org.techytax.domain.CostConstants.EXPENSE_CURRENT_ACCOUNT;
import static org.techytax.domain.CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT;
import static org.techytax.domain.CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT;
import static org.techytax.log.AuditType.MATCH_TRANSACTION;

import java.util.ArrayList;
import java.util.List;

import org.techytax.dao.CostTypeDao;
import org.techytax.dao.KostmatchDao;
import org.techytax.domain.CostType;
import org.techytax.domain.Kostmatch;
import org.techytax.domain.PrivateCostMatch;
import org.techytax.domain.User;
import org.techytax.domain.VatMatchPrivate;
import org.techytax.domain.VatType;
import org.techytax.log.AuditLogger;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class CostTypeViewModel {

	private User user = UserCredentialManager.getUser();
	private CostType selected;
	private PrivateCostMatch selectedPrivateMatch;
	private String selectedVatType;
	private int selectedPercentage;

	private List<CostType> costTypes = new ArrayList<>();
	private List<Kostmatch> publicMatches = new ArrayList<>();
	private List<PrivateCostMatch> privateMatches = new ArrayList<>();
	private KostmatchDao kostmatchDao = new KostmatchDao();
	private CostTypeDao kostensoortDao = new CostTypeDao();

	@Init
	public void init() throws Exception {
		costTypes = kostensoortDao.getCostTypesForAccount();
		selected = costTypes.get(0); // Selected First One
		setPrivateMatches(selected);
	}

	public List<CostType> getCostTypeList() {
		return costTypes;
	}

	private void setPrivateMatches(CostType costType) throws Exception {
		if (user != null) {
			privateMatches = kostmatchDao.getCostMatchPrivateListForCostType(costType);
		}

	}

	@NotifyChange({ "privateMatchesList", "publicMatchesList", "vatVisible", "selectedPrivateMatch", "splitVisible" })
	public void setSelectedCostType(CostType selected) throws Exception {
		this.selected = selected;
		setPrivateMatches(selected);
		setPublicMatches(selected);
		this.selectedPrivateMatch = null;
	}

	private void setPublicMatches(CostType selected) throws Exception {
		this.publicMatches = kostmatchDao.getKostmatchLijstForCostType(selected);
	}

	@NotifyChange
	public void setSelectedVatType(String vatType) {
		this.selectedVatType = vatType;
	}

	public String getSelectedVatType() {
		return selectedVatType;
	}

	@NotifyChange({ "selectedPrivateMatch", "privateMatchesList" })
	@Command
	public void newMatch() {
		PrivateCostMatch kostMatch = new PrivateCostMatch();
		privateMatches.add(kostMatch);
		selectedPrivateMatch = kostMatch;
	}

	@NotifyChange("selectedPrivateMatch")
	@Command
	public void saveMatch() throws Exception {
		if (user != null) {
			AuditLogger.log(MATCH_TRANSACTION, user);
			selectedPrivateMatch.setCostType(selected);
			if (selected.isVatDeclarable()) {
				VatMatchPrivate vatMatchPrivate = new VatMatchPrivate();
				if (selectedVatType == null) {
					vatMatchPrivate.setVatType(VatType.NONE);
				} else {
					vatMatchPrivate.setVatType(VatType.valueOf(selectedVatType));
				}
				selectedPrivateMatch.setVatMatchPrivate(vatMatchPrivate);
			}
			PrivateCostMatch costMatch = kostmatchDao.getCostMatchPrivate(selectedPrivateMatch);
			if (costMatch == null) {
				kostmatchDao.insertCostMatchPrivate(selectedPrivateMatch);
			} else {
				kostmatchDao.updateCostMatchPrivate(selectedPrivateMatch);
			}
		}
	}

	@NotifyChange({ "selectedPrivateMatch", "privateMatchesList" })
	@Command
	public void deleteMatch() throws Exception {
		if (user != null) {
			kostmatchDao.deleteCostMatchPrivate(selectedPrivateMatch);
		}
		privateMatches.remove(selectedPrivateMatch);
		selectedPrivateMatch = null;
	}

	public boolean getVatVisible() {
		return selected.isVatDeclarable() && !selected.equals(TRAVEL_WITH_PUBLIC_TRANSPORT) && !selected.equals(TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT);
	}

	public boolean getSplitVisible() {
		return selected.equals(EXPENSE_CURRENT_ACCOUNT);
	}

	public CostType getSelectedCostType() {
		return selected;
	}

	public List<Kostmatch> getPublicMatchesList() {
		return publicMatches;
	}

	public List<PrivateCostMatch> getPrivateMatchesList() {
		return privateMatches;
	}

	public PrivateCostMatch getSelectedPrivateMatch() {
		return selectedPrivateMatch;
	}

	@NotifyChange({ "selectedPrivateMatch", "selectedVatType" })
	public void setSelectedPrivateMatch(PrivateCostMatch selectedPrivateMatch) {
		this.selectedPrivateMatch = selectedPrivateMatch;
		if (selected.isVatDeclarable() && selectedPrivateMatch.getVatMatchPrivate() != null) {
			setSelectedVatType(selectedPrivateMatch.getVatMatchPrivate().getVatType().name());
		}
	}

	public int getSelectedPercentage() {
		return selectedPercentage;
	}

	public void setSelectedPercentage(int selectedPercentage) {
		this.selectedPercentage = selectedPercentage;
	}
}
