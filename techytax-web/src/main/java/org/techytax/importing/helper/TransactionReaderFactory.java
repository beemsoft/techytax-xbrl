/**
 * Copyright 2015 Hans Beemsterboer
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
package org.techytax.importing.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionReaderFactory {

	@Autowired
	private IngTransactionReader ingTransactionReader;

	@Autowired
	private TravelChipCardTransactionReader travelChipCardTransactionReader;

	@Autowired
	private AbnAmroTransactionReader abnAmroTransactionReader;

	public TransactionReader getTransactionReader(String firstLine) throws IllegalAccessException {
		TransactionReader transactionReader = null;
		if (firstLine.startsWith("\"Datum\",\"Naam")) {
			transactionReader = ingTransactionReader;
		} else if (firstLine.startsWith("\"Datum\";\"Check")) {
			transactionReader = travelChipCardTransactionReader;
		} else {
			transactionReader = abnAmroTransactionReader;
		}
		return transactionReader;
	}

}
