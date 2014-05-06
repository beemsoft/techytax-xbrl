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

	public static final CostType UNDETERMINED = new CostType(0);
	public static final CostType INKOMST_DEZE_REKENING = new CostType(1);
	public static final CostType EXPENSE_CURRENT_ACCOUNT = new CostType(2);
	public static final CostType UITGAVE_DEZE_REKENING_FOUTIEF = new CostType(3);
	public static final CostType INLEG = new CostType(4);
	public static final CostType OPNAME = new CostType(5);
	public static final CostType EXPENSE_OTHER_ACCOUNT_IGNORE = new CostType(6);
	public static final CostType EXPENSE_OTHER_ACCOUNT = new CostType(7);
	public static final CostType TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT = new CostType(8);
	public static final CostType TRAVEL_WITH_PUBLIC_TRANSPORT = new CostType(9);
	public static final CostType TO_SAVINGS_ACCOUNT = new CostType(10);	
	public static final CostType VAT = new CostType(12);
	public static final CostType BUSINESS_FOOD = new CostType(13);
	public static final CostType BUSINESS_CAR = new CostType(14);
	public static final CostType FROM_SAVINGS_ACCOUNT = new CostType(15);
	public static final CostType INVESTMENT = new CostType(16);
	public static final CostType TO_PRIVATE_ACCOUNT = new CostType(17);
	public static final CostType FROM_PRIVATE_ACCOUNT = new CostType(18);	
	public static final CostType BUSINESS_CAR_OTHER_ACCOUNT = new CostType(19);	
	public static final CostType BUSINESS_FOOD_OTHER_ACCOUNT = new CostType(20);
	public static final CostType BUSINESS_TRAVEL_CREDIT_CARD = new CostType(21);
	public static final CostType BUSINESS_LITERATURE_CREDIT_CARD_NO_VAT = new CostType(22);
	
	@Deprecated
	public static final CostType DEPRECIATION_CAR = new CostType(23);
	
	@Deprecated
	public static final CostType FISCALE_BIJTELLING_AUTO = new CostType(24);
	
	public static final CostType INVESTMENT_OTHER_ACCOUNT = new CostType(26);
	public static final CostType ADVERTENTIE = new CostType(27);
	public static final CostType ADVERTENTIE_ZONDER_BTW = new CostType(28);
	public static final CostType INCOME_TAX = new CostType(29);
	public static final CostType INCOME_TAX_PAID_BACK = new CostType(30);
	public static final CostType ROAD_TAX = new CostType(31);
	public static final CostType UITGAVE_CREDIT_CARD = new CostType(32);
	public static final CostType INTEREST = new CostType(33);
	public static final CostType VAT_PAID_BACK_ON_OTHER_ACCOUNT = new CostType(34);	
	
	@Deprecated
	public static final CostType VAT_CORRECTION_CAR_DEPRECIATION = new CostType(35);
	
	public static final CostType VAT_CORRECTION_CAR_PRIVATE = new CostType(36);
	public static final CostType INVOICE_PAID = new CostType(38);	
	public static final CostType INVOICE_SENT = new CostType(39);
	public static final CostType REPURCHASES = new CostType(40);
	public static final CostType SETTLEMENT = new CostType(41);
	public static final CostType SETTLEMENT_INTEREST = new CostType(42);
	
	@Deprecated
	public static final CostType DEPRECIATION_SETTLEMENT = new CostType(43);
	
	public static final CostType SETTLEMENT_OTHER_ACCOUNT = new CostType(44);
	public static final CostType SETTLEMENT_DISCOUNT = new CostType(45);
	public static final CostType INCOME_CURRENT_ACCOUNT_IGNORE = new CostType(46);
	public static final CostType EXPENSE_INSIDE_EU = new CostType(47);	

//	public static final int MAXIMALE_FOR = 11227; // 2008
//	public static final int MAXIMALE_FOR = 11590; // 2009
//	public static final int MAXIMALE_FOR = 11811; // 2010
//	public static final int MAXIMALE_FOR = 11882; // 2011
	public static final BigInteger MAXIMALE_FOR = BigInteger.valueOf(9542); // 2012	
	public static final BigDecimal FOR_PERCENTAGE = BigDecimal.valueOf(0.12f);
	
//	public static final float FOOD_TAXFREE_PERCENTAGE = 0.75f;
	// http://www.belastingdienst.nl/zakelijk/ondernemen_kosten/ondernemen_kosten-09.html
	public static final float FOOD_TAXFREE_PERCENTAGE = 0.735f; // 2011
	
	public static final int INVESTMENT_MINIMUM_AMOUNT = 450;
	

}
