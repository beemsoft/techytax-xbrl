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
package org.techytax.zk;

import java.util.ArrayList;
import java.util.List;

import org.techytax.dao.KostensoortDao;
import org.techytax.dao.KostmatchDao;
import org.techytax.dao.VatMatchDao;
import org.techytax.domain.CostConstants;
import org.techytax.domain.KeyId;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Kostmatch;
import org.techytax.domain.SplitMatch;
import org.techytax.domain.User;
import org.techytax.domain.VatMatch;
import org.techytax.domain.VatType;
import org.techytax.jpa.dao.SplitMatchDao;
import org.techytax.log.AuditLogger;
import org.techytax.log.AuditType;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;

public class CostTypeViewModel {

	private User user = UserCredentialManager.getUser();
	private Kostensoort selected;
	private Kostmatch selectedPrivateMatch;
	private String selectedVatType;
	private int selectedPercentage;

	private List<Kostensoort> costTypes = new ArrayList<Kostensoort>();
	private List<Kostmatch> publicMatches = new ArrayList<Kostmatch>();
	private List<Kostmatch> privateMatches = new ArrayList<Kostmatch>();
	private KostmatchDao kostmatchDao = new KostmatchDao();
	private KostensoortDao kostensoortDao = new KostensoortDao();
	private VatMatchDao vatMatchDao = new VatMatchDao();
	private SplitMatchDao splitMatchDao = new SplitMatchDao();

	@Init
	public void init() throws Exception {
		costTypes = kostensoortDao.getCostTypesForAccount();
		for (Kostensoort costType : costTypes) {
			costType.setOmschrijving(Labels.getLabel(costType.getOmschrijving()));
		}

		selected = costTypes.get(0); // Selected First One
		setPrivateMatches(selected);
	}

	public List<Kostensoort> getCostTypeList() {
		return costTypes;
	}

	private void setPrivateMatches(Kostensoort costType) throws Exception {
		if (user != null) {
			KeyId key = new KeyId();
			key.setId(selected.getKostenSoortId());
			key.setUserId(user.getId());
			privateMatches = kostmatchDao.getCostMatchPrivateListForId(key);
		}

	}

	@NotifyChange({ "privateMatchesList", "publicMatchesList", "vatVisible", "selectedPrivateMatch", "splitVisible" })
	public void setSelectedCostType(Kostensoort selected) throws Exception {
		this.selected = selected;
		setPrivateMatches(selected);
		setPublicMatches(selected);
		this.selectedPrivateMatch = null;
	}

	private void setPublicMatches(Kostensoort selected) throws Exception {
		this.publicMatches = kostmatchDao.getKostmatchLijstForId(Long.toString(selected.getKostenSoortId()));
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
		Kostmatch kostMatch = new Kostmatch();
		privateMatches.add(kostMatch);
		selectedPrivateMatch = kostMatch;
	}

	@NotifyChange("selectedPrivateMatch")
	@Command
	public void saveMatch() throws Exception {
		if (user != null) {
			AuditLogger.log(AuditType.MATCH_TRANSACTION, user);
			selectedPrivateMatch.setUserId(user.getId());
			selectedPrivateMatch.setKostenSoortId(selected.getKostenSoortId());

			// Check create or update
			KeyId key = new KeyId();
			key.setId(selectedPrivateMatch.getId());
			key.setUserId(user.getId());
			Kostmatch costMatch = kostmatchDao.getCostMatchPrivate(key);
			if (costMatch == null) {
				kostmatchDao.insertCostMatchPrivate(selectedPrivateMatch);
			} else {
				kostmatchDao.updateCostMatchPrivate(selectedPrivateMatch);
			}

			if (selected.isVatDeclarable()) {
				if (selectedVatType == null) {
					selectedPrivateMatch.setVatType(VatType.NONE);
				} else {
					selectedPrivateMatch.setVatType(VatType.valueOf(selectedVatType));
				}
				VatMatch vatMatch = vatMatchDao.getVatMatchPrivate(Long.toString(selectedPrivateMatch.getId()));
				if (vatMatch == null) {
					vatMatchDao.insertVatMatchPrivateComplete(selectedPrivateMatch);
				} else {
					vatMatchDao.updateVatMatchPrivate(selectedPrivateMatch);
				}
			}
			SplitMatch splitMatch = new SplitMatch();
			splitMatch.setKostmatchId(selectedPrivateMatch.getId());
			if (selectedPrivateMatch.getPercentage() > 0) {
				splitMatch.setPercentage(selectedPrivateMatch.getPercentage());
				splitMatchDao.insertOrUpdate(splitMatch);
			} else {
				splitMatchDao.delete(selectedPrivateMatch.getId());
			}
		}
	}

	@NotifyChange({ "selectedPrivateMatch", "privateMatchesList" })
	@Command
	public void deleteMatch() throws Exception {
		if (user != null) {
			selectedPrivateMatch.setUserId(user.getId());
			vatMatchDao.deleteVatMatchPrivate(Long.toString(selectedPrivateMatch.getId()));
			splitMatchDao.delete(selectedPrivateMatch.getId());
			kostmatchDao.deleteCostMatchPrivate(selectedPrivateMatch);
		}
		privateMatches.remove(selectedPrivateMatch);
		selectedPrivateMatch = null;
	}

	public boolean getVatVisible() {
		return selected.isVatDeclarable() && selected.getKostenSoortId() != CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT;
	}

	public boolean getSplitVisible() {
		System.out.println("TEstje: " + selected.getKostenSoortId());
		return selected.getKostenSoortId() == CostConstants.UITGAVE_DEZE_REKENING;
	}

	public Kostensoort getSelectedCostType() {
		return selected;
	}

	public List<Kostmatch> getPublicMatchesList() {
		return publicMatches;
	}

	public List<Kostmatch> getPrivateMatchesList() {
		return privateMatches;
	}

	public Kostmatch getSelectedPrivateMatch() {
		return selectedPrivateMatch;
	}

	@NotifyChange({ "selectedPrivateMatch", "selectedVatType" })
	public void setSelectedPrivateMatch(Kostmatch selectedPrivateMatch) {
		this.selectedPrivateMatch = selectedPrivateMatch;
		if (selected.isVatDeclarable()) {
			setSelectedVatType(selectedPrivateMatch.getVatType().name());
		}
		if (selected.getKostenSoortId() == CostConstants.UITGAVE_DEZE_REKENING) {
			SplitMatch splitMatch = splitMatchDao.getSplitMatch(selectedPrivateMatch.getId());
			if (splitMatch != null) {
				this.selectedPrivateMatch.setPercentage(splitMatch.getPercentage());
			}
		}
	}

	public int getSelectedPercentage() {
		return selectedPercentage;
	}

	public void setSelectedPercentage(int selectedPercentage) {
		this.selectedPercentage = selectedPercentage;
	}
}
