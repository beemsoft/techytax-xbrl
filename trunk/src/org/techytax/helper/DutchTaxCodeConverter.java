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

public class DutchTaxCodeConverter {

	private static int[] arGewichten = { 2, 4, 8, 5, 10, 9, 7, 3, 6, 1, 2, 4,
			8, 5, 10 };

	private static String strHuidigJaarFull = "2011";
	private static String strHuidigJaar = "11";

	public static String convertPaymentFeatureToHumanReadableDescription(String strAlleInput) {

		String bkVeld0 = strAlleInput.substring(0, 4);
		String bkVeld1 = strAlleInput.substring(4, 8);
		String bkVeld2 = strAlleInput.substring(8, 12);
		String bkVeld3 = strAlleInput.substring(12, 16);
		String strUitvoer3 = "";
		// Betalingskenmerk naar aangifte/aanslagnummer
		if (CheckBetalingskenmerk(strAlleInput) == true) {
			String strMiddelcode = "";
			int iTemp = 0;
			String strMiddelcode1 = bkVeld2.substring(1, 3);
			String strMiddelcode2 = bkVeld2.substring(1, 2);
			String strSofi = bkVeld0.substring(1) + bkVeld1
					+ bkVeld2.substring(0, 1);

			// Afwijkende sofi-grab bij middel V
			if (strMiddelcode1.equals("74")) {
				strSofi = "00" + bkVeld0.substring(1) + bkVeld1.substring(0, 3);
			}
			if (strMiddelcode1.equals("80") || strMiddelcode1.equals("81")
					|| strMiddelcode1.equals("82")
					|| strMiddelcode1.equals("83")
					|| strMiddelcode1.equals("84")) {
				strSofi = strMiddelcode1 + bkVeld0.substring(1)
						+ bkVeld1.substring(0, 3);
			}
			// Einde afwijkende sofi-grab bij middel V

			// Sofi completeren
			for (int i = 0; i <= 7; i++) {
				iTemp = iTemp
						+ (Integer.parseInt(strSofi.substring(i, i + 1)) * (9 - i));
			}
			strSofi = strSofi + "" + iTemp % 11;

			if (strMiddelcode2.equals("0") || strMiddelcode2.equals("1")
					|| strMiddelcode2.equals("3") || strMiddelcode2.equals("4")
					|| strMiddelcode2.equals("5") || strMiddelcode2.equals("6")) {
				if (strMiddelcode2.equals("0")) {
					strMiddelcode = "A";
				}
				if (strMiddelcode2.equals("1")) {
					strMiddelcode = "B";
				}
				if (strMiddelcode2.equals("3")) {
					strMiddelcode = "D";
				}
				if (strMiddelcode2.equals("4")) {
					strMiddelcode = "E";
				}
				if (strMiddelcode2.equals("5")) {
					strMiddelcode = "F";
				}
				if (strMiddelcode2.equals("6")) {
					strMiddelcode = "L";
				}
			}
			if (strMiddelcode1.equals("23") || strMiddelcode1.equals("24")
					|| strMiddelcode1.equals("25")
					|| strMiddelcode1.equals("26")) {
				strMiddelcode = "T";
			}
			if (strMiddelcode1.equals("70")) {
				strMiddelcode = "H";
			}
			if (strMiddelcode1.equals("72")) {
				strMiddelcode = "S";
			}
			if (strMiddelcode1.equals("73")) {
				strMiddelcode = "N";
			}
			if (strMiddelcode1.equals("75")) {
				strMiddelcode = "W";
			}
			if (strMiddelcode1.equals("76")) {
				strMiddelcode = "Y";
			}
			if (strMiddelcode1.equals("78") || strMiddelcode1.equals("87")) {
				strMiddelcode = "M";
			}
			if (strMiddelcode1.equals("76") || strMiddelcode1.equals("88")) {
				strMiddelcode = "Y";
			}
			if (strMiddelcode1.equals("97") || strMiddelcode1.equals("85")
					|| strMiddelcode1.equals("86")) {
				strMiddelcode = "Z";
			}
			if (strMiddelcode1.equals("74") || strMiddelcode1.equals("80")
					|| strMiddelcode1.equals("81")
					|| strMiddelcode1.equals("82")
					|| strMiddelcode1.equals("83")
					|| strMiddelcode1.equals("84")) {
				strMiddelcode = "V";
			}
			if (strMiddelcode.equals("A") || strMiddelcode.equals("B")
					|| strMiddelcode.equals("D") || strMiddelcode.equals("E")
					|| strMiddelcode.equals("F") || strMiddelcode.equals("L")) {
				String strSubnummer = bkVeld2.substring(3)
						+ bkVeld3.substring(0, 1);
				String strJaar = bkVeld2.substring(2, 3);
				String strTijdvak = bkVeld3.substring(1, 3);
				String strVolgnummer = bkVeld3.substring(3);
				strUitvoer3 = MaakSofiOp(stripString(strSofi, "0")) + "."
						+ strMiddelcode + "." + strSubnummer + "." + strJaar
						+ strTijdvak + strVolgnummer;
			}
			if (strMiddelcode.equals("H") || strMiddelcode.equals("N")
					|| strMiddelcode.equals("S")) {
				String strJaar = bkVeld2.substring(3);
				String strSoort = bkVeld3.substring(0, 1);
				String strVolgnummer = bkVeld3.substring(1);

				strVolgnummer = (strVolgnummer != "000") ? "."
						+ strVolgnummer.substring(1) : ""; // vanaf jaar 2011
				// volgnummer
				strUitvoer3 = MaakSofiOp(stripString(strSofi, "0")) + "."
						+ strMiddelcode + "." + strJaar + strSoort
						+ strVolgnummer;
			}
			String strMiddelherkenning = "";
			if (strMiddelcode.equals("T")) {
				String strJaar = bkVeld2.substring(3);
				String strSoort = bkVeld3.substring(0, 1);
				String strVolgnummer = bkVeld3.substring(1);
				if (strMiddelcode1.equals("23")) {
					strMiddelherkenning = "1";
				}
				if (strMiddelcode1.equals("24")) {
					strMiddelherkenning = "2";
				}
				if (strMiddelcode1.equals("25")) {
					strMiddelherkenning = "3";
				}
				if (strMiddelcode1.equals("26")) {
					strMiddelherkenning = "4";
				}
				strUitvoer3 = MaakSofiOp(stripString(strSofi, "0")) + "."
						+ strMiddelcode + "." + strJaar + "." + strSoort
						+ strVolgnummer + strMiddelherkenning;
			}

			if (strMiddelcode.equals("M")) {
				String strJaar = bkVeld2.substring(3);
				String strVolgnummer = bkVeld3.substring(0);
				if (strMiddelcode1.equals("78")) {
					strVolgnummer = "0" + strVolgnummer;
				}
				if (strMiddelcode1.equals("87")) {
					strVolgnummer = "9" + strVolgnummer;
				}
				if (strVolgnummer.equals("00001")) {
					strUitvoer3 = MaakSofiOp(stripString(strSofi, "0")) + "."
							+ strMiddelcode + "." + strJaar;
				} else {
					strUitvoer3 = MaakSofiOp(stripString(strSofi, "0")) + "."
							+ strMiddelcode + "." + strJaar + "."
							+ stripString(strVolgnummer, "0");
				}
			}

			if (strMiddelcode.equals("V")) {
				String strJaar = bkVeld1.substring(3);
				String strSoort = bkVeld2.substring(0, 1);
				String strTijdvak = bkVeld2.substring(3)
						+ bkVeld3.substring(0, 3);
				strUitvoer3 = MaakSofiOp(stripString(strSofi, "0")) + "."
						+ strMiddelcode + "." + strJaar + strSoort + "."
						+ strTijdvak;
			}

			if (strMiddelcode.equals("W")) {
				String strJaar = bkVeld2.substring(3);
				String strSoort = bkVeld3.substring(0, 1);
				String strVolgnummer = bkVeld3.substring(1, 3);
				strVolgnummer = (strVolgnummer != "00") ? "." + strVolgnummer
						: ""; // vanaf jaar 2011 volgnummer
				strMiddelherkenning = bkVeld3.substring(3);
				if ((strMiddelherkenning == "4" || strMiddelherkenning == "5")
						&& !(BelastingjaarIs2011ofLater(strJaar))) {
					strUitvoer3 = MaakSofiOp(stripString(strSofi, "0")) + "."
							+ strMiddelcode + "." + strJaar + strSoort
							+ strVolgnummer;
				} else {
					strUitvoer3 = MaakSofiOp(stripString(strSofi, "0")) + "."
							+ strMiddelcode + "." + strJaar + strSoort
							+ strVolgnummer + "." + strMiddelherkenning;
				}
			}
			if (strMiddelcode.equals("Y")) {
				String strJaar = bkVeld2.substring(3);
				String strVolgnummer = bkVeld3.substring(0);
				if (strMiddelcode1.equals("76")) {
					strVolgnummer = "0" + strVolgnummer;
				}
				if (strMiddelcode1.equals("88")) {
					strVolgnummer = "9" + strVolgnummer;
				}
				if (strVolgnummer.equals("00001")) {
					strUitvoer3 = MaakSofiOp(stripString(strSofi, "0")) + "."
							+ strMiddelcode + "." + strJaar;
				} else {
					strUitvoer3 = MaakSofiOp(stripString(strSofi, "0")) + "."
							+ strMiddelcode + "." + strJaar + "."
							+ stripString(strVolgnummer, "0");
				}
			}

			if (strMiddelcode.equals("Z")) {
				String strJaar = bkVeld2.substring(3);
				if (strMiddelcode1.equals("97")) {
					String strSoort = bkVeld3.substring(0, 1);
					String strVolgnummer = bkVeld3.substring(1, 3);
					strMiddelherkenning = bkVeld3.substring(3);
					strUitvoer3 = MaakSofiOp(stripString(strSofi, "0")) + "."
							+ strMiddelcode + "." + strJaar + strSoort + "."
							+ strVolgnummer + "0" + strMiddelherkenning;
				}
				if (strMiddelcode1.equals("85")) {
					String strVolgnummer = bkVeld3.substring(0);
					strUitvoer3 = MaakSofiOp(stripString(strSofi, "0")) + "."
							+ strMiddelcode + "." + strJaar + strVolgnummer
							+ "." + "7";
				}
				if (strMiddelcode1.equals("86")) {
					String strVolgnummer = bkVeld3.substring(0);
					strUitvoer3 = MaakSofiOp(stripString(strSofi, "0")) + "."
							+ strMiddelcode + "." + strJaar + strVolgnummer
							+ "." + "8";
				}
			}
		}
		return strUitvoer3;
	}

