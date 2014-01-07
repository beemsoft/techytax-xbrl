package org.techytax.domain;

import org.techytax.helper.Translator;
import org.zkoss.util.Locales;

public enum VatPeriodType {
	PER_QUARTER("vat.period.quarter"), PER_YEAR("vat.period.year");

	private String key;

	private VatPeriodType(String key) {
		this.key = key;
	}

	public String getKey() {
		return Translator.translateKey(key, Locales.getCurrent());
	}

	public static VatPeriodType getInstance(int type) {
		VatPeriodType vatPeriodType;
		switch (type) {
		case 0:
			vatPeriodType = PER_QUARTER;
			break;
		case 1:
			vatPeriodType = PER_YEAR;
			break;
		default:
			vatPeriodType = null;
			break;
		}
		return vatPeriodType;
	}
}
