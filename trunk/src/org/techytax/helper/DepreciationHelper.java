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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.techytax.dao.BoekDao;
import org.techytax.dao.BoekwaardeDao;
import org.techytax.domain.Activa;
import org.techytax.domain.Aftrekpost;
import org.techytax.domain.Boekwaarde;
import org.techytax.domain.Kost;
import org.techytax.domain.Periode;
import org.techytax.util.DateConverter;
import org.techytax.util.DateHelper;

public class DepreciationHelper {

	/**
	 * Split the cost into yearly depreciations. By default, there will be
	 * 5 depreciaton terms, resulting in a restvalue of 10%.
	 * 
	 * @param kost
	 * @return
	 */
	public List<Kost> verdeelKosten(Kost kost) throws Exception {
		// Use the net value.
		BigDecimal aanschafKost = kost.getBedrag();
		BigDecimal restWaarde = aanschafKost.divide(new BigDecimal(10));
		BigDecimal jaarlijkseAfschrijving = (aanschafKost.subtract(restWaarde))
				.divide(new BigDecimal(5));
		// afronden
		jaarlijkseAfschrijving = jaarlijkseAfschrijving.setScale(0,
				BigDecimal.ROUND_UP);
		List<Kost> kostLijst = new ArrayList<Kost>();
		Calendar cal = new GregorianCalendar();
		cal.setTime(DateConverter.stringToDate(kost.getDatum()));
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		BigDecimal boekwaardeBegin = aanschafKost;
		BigDecimal boekwaarde = aanschafKost.subtract(jaarlijkseAfschrijving);
		boekwaarde = boekwaarde.setScale(0, BigDecimal.ROUND_UP);
		for (int i = 0; i < 5; i++) {
			Kost afschrijving = new Kost();
			afschrijving.setBtw(new BigDecimal(0));
			afschrijving.setKostenSoortId(0);
			// TODO: translate
			afschrijving
					.setKostenSoortOmschrijving("Aan te geven als afschrijving");
			System.out.println("Afschrijving datum: "
					+ DateConverter.getDate(cal.getTime()));
			afschrijving.setDatum(DateConverter.getDate(cal.getTime()));
			afschrijving.setBedrag(jaarlijkseAfschrijving);
			afschrijving.setOmschrijving("Afschrijving: " + (i + 1) + ", item "
					+ kost.getId() + ", boekwaarde begin: " + boekwaardeBegin
					+ ", boekwaarde eind: " + boekwaarde);
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
	 * 
	 * @param afschrijvingen
	 */
	public void toevoegenAfschrijvingenAanActiva() throws Exception {
		BoekDao boekDao = new BoekDao();
		Periode periode = DateHelper.getPeriodeVorigJaar();
		BigDecimal totaalAfschrijvingenOverig = new BigDecimal("0");
		BigDecimal totaalAfschrijvingenAuto = new BigDecimal("0");
		List<Aftrekpost> aftrekpostenLijst = boekDao.getDeductableCosts(
				DateHelper.getDate(periode.getBeginDatum()), DateHelper
						.getDate(periode.getEindDatum()));
		totaalAfschrijvingenAuto = BalanceCalculator
				.getAfschrijvingAuto(aftrekpostenLijst);
		totaalAfschrijvingenOverig = BalanceCalculator
				.getOverigeAfschrijvingen(aftrekpostenLijst);
		Boekwaarde boekwaarde = new Boekwaarde();
		int ditJaar = DateConverter.getJaar(periode.getBeginDatum());
		boekwaarde.setJaar(ditJaar);
		boekwaarde.setBalansId(Activa.CAR);
		BoekwaardeDao boekwaardeDao = new BoekwaardeDao();
		boekwaarde = boekwaardeDao.getVorigeBoekwaarde(boekwaarde);
		boekwaarde.setId(0);
		boekwaarde.setJaar(ditJaar);
		boekwaarde.setSaldo(boekwaarde.getSaldo()
				- totaalAfschrijvingenAuto.intValue());
		boekwaardeDao.insertBoekwaarde(boekwaarde);
		boekwaarde.setBalansId(Activa.MACHINERY);
		boekwaarde = boekwaardeDao.getVorigeBoekwaarde(boekwaarde);
		boekwaarde.setId(0);
		boekwaarde.setJaar(ditJaar);
		boekwaarde.setSaldo(boekwaarde.getSaldo()
				- totaalAfschrijvingenOverig.intValue());
		boekwaardeDao.insertBoekwaarde(boekwaarde);
		return;
	}
}
