package org.techytax.helper;

import org.junit.Test;
import org.techytax.domain.Cost;

public class DutchTaxCodeHelperTest {

	@Test
	public void convertTaxCode() {
		DutchTaxCodeHelper dutchTaxCodeHelper = new DutchTaxCodeHelper();
		Cost cost = new Cost();
		cost.setDescription("");
		System.out.println(dutchTaxCodeHelper.convertTaxCode(cost).getDescription());
	}

}
