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
package org.techytax.report.helper;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class Invoice {

	private float unitsOfWork;
	private String consumerAddress;
	private String consumerName;
	private int vat;
	private String vatIdNr;
	private String chamberOfCommerceNumber;
	private String email;
	private String emailBcc;
	private String emailCc;
	private int invoiceNumber;
	private String invoiceDate;
	private int nofDays;
	private String expiryDate;
	
	private int year;
	private String month;
	private BigDecimal netAmount;

	private String activityDescription;
	private BigDecimal rate;
	private BigDecimal totalAmount;
	private BigDecimal vatAmount;
	private int discountPercentage;
	private BigDecimal discount;
	private BigDecimal netAmountAfterDiscount;

}
