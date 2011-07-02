/**
 * Copyright 2009 Hans Beemsterboer
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

public interface KostConstanten {

	public static final int ONBEPAALD = 0;
	public static final int INKOMST_DEZE_REKENING = 1;
	public static final int UITGAVE_DEZE_REKENING = 2;
	public static final int UITGAVE_DEZE_REKENING_FOUTIEF = 3;
	public static final int INLEG = 4;
	public static final int OPNAME = 5;
	public static final int UITGAVE_ANDERE_REKENING_FOUTIEF = 6;
	public static final int UITGAVE_ANDERE_REKENING = 7;
	public static final int REISKOST_ANDERE_REKENING_FOUTIEF = 8;
	public static final int REISKOST = 9;
	public static final int UITGAVE_DEZE_REKENING_FOUTIEF_GESPREIDE_TERUGBETALING = 10;
	public static final int UITGAVE_DEZE_REKENING_GESPREIDE_TERUGBETALING = 11;
	public static final int OMZET_BELASTING = 12;
	public static final int ZAKELIJK_ETENTJE = 13;
	public static final int AUTO_VAN_DE_ZAAK = 14;
	public static final int AUTO_VAN_DE_ZAAK_ANDERE_REKENING = 19;	
	public static final int BUSINESS_FOOD_OTHER_ACCOUNT = 20;	
	public static final int AFSCHRIJVING_AUTO = 23;
	public static final int FISCALE_BIJTELLING_AUTO = 24;
	public static final int AFSCHRIJVING = 25;
	public static final int ADVERTENTIE = 27;
	public static final int ADVERTENTIE_ZONDER_BTW = 28;
	public static final int INKOMSTEN_BELASTING = 29;
	public static final int WEGEN_BELASTING = 31;
	public static final int UITGAVE_CREDIT_CARD = 32;
	public static final int VAT_CORRECTION_CAR_PRIVATE = 36;
	public static final int PAID_INVOICE_ = 38;
	public static final int SENT_INVOICE = 39;

//	public static final int MAXIMALE_FOR = 11227; // 2008
//	public static final int MAXIMALE_FOR = 11590; // 2009
	public static final int MAXIMALE_FOR = 11811; // 2010	
	public static final float FOR_PERCENTAGE = 0.12f;
	public static final float FOOD_TAXFREE_PERCENTAGE = 0.75f;
	public static final int INVESTMENT_MINIMUM_AMOUNT = 450;	

}
