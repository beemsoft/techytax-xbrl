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
package org.techytax.zk.vat;

import static org.techytax.domain.CostConstants.EXPENSE_CURRENT_ACCOUNT;
import static org.techytax.domain.CostConstants.EXPENSE_INSIDE_EU;
import static org.techytax.domain.CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT;
import static org.techytax.domain.CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT;
import static org.techytax.log.AuditType.MATCH_TRANSACTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.techytax.dao.CostTypeDao;
import org.techytax.dao.KostmatchDao;
import org.techytax.dao.PrivateCostMatchDao;
import org.techytax.domain.CostType;
import org.techytax.domain.Kostmatch;
import org.techytax.domain.PrivateCostMatch;
import org.techytax.domain.SplitMatch;
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
	private CostType selectedCostType;
	private PrivateCostMatch selectedPrivateMatch;
	private VatType selectedVatType;
	private int selectedPercentage;

	private List<CostType> costTypes = new ArrayList<>();
	private List<Kostmatch> publicMatches = new ArrayList<>();
	private List<PrivateCostMatch> privateMatches = new ArrayList<>();
	private KostmatchDao kostmatchDao = new KostmatchDao(Kostmatch.class);
	private PrivateCostMatchDao privateCostMatchDao = new PrivateCostMatchDao(PrivateCostMatch.class);	
	private CostTypeDao kostensoortDao = new CostTypeDao(CostType.class);

	@Init
	public void init() throws Exception {
		costTypes = kostensoortDao.getCostTypesForTransactionMatching();
		selectedCostType = costTypes.get(0); // Selected First One
		setPrivateMatches(selectedCostType);
	}

	public List<CostType> getCostTypeList() {
		return costTypes;
	}

	private void setPrivateMatches(CostType costType) throws Exception {
		if (user != null) {
			privateMatches = privateCostMatchDao.getCostMatchPrivateListForCostType(costType);
		}

	}

	@NotifyChange({ "privateMatchesList", "publicMatchesList", "vatVisible", "selectedPrivateMatch", "splitVisible" })
	public void setSelectedCostType(CostType selected) throws Exception {
		this.selectedCostType = selected;
		setPrivateMatches(selected);
		setPublicMatches(selected);
		this.selectedPrivateMatch = null;
		this.selectedVatType = null;
	}

	private void setPublicMatches(CostType selected) throws Exception {
		this.publicMatches = kostmatchDao.getKostmatchLijstForCostType(selected);
	}

	@NotifyChange
	public void setSelectedVatType(String vatType) {
		this.selectedVatType = VatType.valueOf(vatType);
	}

	public String getSelectedVatType() {
		if (selectedVatType != null) {
			return selectedVatType.name();
		} else {
			return null;
		}
	}

	@NotifyChange({ "selectedPrivateMatch", "privateMatchesList" })
	@Command
	public void newMatch() {
		PrivateCostMatch kostMatch = new PrivateCostMatch();
		privateMatches.add(kostMatch);
		selectedPrivateMatch = kostMatch;
		selectedPrivateMatch.setCostType(selectedCostType);
	}

	@NotifyChange("selectedPrivateMatch")
	@Command
	public void saveMatch() throws Exception {
		if (user != null) {
			SplitMatch splitMatch = selectedPrivateMatch.getSplitMatch();
			AuditLogger.log(MATCH_TRANSACTION, user);
			handleSplitMatchPercentage(splitMatch);
			insertOrUpdatePrivateCostMatch();
		}
	}

	private void handleSplitMatchPercentage(SplitMatch splitMatch) {
		if (selectedPercentage > 0) {
			if (splitMatch == null) {
				splitMatch = new SplitMatch();
				splitMatch.setPrivateCostMatch(selectedPrivateMatch);
			}
			splitMatch.setPercentage(selectedPercentage);
			selectedPrivateMatch.setSplitMatch(splitMatch);
		} else {
			selectedPrivateMatch.setSplitMatch(null);
		}
	}

	private void insertOrUpdatePrivateCostMatch() throws Exception {
		PrivateCostMatch costMatch = privateCostMatchDao.getCostMatchPrivate(selectedPrivateMatch);
		if (selectedCostType.isVatDeclarable()) {
			if (selectedCostType.equals(EXPENSE_INSIDE_EU)) {
				selectedVatType = VatType.NONE;
			}
			VatMatchPrivate vatMatchPrivate = new VatMatchPrivate();
			vatMatchPrivate.setVatType(selectedVatType);
			vatMatchPrivate.setPrivateCostMatch(selectedPrivateMatch);
			selectedPrivateMatch.setVatMatchPrivate(vatMatchPrivate);
		}
		selectedPrivateMatch.setUser(user);
		if (costMatch == null) {
			privateCostMatchDao.persistEntity(selectedPrivateMatch);
		} else {
			setSplitMatchIdWhenUpdating(costMatch);
			privateCostMatchDao.merge(selectedPrivateMatch);
		}
	}

	/**
	 * If the id's are unequal, JPA will do insert instead of update. 
	 */
	private void setSplitMatchIdWhenUpdating(PrivateCostMatch costMatch) {
		if (selectedPrivateMatch.getSplitMatch() != null && costMatch.getSplitMatch() != null) {
			selectedPrivateMatch.getSplitMatch().setId(costMatch.getSplitMatch().getId());
		}
	}	

	@NotifyChange({ "selectedPrivateMatch", "privateMatchesList" })
	@Command
	public void deleteMatch() throws Exception {
		if (user != null) {
			privateCostMatchDao.deleteEntity(selectedPrivateMatch);
		}
		privateMatches.remove(selectedPrivateMatch);
		selectedPrivateMatch = null;
	}

	public boolean getVatVisible() {
		List<CostType> costTypes = Arrays.asList(TRAVEL_WITH_PUBLIC_TRANSPORT, TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT, EXPENSE_INSIDE_EU);
		return selectedCostType.isVatDeclarable() && !costTypes.contains(selectedCostType);
	}

	public boolean getSplitVisible() {
		return selectedCostType.equals(EXPENSE_CURRENT_ACCOUNT);
	}

	public CostType getSelectedCostType() {
		return selectedCostType;
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

	@NotifyChange({ "selectedPrivateMatch", "selectedVatType", "selectedPercentage" })
	public void setSelectedPrivateMatch(PrivateCostMatch selectedPrivateMatch) {
		this.selectedPrivateMatch = selectedPrivateMatch;
		this.selectedVatType = null;
		if (selectedCostType.isVatDeclarable() && selectedPrivateMatch.getVatMatchPrivate() != null) {
			setSelectedVatType(selectedPrivateMatch.getVatMatchPrivate().getVatType().name());
		}
		if (selectedPrivateMatch.getSplitMatch() != null) {
			selectedPercentage = selectedPrivateMatch.getSplitMatch().getPercentage();
		} else {
			selectedPercentage = 0;
		}
	}

	public int getSelectedPercentage() {
		return selectedPercentage;
	}

	public void setSelectedPercentage(int selectedPercentage) {
		this.selectedPercentage = selectedPercentage;
	}
}
