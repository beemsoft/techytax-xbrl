package org.techytax.jpa.dao;

import java.util.List;

import javax.persistence.Query;

import org.techytax.domain.SplitMatch;
import org.techytax.jpa.entities.EntityManagerHelper;

public class SplitMatchDao {

	public void insertOrUpdate(SplitMatch splitMatch) {
		List<SplitMatch> result = getMatch(splitMatch);
		GenericDao<SplitMatch> genericDao = new GenericDao<SplitMatch>(EntityManagerHelper.getEntityManager());
		if (result.size() == 0) {
			genericDao.persistEntity(splitMatch);
		} else {
			SplitMatch persistedSplitMatch = result.get(0);
			persistedSplitMatch.setPercentage(splitMatch.getPercentage());
			genericDao.persistEntity(persistedSplitMatch);
		}
	}
	
	public SplitMatch getSplitMatch(long costMatchId) {
		SplitMatch splitMatch = new SplitMatch();
		splitMatch.setKostmatchId(costMatchId);
		List<SplitMatch> result = getMatch(splitMatch);
		if (result.size() == 1) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private List<SplitMatch> getMatch(SplitMatch splitMatch) {
		Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT sm FROM SplitMatch sm WHERE sm.kostmatchId= :costMatchId");
		query.setParameter("costMatchId", splitMatch.getKostmatchId());
		List<SplitMatch> result = query.getResultList();
		return result;
	}
	
	public void delete(long costMatchId) {
		SplitMatch splitMatch = new SplitMatch();
		splitMatch.setKostmatchId(costMatchId);
		List<SplitMatch> result = getMatch(splitMatch);
		if (result.size() == 1) {
			GenericDao<SplitMatch> genericDao = new GenericDao<SplitMatch>(EntityManagerHelper.getEntityManager());
			genericDao.deleteEntity(result.get(0));
		}
	}
}
