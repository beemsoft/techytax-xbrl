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
package org.techytax.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface CostConstants {

	CostType UNDETERMINED = new CostType(0);
	CostType INCOME_CURRENT_ACCOUNT = new CostType(1);
	CostType EXPENSE_CURRENT_ACCOUNT = new CostType(2);
	CostType EXPENSE_CURRENT_ACCOUNT_IGNORE = new CostType(3);
	CostType DEPOSIT = new CostType(4);
	CostType WITHDRAWAL = new CostType(5);
	CostType EXPENSE_OTHER_ACCOUNT_IGNORE = new CostType(6);
	CostType EXPENSE_OTHER_ACCOUNT = new CostType(7);
	CostType TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT = new CostType(8);
	CostType TRAVEL_WITH_PUBLIC_TRANSPORT = new CostType(9);
	CostType TO_SAVINGS_ACCOUNT = new CostType(10);	
	CostType VAT = new CostType(12);
	CostType BUSINESS_FOOD = new CostType(13);
	CostType BUSINESS_CAR = new CostType(14);
	CostType FROM_SAVINGS_ACCOUNT = new CostType(15);
	CostType INVESTMENT = new CostType(16);
	CostType TO_PRIVATE_ACCOUNT = new CostType(17);
	CostType FROM_PRIVATE_ACCOUNT = new CostType(18);	
	CostType BUSINESS_CAR_OTHER_ACCOUNT = new CostType(19);	
	CostType BUSINESS_FOOD_OTHER_ACCOUNT = new CostType(20);
	
	@Deprecated
	CostType BUSINESS_TRAVEL_CREDIT_CARD = new CostType(21);
	
	@Deprecated	
	CostType BUSINESS_LITERATURE_CREDIT_CARD_NO_VAT = new CostType(22);
	
	@Deprecated
	CostType DEPRECIATION_CAR = new CostType(23);
	
	@Deprecated
	CostType FISCAL_TAX_BUSINESS_CAR_PRIVATE_USAGE = new CostType(24);
	
	@Deprecated
	CostType DEPRECIATION_MACHINE = new CostType(25);
	
	CostType INVESTMENT_OTHER_ACCOUNT = new CostType(26);
	CostType ADVERTORIAL = new CostType(27);
	CostType ADVERTORIAL_NO_VAT = new CostType(28);
	CostType INCOME_TAX = new CostType(29);
	CostType INCOME_TAX_PAID_BACK = new CostType(30);
	CostType ROAD_TAX = new CostType(31);
	CostType EXPENSE_CREDIT_CARD = new CostType(32);
	CostType INTEREST = new CostType(33);
	
	@Deprecated
	CostType VAT_PAID_BACK_ON_OTHER_ACCOUNT = new CostType(34);	
	
	@Deprecated
	CostType VAT_CORRECTION_CAR_DEPRECIATION = new CostType(35);
	
	CostType VAT_CORRECTION_CAR_PRIVATE = new CostType(36);
	CostType INVOICE_PAID = new CostType(38);	
	CostType INVOICE_SENT = new CostType(39);
	CostType REPURCHASES = new CostType(40);
	CostType SETTLEMENT = new CostType(41);
	CostType SETTLEMENT_INTEREST = new CostType(42);
	
	@Deprecated
	CostType DEPRECIATION_SETTLEMENT = new CostType(43);
	
	CostType SETTLEMENT_OTHER_ACCOUNT = new CostType(44);
	CostType SETTLEMENT_DISCOUNT = new CostType(45);
	CostType INCOME_CURRENT_ACCOUNT_IGNORE = new CostType(46);
	CostType EXPENSE_INSIDE_EU = new CostType(47);

    /**
     * http://www.belastingdienst.nl/wps/wcm/connect/bldcontentnl/belastingdienst/zakelijk/winst/inkomstenbelasting/inkomstenbelasting_voor_ondernemers/fiscale_reserves/oudedagsreserve?projectid=521dd6ba-75a6-4401-8ae3-06c59bd73619&projectid=521dd6ba-75a6-4401-8ae3-06c59bd73619&projectid=521dd6ba-75a6-4401-8ae3-06c59bd73619&projectid=521dd6ba-75a6-4401-8ae3-06c59bd73619
     */
	BigInteger MAXIMALE_FOR = BigInteger.valueOf(9542);
	BigDecimal FOR_PERCENTAGE = BigDecimal.valueOf(0.109f);

    /**
     * http://www.belastingdienst.nl/wps/wcm/connect/bldcontentnl/belastingdienst/zakelijk/winst/inkomstenbelasting/inkomstenbelasting_voor_ondernemers/zakelijke_kosten/aftrek_van_kosten
     */
	float FOOD_TAXFREE_PERCENTAGE = 0.735f;
	
	int INVESTMENT_MINIMUM_AMOUNT = 450;
	

}
