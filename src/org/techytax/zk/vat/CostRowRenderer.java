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
package org.techytax.zk.vat;

import java.util.HashMap;
import java.util.Map;

import org.techytax.domain.Cost;
import org.techytax.helper.AmountHelper;
import org.techytax.util.DateHelper;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Window;

public class CostRowRenderer implements RowRenderer<Cost> {

	@Override
	public void render(Row row, final Cost cost, int index) throws Exception {
		row.appendChild(new Label(cost.getDescription()));
		row.appendChild(new Label(DateHelper.getDate(cost.getDate())));
		row.appendChild(new Label(AmountHelper.formatDecimal(cost.getAmount())));
		row.appendChild(new Label(AmountHelper.formatDecimal(cost.getVat())));
		row.appendChild(new Label(cost.getKostenSoortOmschrijving().toString()));
		row.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			public void onEvent(Event event) throws Exception {
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("cost", cost);
				String template = "edit-cost.zul";
				Window window = (Window) Executions.createComponents(template, null, arguments);
				window.doModal();
			}
		});

	}

}