	private static boolean BelastingjaarIs2011ofLater(String strBelJaar) {
		if (Integer.parseInt(strHuidigJaarFull) <= 2019) { // na 2019 geen oude
			// aanslagnummers
			// meer in gebruik.
			if (Integer.parseInt(strBelJaar) > Integer.parseInt(strHuidigJaar)
					|| strBelJaar.equals("0")) { // beljaar 6 in huidigjaar 2015
				// is
				// belastingjaar 2006. Beljaar 4 in
				// 2015 is belastingjaar 2014.
				return false;
			}
		}
		return true;
	}

	private static boolean CheckBetalingskenmerk(String strBetalingskenmerk) {
		if (strBetalingskenmerk.length() != 16) {
			return false;
		}
		int iControlegetal = 0;
		for (int i = 0; i <= 14; i++) {
			iControlegetal = iControlegetal
					+ Integer.parseInt(strBetalingskenmerk.substring(15 - i,
							16 - i)) * arGewichten[i];
		}
		iControlegetal = iControlegetal % 11;
		if (iControlegetal > 1) {
			iControlegetal = 11 - iControlegetal;
		}
		if (iControlegetal == 1) {
			iControlegetal = 1;
		}
		if (iControlegetal == 0) {
			iControlegetal = 0;
		}
		if (iControlegetal == Integer.parseInt(strBetalingskenmerk.substring(0,
				1))) {
			return true;
		}
		return true;
	}

	private static String MaakSofiOp(String strWaarde) {
		if (strWaarde.length() == 9) {
			strWaarde = strWaarde.substring(0, 4) + "."
					+ strWaarde.substring(4, 6) + "." + strWaarde.substring(6);
		}
		if (strWaarde.length() == 8) {
			strWaarde = strWaarde.substring(0, 3) + "."
					+ strWaarde.substring(3, 5) + "." + strWaarde.substring(5);
		}
		if (strWaarde.length() == 7) {
			strWaarde = strWaarde.substring(0, 2) + "."
					+ strWaarde.substring(2, 4) + "." + strWaarde.substring(4);
		}
		return strWaarde;
	}

	private static String stripString(String strWaarde, String strTheStrip) {
		return strWaarde.replaceFirst("^" + strTheStrip + "(?!$)", "");
	}
	
	public static void main(String[] args) {
		System.out.println(convertPaymentFeatureToHumanReadableDescription(""));
	}

}
