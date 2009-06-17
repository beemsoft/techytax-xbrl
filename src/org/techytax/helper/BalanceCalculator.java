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
package org.techytax.helper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.techytax.dao.BoekDao;
import org.techytax.dao.BoekwaardeDao;
import org.techytax.dao.FiscaalDao;
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Activa;
import org.techytax.domain.Aftrekpost;
import org.techytax.domain.Balans;
import org.techytax.domain.Boekwaarde;
import org.techytax.domain.FiscalOverview;
import org.techytax.domain.Kost;
import org.techytax.domain.KostConstanten;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Liquiditeit;
import org.techytax.domain.Passiva;
import org.techytax.domain.PriveOnttrekking;
import org.techytax.domain.Reiskosten;
import org.techytax.props.PropsFactory;
import org.techytax.util.DateConverter;

public class BalanceCalculator {

	private static KostensoortDao dao = new KostensoortDao();

	public static Balans calculateBtwBalance(List<Kost> res) throws Exception {

		BigDecimal totalBtwOut = new BigDecimal(0);
		BigDecimal totalBtwIn = new BigDecimal(0);
		BigDecimal brutoOmzet = new BigDecimal(0);
		BigDecimal nettoOmzet = new BigDecimal(0);
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {
				Kost obj = null;
				obj = res.get(i);
				if (obj != null) {

					long id = obj.getKostenSoortId();
					Kostensoort kostensoort = dao.getKostensoort(Long
							.toString(id));
					if (kostensoort.isBtwVerrekenbaar()) {
						if (kostensoort.isBijschrijving()) {
							totalBtwIn = totalBtwIn.add(obj.getBtw());
							brutoOmzet = brutoOmzet.add(obj.getBedrag());
							nettoOmzet = nettoOmzet.add(obj.getBedrag());
							brutoOmzet = brutoOmzet.add(obj.getBtw());
						} else {
							totalBtwOut = totalBtwOut.add(obj.getBtw());
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
		return balans;
	}

	public static Liquiditeit calculatAccountBalance(List<Kost> res)
			throws Exception {
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
					Kostensoort kostensoort = dao.getKostensoort(Long
							.toString(id));
					if (kostensoort.isBalansMeetellen()) {
						if (kostensoort.isBijschrijving()) {
							System.out.println("plus "
									+ obj.getBedrag().add(obj.getBtw()));
							totalKost = totalKost.add(obj.getBedrag());
							totalKost = totalKost.add(obj.getBtw());
							// if (id == KostConstanten.INLEG) {
							// totalInleg = totalInleg.add(obj.getBedrag());
							// }
							if (obj.getOmschrijving().contains("prive inleg")) {
								totalInleg = totalInleg.add(obj.getBedrag());
							}
							if (obj.getOmschrijving().equals(
									"Inleg vanaf spaarrekening")) {
								totalSparen = totalSparen.subtract(obj
										.getBedrag());
							}
						} else {
							System.out.println("min "
									+ obj.getBedrag().add(obj.getBtw()));
							totalKost = totalKost.subtract(obj.getBedrag());
							totalKost = totalKost.subtract(obj.getBtw());
							if (obj.getOmschrijving().equals(
									"naar spaarrekening")) {
								totalSparen = totalSparen.add(obj.getBedrag());
							} else if (obj.getOmschrijving().contains(
									"prive opname")) {
								totalOpname = totalOpname.add(obj.getBedrag());
							}
							// else if (id == KostConstanten.OPNAME) {
							// totalOpname = totalOpname.add(obj.getBedrag());
							// }

						}
					}
				}
				if (i % 10 == 0) {
					System.out.println("Tussenresultaat balans: " + totalKost);
				}
			}
		}
		System.out.println("Totale inleg: " + totalInleg);
		System.out.println("Totale opname: " + totalOpname);
		System.out.println("Totale spaarsaldo: " + totalSparen);
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
					} else if (id == KostConstanten.UITGAVE_DEZE_REKENING
							|| id == KostConstanten.UITGAVE_DEZE_REKENING_FOUTIEF
							|| id == KostConstanten.REISKOST_ANDERE_REKENING_FOUTIEF
							|| id == KostConstanten.REISKOST
							|| id == KostConstanten.AUTO_VAN_DE_ZAAK
							|| id == KostConstanten.AUTO_VAN_DE_ZAAK_ANDERE_REKENING
							|| id == KostConstanten.WEGEN_BELASTING
							|| id == KostConstanten.UITGAVE_CREDIT_CARD
							|| id == KostConstanten.UITGAVE_ANDERE_REKENING) {
						totalKost = totalKost.add(obj.getBedrag());
						// BTW niet meenemen
						// totalKost = totalKost.add(obj.getBtw());
					} else if (id == KostConstanten.ZAKELIJK_ETENTJE) {
						// Do not apply tax deduction to this cost.
						totalKost = totalKost
						.add(obj
								.getBedrag());						
					}
				}
			}
		}
		Balans balans = new Balans();
		balans.setTotaleBaten(totalBaat);
		balans.setTotaleKosten(totalKost);
		return balans;
	}

	public static Reiskosten calculatTravelCostBalance(List<Kost> res) {
		Reiskosten reiskosten = new Reiskosten();
		BigDecimal totalKostOV = new BigDecimal(0);
		BigDecimal totalKostAuto = new BigDecimal(0);
		BigDecimal totalKostAutoMetBtw = new BigDecimal(0);
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {
				Kost obj = null;
				obj = res.get(i);
				if (obj != null) {
					long id = obj.getKostenSoortId();
					if (id == KostConstanten.REISKOST
							|| id == KostConstanten.REISKOST_ANDERE_REKENING_FOUTIEF) {
						totalKostOV = totalKostOV.add(obj.getBedrag());
						// BTW niet meenemen
					}
					if (id == KostConstanten.AUTO_VAN_DE_ZAAK
							|| id == KostConstanten.AUTO_VAN_DE_ZAAK_ANDERE_REKENING
							|| id == KostConstanten.WEGEN_BELASTING) {
						totalKostAuto = totalKostAuto.add(obj.getBedrag());
						totalKostAutoMetBtw = totalKostAutoMetBtw.add(obj
								.getBedrag());
						totalKostAutoMetBtw = totalKostAutoMetBtw.add(obj
								.getBtw());
						// BTW niet meenemen
					}
				}
			}
		}
		System.out.println("Totale kosten OV: " + totalKostOV);
		System.out.println("Totale kosten auto: " + totalKostAuto);
		reiskosten.setOvKosten(totalKostOV);
		reiskosten.setAutoKostenZonderBtw(totalKostAuto);
		reiskosten.setAutoKostenMetBtw(totalKostAutoMetBtw);
		return reiskosten;
	}

	public static BigDecimal getAfschrijvingAuto(
			List<Aftrekpost> aftrekpostenLijst) {
		Iterator<Aftrekpost> iterator = aftrekpostenLijst.iterator();
		while (iterator.hasNext()) {
			Aftrekpost aftrekpost = iterator.next();
			if (aftrekpost.getKostenSoortId() == KostConstanten.AFSCHRIJVING_AUTO) {
				return aftrekpost.getAftrekbaarBedrag();
			}
		}
		return null;
	}

	public static BigDecimal getOverigeAfschrijvingen(
			List<Aftrekpost> aftrekpostenLijst) {
		Iterator<Aftrekpost> iterator = aftrekpostenLijst.iterator();
		while (iterator.hasNext()) {
			Aftrekpost aftrekpost = iterator.next();
			if (aftrekpost.getKostenSoortId() == KostConstanten.AFSCHRIJVING) {
				return aftrekpost.getAftrekbaarBedrag();
			}
		}
		return null;
	}

	public static BigDecimal getFiscaleBijtelling(
			List<Aftrekpost> aftrekpostenLijst) throws Exception {
		Iterator<Aftrekpost> iterator = aftrekpostenLijst.iterator();
		while (iterator.hasNext()) {
			Aftrekpost aftrekpost = iterator.next();
			if (aftrekpost.getKostenSoortId() == KostConstanten.FISCALE_BIJTELLING_AUTO) {
				return aftrekpost.getAftrekbaarBedrag();
			}
		}
		throw new Exception("errors.fiscal.car");
	}

	public static BigDecimal getKostenVoorAuto(
			List<Aftrekpost> aftrekpostenLijst) {
		Iterator<Aftrekpost> iterator = aftrekpostenLijst.iterator();
		BigDecimal kosten = new BigDecimal("0");
		while (iterator.hasNext()) {
			Aftrekpost aftrekpost = iterator.next();
			if (aftrekpost.getKostenSoortId() == KostConstanten.AUTO_VAN_DE_ZAAK
					|| aftrekpost.getKostenSoortId() == KostConstanten.AUTO_VAN_DE_ZAAK_ANDERE_REKENING
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

	public static BigDecimal getAlgemeneKosten(
			List<Aftrekpost> aftrekpostenLijst) {
		Iterator<Aftrekpost> iterator = aftrekpostenLijst.iterator();
		BigDecimal kosten = new BigDecimal("0");
		while (iterator.hasNext()) {
			Aftrekpost aftrekpost = (Aftrekpost) iterator.next();
			if (aftrekpost.getKostenSoortId() == KostConstanten.UITGAVE_DEZE_REKENING
					|| aftrekpost.getKostenSoortId() == KostConstanten.UITGAVE_ANDERE_REKENING
					|| aftrekpost.getKostenSoortId() == KostConstanten.UITGAVE_CREDIT_CARD) {
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
			if (aftrekpost.getKostenSoortId() == KostConstanten.ZAKELIJK_ETENTJE) {
				kosten = kosten.add(aftrekpost.getAftrekbaarBedrag());
			}
		}
		return kosten.multiply(new BigDecimal(
				KostConstanten.FOOD_TAXFREE_PERCENTAGE));
	}

}
