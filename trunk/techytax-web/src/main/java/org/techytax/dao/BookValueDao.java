/**
 * Copyright 2014 Hans Beemsterboer
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
package org.techytax.dao;

import static org.techytax.domain.BalanceType.CAR;
import static org.techytax.domain.BalanceType.CURRENT_ASSETS;
import static org.techytax.domain.BalanceType.INVOICES_TO_BE_PAID;
import static org.techytax.domain.BalanceType.MACHINERY;
import static org.techytax.domain.BalanceType.NON_CURRENT_ASSETS;
import static org.techytax.domain.BalanceType.OFFICE;
import static org.techytax.domain.BalanceType.PENSION;
import static org.techytax.domain.BalanceType.STOCK;
import static org.techytax.domain.BalanceType.VAT_TO_BE_PAID;
import static org.techytax.jpa.dao.QueryParameter.with;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.jpa.dao.GenericDao;

@Component
public class BookValueDao extends GenericDao<BookValue> {

	public List<BookValue> getBookValuesHistory() {
		return findByNamedQuery(BookValue.HISTORY);
	}

	public List<BookValue> getBookValuesForYear(int year) {
		return findByNamedQuery(BookValue.FOR_YEAR, with("year", year).parameters());
	}

	public BookValue getBookValue(BalanceType balanceType, int year) {
		return findEntityByNamedQuery(BookValue.GET, with("year", year).and("balanceType", balanceType).parameters());
	}
	
	public List<BookValue> getPassivaList(int year) {
		return getBookValuesForBalanceTypes(year, Arrays.asList(NON_CURRENT_ASSETS, PENSION, VAT_TO_BE_PAID));
	}
	
	public List<BookValue> getActivaList(int year) {
		return getBookValuesForBalanceTypes(year, Arrays.asList(MACHINERY, CAR, CURRENT_ASSETS, STOCK, OFFICE, INVOICES_TO_BE_PAID));
	}

	private List<BookValue> getBookValuesForBalanceTypes(int year, List<BalanceType> balanceTypes) {
		return findByNamedQuery(BookValue.FOR_YEAR_AND_TYPES, with("year", year).and("balanceTypes", balanceTypes).parameters());
	}
	
	@Override
	public void persistEntity(BookValue entity) {
		throw new IllegalStateException("Bookyear closed!");
//		super.persistEntity(entity);
	}
	
	@Override
	public void merge(BookValue entity) {
		throw new IllegalStateException("Bookyear closed!");
//		super.merge(entity);
	}

}
