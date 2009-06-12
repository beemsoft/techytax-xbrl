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
import org.techytax.domain.FiscaalOverzicht;
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
//						totalKost = totalKost
//								.add(obj
//										.getBedrag()
//										.multiply(
//												new BigDecimal(
//														KostConstanten.FOOD_TAXFREE_PERCENTAGE)));
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

	public static int berekenWinst(FiscaalOverzicht overzicht) {
		int nettoOmzet = overzicht.getNettoOmzet();
		nettoOmzet -= overzicht.getAfschrijvingAuto();
		nettoOmzet -= overzicht.getAfschrijvingOverig();
		int kostenAuto = overzicht.getKostenAuto()
				- overzicht.getBijtellingAuto();
		if (kostenAuto > 0) {
			nettoOmzet -= kostenAuto;
		}
		nettoOmzet -= overzicht.getKostenOverigTransport();
		nettoOmzet -= overzicht.getKostenOverig();
		return nettoOmzet;
	}

	public static FiscaalOverzicht maakFiscaalOverzicht(String beginDatum,
			String eindDatum, List<Kost> boekingen) throws Exception {

		// Load properties
		Properties props = PropsFactory.loadProperties();

		FiscaalOverzicht overzicht = new FiscaalOverzicht();
		PriveOnttrekking onttrekking = new PriveOnttrekking();
		Liquiditeit liquiditeit = null;

		int forLimiet = KostConstanten.MAXIMALE_FOR;
		Date datum = DateConverter.stringToDate(beginDatum);
		int jaar = DateConverter.getJaar(datum);
		System.out.println("Jaar: " + jaar);

		// Maak winst-en-verlies rekening op
		Balans btwBalans = calculateBtwBalance(boekingen);

		BoekDao boekDao = new BoekDao();
		List<Aftrekpost> aftrekpostenLijst = boekDao.getAftrekpostLijst(
				beginDatum, eindDatum, "aftrekpostenLijst");
		overzicht.setJaar(jaar);
		overzicht.setNettoOmzet(btwBalans.getNettoOmzet().intValue());
		BigDecimal afschrijvingAuto = getAfschrijvingAuto(aftrekpostenLijst);
		if (afschrijvingAuto != null) {
			overzicht.setAfschrijvingAuto(afschrijvingAuto.intValue());
			overzicht.setBijtellingAuto(getFiscaleBijtelling(aftrekpostenLijst)
					.intValue());
			overzicht.setKostenAuto(getKostenVoorAuto(aftrekpostenLijst)
					.intValue());
		}
		BigDecimal depreciationOther = getOverigeAfschrijvingen(aftrekpostenLijst);
		if (depreciationOther != null) {
			overzicht.setAfschrijvingOverig(getOverigeAfschrijvingen(
					aftrekpostenLijst).intValue());
		}

		overzicht.setKostenOverigTransport(getReiskosten(aftrekpostenLijst)
				.intValue());
		overzicht.setKostenOverig(getEtenskosten(aftrekpostenLijst).add(
				getAlgemeneKosten(aftrekpostenLijst)).intValue());
		overzicht.setWinst(berekenWinst(overzicht));
		int maximaleFor = (int) (overzicht.getWinst() * KostConstanten.FOR_PERCENTAGE);
		System.out.println("Maximale FOR: " + maximaleFor);
		if (maximaleFor > forLimiet) {
			maximaleFor = forLimiet;
		}
		overzicht.setOudedagsReserveMaximaal(maximaleFor);

		// Maak activa balans op.

		// Bereken liquide middelen.
		BoekwaardeDao boekwaardeDao = new BoekwaardeDao();
		Boekwaarde boekwaarde = new Boekwaarde();
		boekwaarde.setBalansId(Activa.CURRENT_ASSETS);
		boekwaarde.setJaar(jaar);
		boekwaarde = boekwaardeDao.getBoekwaardeDitJaar(boekwaarde);

		// Alleen voor het eerste boekjaar??
		if (boekwaarde == null) {
			String startDate = props.getProperty("start.date");
			List<Kost> rekeningLijst = boekDao.getKostLijst(startDate,
					eindDatum, "rekeningBalans");
			liquiditeit = calculatAccountBalance(rekeningLijst);

			int saldo = liquiditeit.getRekeningBalans().intValue();
			saldo += liquiditeit.getSpaarBalans().intValue();
			boekwaarde = new Boekwaarde();
			boekwaarde.setJaar(jaar);
			boekwaarde.setBalansId(Activa.CURRENT_ASSETS);
			boekwaarde.setSaldo(saldo);

			boekwaardeDao.insertBoekwaarde(boekwaarde);
		} else {
			List<Kost> rekeningLijst = boekDao.getKostLijst(beginDatum,
					eindDatum, "rekeningBalans");
			liquiditeit = calculatAccountBalance(rekeningLijst);
		}
		FiscaalDao fiscaalDao = new FiscaalDao();
		List<Activa> activaLijst = fiscaalDao.getActivaLijst(Integer
				.toString(jaar));

		if (!checkActivaOpgegeven(activaLijst, jaar)) {
			throw new Exception("errors.fiscal.activa");
		}
		overzicht.setActiva(activaLijst);

		// Maak passiva balans op.

		// Voer dezelfde FOR op.
		boekwaarde = new Boekwaarde();
		boekwaarde.setBalansId(Passiva.PENSION);
		boekwaarde.setJaar(jaar);
		boekwaarde = boekwaardeDao.getBoekwaardeDitJaar(boekwaarde);
		int FOR = 0;
		if (boekwaarde == null) {
			boekwaarde = new Boekwaarde();
			boekwaarde.setBalansId(Passiva.PENSION);
			boekwaarde.setJaar(jaar);
			boekwaarde = boekwaardeDao.getVorigeBoekwaarde(boekwaarde);

			if (boekwaarde != null) {
				FOR = boekwaarde.getSaldo();
				boekwaarde.setId(0);
				boekwaarde.setJaar(jaar);
				boekwaardeDao.insertBoekwaarde(boekwaarde);
			}
		}
		// Voer eigen vermogen op.
		boekwaarde = new Boekwaarde();
		boekwaarde.setBalansId(Passiva.NON_CURRENT_ASSETS);
		boekwaarde.setJaar(jaar);
		boekwaarde = boekwaardeDao.getBoekwaardeDitJaar(boekwaarde);
		if (boekwaarde == null) {
			boekwaarde = new Boekwaarde();
			boekwaarde.setBalansId(Passiva.NON_CURRENT_ASSETS);
			boekwaarde.setJaar(jaar);
			boekwaarde.setSaldo(getBalansTotaal(activaLijst, jaar) - FOR);
			boekwaardeDao.insertBoekwaarde(boekwaarde);
		}
		List<Passiva> passivaLijst = fiscaalDao.getPassivaLijst(Integer
				.toString(jaar));
		overzicht.setPassiva(passivaLijst);

		// Vul prive onttrekking in
		if (liquiditeit != null) {
			onttrekking.setOpnameSaldo(liquiditeit.getPriveBalans().intValue());
		}
		Integer belastingKosten = boekDao.getBelastingKosten(beginDatum,
				eindDatum);
		if (belastingKosten != null) {
			onttrekking.setVoorlopigeAanslag(belastingKosten);
			onttrekking.setTeruggave(boekDao.getBelastingTeruggave(beginDatum,
					eindDatum));
		}
		overzicht.setOnttrekking(onttrekking);
		return overzicht;
	}

	private static int getBalansTotaal(List<Activa> activaLijst, int fiscaalJaar) {
		Iterator<Activa> iterator = activaLijst.iterator();
		int totaal = 0;
		while (iterator.hasNext()) {
			Activa activa = iterator.next();
			if (activa.getBoekjaar() == fiscaalJaar) {
				totaal += activa.getSaldo().intValue();
			}
		}
		return totaal;
	}

	private static boolean checkActivaOpgegeven(List<Activa> activaLijst,
			int fiscaalJaar) {
		Iterator<Activa> iterator = activaLijst.iterator();
		while (iterator.hasNext()) {
			Activa activa = iterator.next();
			if (activa.getBoekjaar() == fiscaalJaar) {
				return true;
			}
		}
		return false;
	}
}
