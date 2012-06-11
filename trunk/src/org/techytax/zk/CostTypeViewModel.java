package org.techytax.zk;

import java.util.ArrayList;
import java.util.List;

import org.techytax.dao.KostensoortDao;
import org.techytax.dao.KostmatchDao;
import org.techytax.dao.VatMatchDao;
import org.techytax.domain.KeyId;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Kostmatch;
import org.techytax.domain.User;
import org.techytax.domain.VatMatch;
import org.techytax.domain.VatType;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;

public class CostTypeViewModel {

	private Kostensoort selected;
	private Kostmatch selectedPrivateMatch;
	private String selectedVatType;
	
	private List<Kostensoort> costTypes = new ArrayList<Kostensoort>();
	private List<Kostmatch> publicMatches = new ArrayList<Kostmatch>();
	private List<Kostmatch> privateMatches = new ArrayList<Kostmatch>();
	private KostmatchDao kostmatchDao = new KostmatchDao();
	private KostensoortDao kostensoortDao = new KostensoortDao();
	private VatMatchDao vatMatchDao = new VatMatchDao();	

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
		User user = UserCredentialManager.getUser();
		if (user != null) {
			KeyId key = new KeyId();
			key.setId(selected.getKostenSoortId());
			key.setUserId(user.getId());
			privateMatches = kostmatchDao.getCostMatchPrivateListForId(key);
		}
		
	}

	@NotifyChange({"privateMatchesList", "publicMatchesList", "vatVisible", "selectedPrivateMatch"})
	public void setSelectedCostType(Kostensoort selected) throws Exception {
		this.selected = selected;
		setPrivateMatches(selected);
		this.publicMatches = kostmatchDao.getKostmatchLijstForId(Long.toString(selected.getKostenSoortId()));
		this.selectedPrivateMatch = null;
	}
	
	@NotifyChange
	public void setSelectedVatType(String vatType) {
		this.selectedVatType = vatType;
	}
	
	public String getSelectedVatType() {
		return selectedVatType;
	}
	
	@NotifyChange({"selectedPrivateMatch","privateMatchesList"})
	@Command
	public void newMatch(){
		Kostmatch kostMatch = new Kostmatch();
		privateMatches.add(kostMatch);
		selectedPrivateMatch = kostMatch;
	}
	
	@NotifyChange("selectedPrivateMatch")
	@Command
	public void saveMatch() throws Exception{
		User user = UserCredentialManager.getUser();
		if (user != null) {
			selectedPrivateMatch.setUserId(user.getId());
			selectedPrivateMatch.setKostenSoortId(selected.getKostenSoortId());
			
			// Check create or update
			KeyId key = new KeyId();
			key.setId(selectedPrivateMatch.getId());
			key.setUserId(user.getId());
			Kostmatch costMatch = kostmatchDao.getCostMatchPrivate(key);
			Integer insertedId = null;
			if (costMatch == null) {
				insertedId = kostmatchDao.insertCostMatchPrivate(selectedPrivateMatch);
				selectedPrivateMatch.setId(insertedId.longValue());
			} else {
				kostmatchDao.updateCostMatchPrivate(selectedPrivateMatch);
			}
			
			if (selected.isBtwVerrekenbaar()) {
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
		}
	}
	
	@NotifyChange({"selectedPrivateMatch","privateMatchesList"})
	@Command
	public void deleteMatch() throws Exception{
		User user = UserCredentialManager.getUser();
		if (user != null) {
			selectedPrivateMatch.setUserId(user.getId());
			vatMatchDao.deleteVatMatchPrivate(Long.toString(selectedPrivateMatch.getId()));
			kostmatchDao.deleteCostMatchPrivate(selectedPrivateMatch);
		}
		privateMatches.remove(selectedPrivateMatch);
		selectedPrivateMatch = null;		
	}
	
	public boolean getVatVisible() {
		return selected.isBtwVerrekenbaar();
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

	@NotifyChange({"selectedPrivateMatch","selectedVatType"})
	public void setSelectedPrivateMatch(Kostmatch selectedPrivateMatch) {
		this.selectedPrivateMatch = selectedPrivateMatch;
		if (selected.isBtwVerrekenbaar()) {
			setSelectedVatType(selectedPrivateMatch.getVatType().name());
		}
	}
}
