/**
 * Copyright 2013 Hans Beemsterboer
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
package org.techytax.zk.listener;

import java.util.List;

import org.techytax.jpa.entities.EntityManagerHelper;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.util.ExecutionCleanup;
import org.zkoss.zk.ui.util.ExecutionInit;

public class OpenSessionInViewListener implements ExecutionInit, ExecutionCleanup {
	private static final Log log = Log.lookup(OpenSessionInViewListener.class);

	public void init(Execution exec, Execution parent) {
		if (parent == null) {
			log.debug("Starting a database transaction: " + exec);
			EntityManagerHelper.beginTransaction();
		}
	}

	@SuppressWarnings("rawtypes")
	public void cleanup(Execution exec, Execution parent, List errs) {
		if (parent == null) {
			if (errs == null || errs.isEmpty()) {
				log.debug("Committing the database transaction: " + exec);
				EntityManagerHelper.commit();
			} else {
				final Throwable ex = (Throwable) errs.get(0);
				rollback(exec, ex);
			}
		}
	}

	private void rollback(Execution exec, Throwable ex) {
		try {
			if (EntityManagerHelper.isOpen()) {
				log.debug("Trying to rollback database transaction after exception:" + ex);
				EntityManagerHelper.rollback();
			}
		} catch (Throwable rbEx) {
			log.error("Could not rollback transaction after exception! Original Exception:\n" + ex, rbEx);
		}
	}
}
