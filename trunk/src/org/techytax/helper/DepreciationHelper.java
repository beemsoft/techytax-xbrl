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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import org.techytax.dao.BoekDao;
import org.techytax.dao.BoekwaardeDao;
import org.techytax.domain.Activa;
import org.techytax.domain.Aftrekpost;
import org.techytax.domain.Boekwaarde;
import org.techytax.domain.Kost;
import org.techytax.domain.KostConstanten;
import org.techytax.domain.Periode;
import org.techytax.props.PropsFactory;
import org.techytax.util.DateHelper;

public class DepreciationHelper {

	/**
	 * Split the cost into yearly depreciations. The number of depreciation
	 * terms is read from the properties file. The restvalue is 10%.
	 * 
	 * @param kost
	 * @return
	 */
	public List<Kost> verdeelKosten(Kost kost) throws Exception {
		Properties props = PropsFactory.loadProperties();
		String nofYearsString = props.getProperty("depreciation.terms");
		int nofYears = Integer.parseInt(nofYearsString);

		// Use the net value.
		BigDecimal aanschafKost = kost.getBedrag();
		BigDecimal restWaarde = aanschafKost.divide(new BigDecimal(10));
		BigDecimal jaarlijkseAfschrijving = (aanschafKost.subtract(restWaarde)).divide(new BigDecimal(nofYears));
		// afronden
		jaarlijkseAfschrijving = jaarlijkseAfschrijving.setScale(0, BigDecimal.ROUND_UP);
		List<Kost> kostLijst = new ArrayList<Kost>();
		Calendar cal = new GregorianCalendar();
		cal.setTime(DateHelper.stringToDate(kost.getDatum()));
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		BigDecimal boekwaardeBegin = aanschafKost;
		BigDecimal boekwaarde = aanschafKost.subtract(jaarlijkseAfschrijving);
		boekwaarde = boekwaarde.setScale(0, BigDecimal.ROUND_UP);
		for (int i = 0; i < nofYears; i++) {
			Kost afschrijving = new Kost();
			afschrijving.setBtw(new BigDecimal(0));
			afschrijving.setKostenSoortId(KostConstanten.AFSCHRIJVING);
			afschrijving.setKostenSoortOmschrijving("costtype.depreciation");
			afschrijving.setDatum(DateHelper.getDate(cal.getTime()));
			afschrijving.setBedrag(jaarlijkseAfschrijving.setScale(2));
			afschrijving.setOmschrijving("Afschrijving: " + (i + 1) + ", item " + kost.getId() + ", boekwaarde begin: " + boekwaardeBegin + ", boekwaarde eind: " + boekwaarde);
			kostLijst.add(afschrijving);
			cal.add(Calendar.YEAR, 1);
			boekwaardeBegin = boekwaarde;
			boekwaarde = boekwaarde.subtract(jaarlijkseAfschrijving);
		}
		return kostLijst;
	}

	/**
	 * Voeg de hele lijst met afschrijvingen toe aan de Activa balans. Maak
	 * hierbij onderscheid tussen afschrijvingen voor de auto van de zaak en
	 * overige afschrijvingen.
	 */
	public void toevoegenAfschrijvingenAanActiva(String userId) throws Exception {
		BoekDao boekDao = new BoekDao();
		Periode periode = DateHelper.getPeriodeVorigJaar();
		BigDecimal totaalAfschrijvingenOverig = new BigDecimal("0");
		BigDecimal totaalAfschrijvingenAuto = new BigDecimal("0");
		List<Aftrekpost> aftrekpostenLijst = boekDao.getDeductableCosts(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()), userId);
		totaalAfschrijvingenAuto = BalanceCalculator.getAfschrijvingAuto(aftrekpostenLijst);
		totaalAfschrijvingenOverig = BalanceCalculator.getOverigeAfschrijvingen(aftrekpostenLijst);
		Boekwaarde boekwaarde = new Boekwaarde();
		int ditJaar = DateHelper.getYear(periode.getBeginDatum());
		boekwaarde.setJaar(ditJaar);
		boekwaarde.setBalansId(Activa.CAR);
		boekwaarde.setUserId(Long.parseLong(userId));
		BoekwaardeDao boekwaardeDao = new BoekwaardeDao();
		boekwaarde = boekwaardeDao.getVorigeBoekwaarde(boekwaarde);
		if (boekwaarde != null) {
			boekwaarde.setId(0);
			boekwaarde.setJaar(ditJaar);
			boekwaarde.setSaldo(boekwaarde.getSaldo().subtract(totaalAfschrijvingenAuto.toBigInteger()));
			boekwaarde.setUserId(Long.parseLong(userId));
			boekwaardeDao.insertBoekwaarde(boekwaarde);

			boekwaarde.setBalansId(Activa.MACHINERY);
			boekwaarde = boekwaardeDao.getVorigeBoekwaarde(boekwaarde);

			boekwaarde.setId(0);
			boekwaarde.setJaar(ditJaar);
			boekwaarde.setSaldo(boekwaarde.getSaldo().subtract(totaalAfschrijvingenOverig.toBigInteger()));
			boekwaarde.setUserId(Long.parseLong(userId));
			boekwaardeDao.insertBoekwaarde(boekwaarde);
		}
	}
}
