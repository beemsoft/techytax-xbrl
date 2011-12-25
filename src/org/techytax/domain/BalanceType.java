package org.techytax.domain;

public enum BalanceType {
	NONE, MACHINERY, CAR, CURRENT_ASSETS, NON_CURRENT_ASSETS, PENSION;
	
	public static BalanceType getInstance(int type) {
		BalanceType balanceType;
		switch (type) {
		case 1:
			balanceType = MACHINERY;
			break;
		case 2:
			balanceType = CAR;
			break;
		case 3:
			balanceType = CURRENT_ASSETS;
			break;
		case 4:
			balanceType = NON_CURRENT_ASSETS;
			break;
		case 5:
			balanceType = PENSION;
			break;	
		default:
			balanceType = NONE;
			break;
		}
		return balanceType;
	}	
}
