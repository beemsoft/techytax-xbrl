/**
 * Copyright 2011 Hans Beemsterboer
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
package org.techytax.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.techytax.dao.AccountDao;
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Account;
import org.techytax.domain.AccountBalance;
import org.techytax.domain.Aftrekpost;
import org.techytax.domain.Balans;
import org.techytax.domain.KeyId;
import org.techytax.domain.Kost;
import org.techytax.domain.KostConstanten;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Liquiditeit;
import org.techytax.domain.Reiskosten;
import org.techytax.util.DateHelper;

public class BalanceCalculator {

	private static KostensoortDao dao = new KostensoortDao();

	public static Balans calculateBtwBalance(List<Kost> res, boolean isForAccountBalance) throws Exception {

		BigDecimal totalBtwOut = new BigDecimal(0);
		BigDecimal totalBtwIn = new BigDecimal(0);
		BigDecimal totalBtwCorrection = new BigDecimal(0);
		BigDecimal brutoOmzet = new BigDecimal(0);
		BigDecimal nettoOmzet = new BigDecimal(0);
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {
				Kost obj = null;
				obj = res.get(i);
				if (obj != null) {

					long id = obj.getKostenSoortId();
					if (obj.getKostenSoortId() == KostConstanten.INVOICE_SENT && isForAccountBalance) {
						// skip
					} else {
						Kostensoort kostensoort = dao.getKostensoort(Long.toString(id));
						if (kostensoort.isBtwVerrekenbaar()) {
							if (kostensoort.isBijschrijving() || kostensoort.getKostenSoortId() == KostConstanten.INVOICE_SENT) {
								totalBtwIn = totalBtwIn.add(obj.getBtw());
								brutoOmzet = brutoOmzet.add(obj.getBedrag());
								nettoOmzet = nettoOmzet.add(obj.getBedrag());
								brutoOmzet = brutoOmzet.add(obj.getBtw());
							} else {
								if (kostensoort.getKostenSoortId() == KostConstanten.VAT_CORRECTION_CAR_PRIVATE) {
									totalBtwCorrection = totalBtwCorrection.add(obj.getBtw());
								} else {
									totalBtwOut = totalBtwOut.add(obj.getBtw());
								}
							}
						}
					}
				}
			}
		}
		Balans balans = new Balans();
		balans.setTotaleBaten(totalBtwIn);
		balans.setTotaleKosten(totalBtwOut);
		balans.setBrutoOmzet(brutoOmzet);
		balans.setNettoOmzet(nettoOmzet);
		balans.setCorrection(totalBtwCorrection);
		return balans;
	}

	public static BigDecimal getActualAccountBalance(String beginDatum, String eindDatum, long userId) throws Exception {
		AccountDao accountDao = new AccountDao();
		Account businessAccount = accountDao.getBusinessAccount(userId);
		if (businessAccount != null) {
			KeyId key = new KeyId();
			key.setUserId(userId);
			key.setId(businessAccount.getId());
			List<AccountBalance> accountBalances = accountDao.getAccountBalance(key);
			Collections.sort(accountBalances);
			BigDecimal beginAmount = null;
			BigDecimal endAmount = null;
			for (AccountBalance accountBalance : accountBalances) {
				if (DateHelper.hasOneDayDifference(accountBalance.getDatum(), beginDatum)) {
					beginAmount = accountBalance.getBalance();
				}
				if (beginAmount != null) {
					if (DateHelper.hasOneDayDifference(accountBalance.getDatum(), eindDatum)) {
						endAmount = accountBalance.getBalance();
					}
					if (endAmount != null) {
						return endAmount.subtract(beginAmount);
					}
				}
			}
		}
		return null;
	}

	public static Liquiditeit calculateAccountBalance(List<Kost> res) throws Exception {
		BigDecimal totalKost = new BigDecimal(0);
		BigDecimal totalInleg = new BigDecimal(0);
		BigDecimal totalOpname = new BigDecimal(0);
		BigDecimal totalSparen = new BigDecimal(0);
		Liquiditeit liquiditeit = new Liquiditeit();
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {
				Kost obj = null;
				obj = res.get(i);
				if (obj != null) {
					long id = obj.getKostenSoortId();
					String descr = obj.getOmschrijving();
					Kostensoort kostensoort = dao.getKostensoort(Long.toString(id));
					if (kostensoort.isBalansMeetellen()) {
						if (kostensoort.isBijschrijving()) {
							totalKost = totalKost.add(obj.getBedrag());
							totalKost = totalKost.add(obj.getBtw());
							if (descr.contains("prive inleg") || id == KostConstanten.FROM_PRIVATE_ACCOUNT) {
								totalInleg = totalInleg.add(obj.getBedrag());
							}
							if (descr.equals("Inleg vanaf spaarrekening") || descr.startsWith("VAN Toprekening")
									|| descr.startsWith("VAN Profijtrekening") || id == KostConstanten.FROM_SAVINGS_ACCOUNT) {
								totalSparen = totalSparen.subtract(obj.getBedrag());
							}
						} else {
							totalKost = totalKost.subtract(obj.getBedrag());
							totalKost = totalKost.subtract(obj.getBtw());
							if (descr.contains("naar spaarrekening") || descr.startsWith("NAAR Toprekening")
									|| descr.startsWith("NAAR Profijtrekening") || descr.startsWith("NAAR Spaardeposito") || id == KostConstanten.TO_SAVINGS_ACCOUNT) {
								totalSparen = totalSparen.add(obj.getBedrag());
							} else if (descr.contains("prive opname") || descr.contains("OPL. CHIPKNIP") || id == KostConstanten.TO_PRIVATE_ACCOUNT) {
								totalOpname = totalOpname.add(obj.getBedrag());
							}
						}
					}
				}
			}
		}
		liquiditeit.setPriveBalans(totalInleg.subtract(totalOpname));
		liquiditeit.setRekeningBalans(totalKost);
		liquiditeit.setSpaarBalans(totalSparen);
		return liquiditeit;
	}

	public static boolean meetellenVoorAccount(long id) throws Exception {
		Kostensoort kostensoort = dao.getKostensoort(Long.toString(id));
		return kostensoort.isBalansMeetellen();
	}

	public static boolean optellenVoorAccount(long id) throws Exception {
		Kostensoort kostensoort = dao.getKostensoort(Long.toString(id));
		return kostensoort.isBalansMeetellen() && kostensoort.isBijschrijving();
	}

	public static Balans calculatCostBalance(List<Kost> res) {

		BigDecimal totalKost = new BigDecimal(0);
		BigDecimal totalBaat = new BigDecimal(0);
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {
				Kost obj = null;
				obj = res.get(i);
				if (obj != null) {
					long id = obj.getKostenSoortId();
					if (id == KostConstanten.INKOMST_DEZE_REKENING) {
						totalBaat = totalBaat.add(obj.getBedrag());
						// BTW niet meenemen
						// totalBaat = totalBaat.add(obj.getBtw());
					} else if (id == KostConstanten.UITGAVE_DEZE_REKENING || id == KostConstanten.UITGAVE_DEZE_REKENING_FOUTIEF || id == KostConstanten.REISKOST_ANDERE_REKENING_FOUTIEF
							|| id == KostConstanten.REISKOST || id == KostConstanten.AUTO_VAN_DE_ZAAK || id == KostConstanten.AUTO_VAN_DE_ZAAK_ANDERE_REKENING || id == KostConstanten.WEGEN_BELASTING
							|| id == KostConstanten.UITGAVE_CREDIT_CARD || id == KostConstanten.UITGAVE_ANDERE_REKENING || id == KostConstanten.ADVERTENTIE) {
						totalKost = totalKost.add(obj.getBedrag());
						// BTW niet meenemen
						// totalKost = totalKost.add(obj.getBtw());
					} else if (id == KostConstanten.ZAKELIJK_ETENTJE || id == KostConstanten.BUSINESS_FOOD_OTHER_ACCOUNT) {
						// Do not apply tax deduction to this cost.
						totalKost = totalKost.add(obj.getBedrag());
					}
				}
			}
		}
		Balans balans = new Balans();
		balans.setTotaleBaten(totalBaat);
		balans.setTotaleKosten(totalKost);
		return balans;
	}

	public static Balans calculateCostBalanceCurrentAccount(List<Kost> res, boolean isIncludingVat) {

		BigDecimal totalKost = new BigDecimal(0);
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {
				Kost obj = null;
				obj = res.get(i);
				if (obj != null) {
					totalKost = totalKost.add(obj.getBedrag());
					if (isIncludingVat) {
						totalKost = totalKost.add(obj.getBtw());
					}
				}
			}
		}
		Balans balans = new Balans();
		balans.setTotaleKosten(totalKost);
		return balans;
	}
	
	public static BigDecimal calculateTotalPaidInvoices(List<Kost> res) {
		BigDecimal total = new BigDecimal(0);
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {
				Kost obj = null;
				obj = res.get(i);
				if (obj != null) {
					long id = obj.getKostenSoortId();
					if (id == KostConstanten.INVOICE_PAID) {
						total = total.add(obj.getBedrag()).add(obj.getBtw());
					}
				}
			}
		}
		return total;
	}	

	public static Reiskosten calculatTravelCostBalance(List<Kost> res) {
		Reiskosten reiskosten = new Reiskosten();
		BigDecimal totalKostOV = new BigDecimal(0);
		BigDecimal totalKostAuto = new BigDecimal(0);
		BigDecimal totalKostAutoMetBtw = new BigDecimal(0);
		BigDecimal totalVatCorrection = new BigDecimal(0);
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {
				Kost obj = null;
				obj = res.get(i);
				if (obj != null) {
					long id = obj.getKostenSoortId();
					if (id == KostConstanten.REISKOST || id == KostConstanten.REISKOST_ANDERE_REKENING_FOUTIEF) {
						totalKostOV = totalKostOV.add(obj.getBedrag());
					} else if (id == KostConstanten.AUTO_VAN_DE_ZAAK || id == KostConstanten.AUTO_VAN_DE_ZAAK_ANDERE_REKENING || id == KostConstanten.WEGEN_BELASTING) {
						totalKostAuto = totalKostAuto.add(obj.getBedrag());
						totalKostAutoMetBtw = totalKostAutoMetBtw.add(obj.getBedrag());
						totalKostAutoMetBtw = totalKostAutoMetBtw.add(obj.getBtw());
					} else if (id == KostConstanten.VAT_CORRECTION_CAR_PRIVATE) {
						totalVatCorrection = totalVatCorrection.add(obj.getBtw());
					}
				}
			}
		}
		reiskosten.setOvKosten(totalKostOV);
		reiskosten.setAutoKostenZonderBtw(totalKostAuto);
		reiskosten.setAutoKostenMetBtw(totalKostAutoMetBtw);
		reiskosten.setVatCorrection(totalVatCorrection);
		return reiskosten;
	}

	public static Balans calculateTaxBalance(List<Kost> res) throws Exception {
		BigDecimal total = new BigDecimal(0);
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {
				Kost obj = null;
				obj = res.get(i);
				if (obj != null) {
					if (obj.getKostenSoortId() == KostConstanten.INKOMSTEN_BELASTING_TERUGGAVE) {
						total = total.subtract(obj.getBedrag());
					} else {
						total = total.add(obj.getBedrag());
					}
				}
			}
		}
		Balans balans = new Balans();
		balans.setTotaleKosten(total);
		return balans;
	}

	public static BigDecimal getAfschrijvingAuto(List<Aftrekpost> aftrekpostenLijst) {
		Iterator<Aftrekpost> iterator = aftrekpostenLijst.iterator();
		while (iterator.hasNext()) {
			Aftrekpost aftrekpost = iterator.next();
			if (aftrekpost.getKostenSoortId() == KostConstanten.AFSCHRIJVING_AUTO) {
				return aftrekpost.getAftrekbaarBedrag();
			}
		}
		return null;
	}
	
	public static BigInteger getRepurchase(List<Aftrekpost> aftrekpostenLijst) {
		Iterator<Aftrekpost> iterator = aftrekpostenLijst.iterator();
		BigDecimal repurchases = new BigDecimal("0");
		while (iterator.hasNext()) {
			Aftrekpost aftrekpost = iterator.next();
			if (aftrekpost.getKostenSoortId() == KostConstanten.REPURCHASES) {
				repurchases = repurchases.add(aftrekpost.getAftrekbaarBedrag());
			}
		}
		return repurchases.toBigInteger();
	}	

	public static BigDecimal getOverigeAfschrijvingen(List<Aftrekpost> aftrekpostenLijst) {
		Iterator<Aftrekpost> iterator = aftrekpostenLijst.iterator();
		while (iterator.hasNext()) {
			Aftrekpost aftrekpost = iterator.next();
			if (aftrekpost.getKostenSoortId() == KostConstanten.AFSCHRIJVING) {
				return aftrekpost.getAftrekbaarBedrag();
			}
		}
		return null;
	}

	public static BigDecimal getFiscaleBijtelling(List<Aftrekpost> aftrekpostenLijst) throws Exception {
		Iterator<Aftrekpost> iterator = aftrekpostenLijst.iterator();
		while (iterator.hasNext()) {
			Aftrekpost aftrekpost = iterator.next();
			if (aftrekpost.getKostenSoortId() == KostConstanten.FISCALE_BIJTELLING_AUTO) {
				return aftrekpost.getAftrekbaarBedrag();
			}
		}
		throw new Exception("errors.fiscal.car");
	}

	public static BigDecimal getKostenVoorAuto(List<Aftrekpost> aftrekpostenLijst) {
		Iterator<Aftrekpost> iterator = aftrekpostenLijst.iterator();
		BigDecimal kosten = new BigDecimal("0");
		while (iterator.hasNext()) {
			Aftrekpost aftrekpost = iterator.next();
			if (aftrekpost.getKostenSoortId() == KostConstanten.AUTO_VAN_DE_ZAAK || aftrekpost.getKostenSoortId() == KostConstanten.AUTO_VAN_DE_ZAAK_ANDERE_REKENING
					|| aftrekpost.getKostenSoortId() == KostConstanten.WEGEN_BELASTING) {
				kosten = kosten.add(aftrekpost.getAftrekbaarBedrag());
			}
		}
		return kosten;
	}

	public static BigDecimal getReiskosten(List<Aftrekpost> aftrekpostenLijst) {
		Iterator<Aftrekpost> iterator = aftrekpostenLijst.iterator();
		BigDecimal reiskosten = new BigDecimal("0");
		while (iterator.hasNext()) {
			Aftrekpost aftrekpost = iterator.next();
			if (aftrekpost.getKostenSoortId() == KostConstanten.REISKOST) {
				reiskosten = reiskosten.add(aftrekpost.getAftrekbaarBedrag());
			}
		}
		return reiskosten;
	}

	public static BigDecimal getAlgemeneKosten(List<Aftrekpost> aftrekpostenLijst) {
		Iterator<Aftrekpost> iterator = aftrekpostenLijst.iterator();
		BigDecimal kosten = new BigDecimal("0");
		while (iterator.hasNext()) {
			Aftrekpost aftrekpost = (Aftrekpost) iterator.next();
			if (aftrekpost.getKostenSoortId() == KostConstanten.UITGAVE_DEZE_REKENING || aftrekpost.getKostenSoortId() == KostConstanten.UITGAVE_ANDERE_REKENING
					|| aftrekpost.getKostenSoortId() == KostConstanten.UITGAVE_CREDIT_CARD || aftrekpost.getKostenSoortId() == KostConstanten.ADVERTENTIE
					|| aftrekpost.getKostenSoortId() == KostConstanten.ADVERTENTIE_ZONDER_BTW) {
				kosten = kosten.add(aftrekpost.getAftrekbaarBedrag());
			}
		}
		return kosten;
	}

	public static BigDecimal getEtenskosten(List<Aftrekpost> aftrekpostenLijst) {
		Iterator<Aftrekpost> iterator = aftrekpostenLijst.iterator();
		BigDecimal kosten = new BigDecimal("0");
		while (iterator.hasNext()) {
			Aftrekpost aftrekpost = (Aftrekpost) iterator.next();
			if (aftrekpost.getKostenSoortId() == KostConstanten.ZAKELIJK_ETENTJE || aftrekpost.getKostenSoortId() == KostConstanten.BUSINESS_FOOD_OTHER_ACCOUNT) {
				kosten = kosten.add(aftrekpost.getAftrekbaarBedrag());
			}
		}
		return kosten.multiply(new BigDecimal(KostConstanten.FOOD_TAXFREE_PERCENTAGE));
	}

	public static BigDecimal calculatMonthlyPrivateExpenses(List<Kost> res) throws Exception {
		BigDecimal monthlyExpenses = new BigDecimal(0);
		int nofMonths = 0;
		int lastMonth = -1;
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {
				Kost obj = null;
				obj = res.get(i);
				if (obj != null) {
					if (!obj.isIncoming()) {
						if (!obj.getOmschrijving().contains("spaarrekening") && !obj.getOmschrijving().contains("inleg")) {
							Date datum = DateHelper.stringToDate(obj.getDatum());
							int month = DateHelper.getMonth(datum);
							if (month != lastMonth) {
								lastMonth = month;
								nofMonths++;
							}
							monthlyExpenses = monthlyExpenses.add(obj.getBedrag());
						}
					}
				}
			}
		}
		System.out.println("Expenses: " + monthlyExpenses);
		System.out.println("nofmonths: " + nofMonths);
		if (nofMonths == 0) {
			return monthlyExpenses;
		} else {
			return monthlyExpenses.divide(new BigDecimal(nofMonths), RoundingMode.HALF_UP);
		}
	}

}
