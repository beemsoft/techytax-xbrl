package org.techytax.helper;

import org.apache.commons.lang.StringUtils;

public class DutchTaxPaymentFeatureHelper {

	int[] arGewichten = new int[] { 2, 4, 8, 5, 10, 9, 7, 3, 6, 1, 2, 4, 8, 5, 10 };

	String strHuidigJaarFull = "2013";

	String strHuidigJaar = strHuidigJaarFull.substring(strHuidigJaarFull.length() - 1,

	strHuidigJaarFull.length());

	String sMiddelCode = "B";

	String sSubnr = "01";

	private int sMidddelBetalingskenmerk;

	private final String selectBtwLhJaar = "3";

	private final String selectBtwLhAangiftetijdvak = "40";// Q1: 21, Q2: 24,
															// Q3: 27, Q4: 30,
															// jaar: 40

	public String bereken(String sSofinummer) {

		String sBetalingskenmerk = null;

		// Van btw-nummer naar betalingskenmerk

		if (sMiddelCode == "B") {

			sMidddelBetalingskenmerk = 1;

		} else if (sMiddelCode == "L") {

			sMidddelBetalingskenmerk = 6;

		}

		String aTijdvak = selectBtwLhAangiftetijdvak;

		String sAanslagnummer = maakSofiOp(sSofinummer) + "." + sMiddelCode + "." + sSubnr + "."

		+ selectBtwLhJaar + aTijdvak + "0";

		String tmpBetalingskenmerk = sSofinummer.substring(0, 8) + sMidddelBetalingskenmerk + selectBtwLhJaar

		+ sSubnr + aTijdvak + 0;

		int iControlegetal = 0;

		for (int ii = 0; ii <= 14; ii++) {

			iControlegetal = iControlegetal

			+ Integer.parseInt(Character.toString(tmpBetalingskenmerk.charAt(14 - ii)))

			* arGewichten[ii];

		}

		iControlegetal = 11 - (iControlegetal % 11);

		if (iControlegetal == 10) {

			iControlegetal = 1;

		}

		if (iControlegetal == 11) {

			iControlegetal = 0;

		}

		tmpBetalingskenmerk = iControlegetal + tmpBetalingskenmerk.substring(0, 3) + " "

		+ tmpBetalingskenmerk.substring(3, 7) + " " + tmpBetalingskenmerk.substring(7, 11) + " "

		+ tmpBetalingskenmerk.substring(11);

		sBetalingskenmerk = tmpBetalingskenmerk;

		System.out.println(sAanslagnummer);

		System.out.println(sBetalingskenmerk);

		return sBetalingskenmerk;

	}

	private String verwijderKarakters(String sVal, boolean bCent, boolean bOokNul) {

		String sNewVal;
		if (bCent) {
			sNewVal = sVal.replace("/[^\\d,]/g", "");// laat cijfers en komma
														// staan
			// dubbele komma's verwijderen
			int iKomma = 0;
			StringBuffer sTemp = new StringBuffer();
			for (int i = 0; i < sNewVal.length(); i++) {
				if (sNewVal.substring(i, 1) != "," || (sNewVal.substring(i, 1) == "," && iKomma == 0)) {
					sTemp.append(sNewVal.substring(i, 1));
					if (sNewVal.substring(i, 1) == "," && iKomma == 0) {
						iKomma++;
					}
				}
			}
			sNewVal = sTemp.toString();
		} else {
			sNewVal = sVal.replace("/[^\\d]/g", "");// laat alleen cijfers staan
			if (bOokNul && sVal == "0") {
				sNewVal = "";
			}
		}
		return sNewVal;
	}

	public boolean checkSofi(String sSofi) {

		sSofi = verwijderKarakters(sSofi, false, false);

		if (sSofi.length() >= 7) {

			if (!StringUtils.isNumeric(sSofi) || sSofi.indexOf(".") > -1) {

				System.out.println("Sofinummer mag alleen cijfers bevatten");

				return false;

			} else {

				if (CheckSofiControleGetal(sSofi)) {

					return true;

				} else {

					System.out.println("Sofinummer onjuist");

				}

			}

		}

		return false;

	}

	private boolean CheckSofiControleGetal(String strWaarde) {

		while (strWaarde.length() != 9) {

			strWaarde = "0" + strWaarde;

		}

		int iTemp = 0;

		int strControleGetal = Integer.parseInt(strWaarde.substring(8, 9));

		for (int i = 0; i <= 7; i++) {

			iTemp = iTemp + (Integer.parseInt(strWaarde.substring(i, i + 1)) * (9 - i));

		}

		int modResult = iTemp % 11;

		if (modResult == strControleGetal) {

			return true;

		} else {

			return false;

		}

	}

	private String maakSofiOp(String strWaarde) {

		if (strWaarde.length() == 9) {

			strWaarde = strWaarde.substring(0, 4) + "." + strWaarde.substring(4, 6) + "."

			+ strWaarde.substring(6);

		}

		if (strWaarde.length() == 8) {

			strWaarde = strWaarde.substring(0, 3) + "." + strWaarde.substring(3, 5) + "."

			+ strWaarde.substring(5);

		}

		if (strWaarde.length() == 7) {

			strWaarde = strWaarde.substring(0, 2) + "." + strWaarde.substring(2, 4) + "."

			+ strWaarde.substring(4);

		}

		return strWaarde;

	}

	/**
	 * Validate BSN according to
	 * http://nl.wikipedia.org/wiki/Burgerservicenummer
	 */

	public boolean isValidBSN(int candidate) {

		if (candidate <= 9999999 || candidate > 999999999) {

			return false;

		}

		int sum = -1 * candidate % 10;

		for (int multiplier = 2; candidate > 0; multiplier++) {

			int val = (candidate /= 10) % 10;

			sum += multiplier * val;

		}

		return sum != 0 && sum % 11 == 0;

	}

	public static void main(String[] args) {

		DutchTaxPaymentFeatureHelper converter = new DutchTaxPaymentFeatureHelper();

		String bk = converter.bereken("");

		converter.checkSofi("");

		System.out.println(converter.isValidBSN(0));

	}

}
