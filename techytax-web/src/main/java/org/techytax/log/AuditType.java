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
package org.techytax.log;

public enum AuditType {
	UPLOAD_TRANSACTIONS, IMPORT_TRANSACTIONS, LOGOFF, LOGON, LOGON_FOR_SERVICE, MATCH_TRANSACTION, SEND_INVOICE, SEND_AUDIT_FILE, SEND_VAT_DECLARATION, ENTER_COST, UPDATE_COST, DELETE_COST, DELETE_ALL_COSTS, SPLIT_COST, DEPRECIATE_COST, UPDATE_ACTIVUM, VAT_OVERVIEW, TAX_OVERVIEW, CHECK_VAT, CHECK_ACCOUNT, INVOICE_OVERVIEW, ENTER_BOOKVALUE, UPDATE_BOOKVALUE, DELETE_BOOKVALUE;
}
