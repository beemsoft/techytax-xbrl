package org.techytax.jpa.dao;

import static org.techytax.domain.CostConstants.BUSINESS_CAR_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.BUSINESS_FOOD_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.EXPENSE_CURRENT_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.INVESTMENT_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.INVOICE_PAID;
import static org.techytax.domain.CostConstants.INVOICE_SENT;
import static org.techytax.jpa.dao.QueryParameter.with;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.techytax.domain.Cost;
import org.techytax.domain.CostType;
import org.techytax.domain.FiscalPeriod;
import org.techytax.zk.login.UserCredentialManager;

import javax.transaction.Transactional;

@Component
public class CostDao extends GenericDao<Cost> {
	
	protected static final List<CostType> COSTS_FROM_OTHER_ACCOUNT = Arrays.asList(EXPENSE_OTHER_ACCOUNT, BUSINESS_CAR_OTHER_ACCOUNT, INVESTMENT_OTHER_ACCOUNT, BUSINESS_FOOD_OTHER_ACCOUNT);

	@Transactional
	public void insertSplitCost(Cost originalCost, Cost splitCost) {
		splitCost.setDate(originalCost.getDate());
		splitCost.setCostType(originalCost.getCostType().isBalansMeetellen() ? EXPENSE_CURRENT_ACCOUNT_IGNORE : EXPENSE_OTHER_ACCOUNT_IGNORE);
		splitCost.roundValues();
		splitCost.setUser(UserCredentialManager.getUser());
		persistEntity(splitCost);
	}

	public List<Cost> getCostsInPeriod(FiscalPeriod period) {
		return findByNamedQueryForPeriod(Cost.FOR_PERIOD, period);
	}

	public List<Cost> getVatCostsInPeriod(FiscalPeriod period) {
		return findByNamedQueryForPeriod(Cost.FOR_PERIOD_AND_VAT_DECLARABLE, period);
	}

	public List<Cost> getCostsOnBusinessAccountInPeriod(FiscalPeriod period) {
		return findByNamedQueryForPeriod(Cost.FOR_PERIOD_AND_ACCOUNT, period);
	}

	public List<Cost> getInvoicesSentAndPaid(FiscalPeriod period) {
		return getCosts(period, Arrays.asList(INVOICE_SENT, INVOICE_PAID));
	}

	public List<Cost> getInvoicesSent(FiscalPeriod period) {
		return getCosts(period, Arrays.asList(INVOICE_SENT));
	}

	/**
	 * Get the costs that have been paid with private money. Leave TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT out, because it is paid with a chipcard.  
	 */
	public List<Cost> getCostsWithPrivateMoneyExceptForTravelCosts(FiscalPeriod period) {
		return getCosts(period, COSTS_FROM_OTHER_ACCOUNT);
	}

	private List<Cost> getCosts(FiscalPeriod period, List<CostType> costTypes) {
		return findByNamedQuery(Cost.FOR_PERIOD_AND_TYPES, with("beginDate", period.getBeginDate()).and("endDate", period.getEndDate()).and("costTypes", costTypes).parameters());
	}	
	
	

}
