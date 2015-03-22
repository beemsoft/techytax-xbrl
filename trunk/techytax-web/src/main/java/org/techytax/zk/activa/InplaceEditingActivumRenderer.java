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
package org.techytax.zk.activa;

import java.math.BigInteger;
import java.text.ParseException;

import org.techytax.domain.BookValue;
import org.techytax.domain.BookValueHistory;
import org.techytax.helper.AmountHelper;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class InplaceEditingActivumRenderer implements ListitemRenderer<BookValueHistory> {
	@SuppressWarnings("unchecked")
	public void render(Listitem listitem, BookValueHistory data, int index) {
		Listbox listbox = listitem.getListbox();
		if (index == 0) {
			if (listbox.getListhead() != null) {
				listbox.getListhead().setParent(null);
			}
			createListhead(data).setParent(listbox);
		}
		listitem.setValue(data);
		if (data.getBookValues().size() > 0) {
			String description = data.getBookValues().get(0).getDescription();
			addLabelCell(listitem, description);
			for (BookValue bookValue : data.getBookValues()) {
				if (bookValue.getSaldo() != null) {
					addIntboxCell(listitem, bookValue.getSaldo().intValue()).addEventListener(Events.ON_CHANGE,
							getSaldoChangedListener(listbox, bookValue, listitem));
				} else {
					addLabelCell(listitem, "");
				}
			}
		}
	}

	private Listhead createListhead(BookValueHistory data) {

		Listhead lh = new Listhead();

		Listheader header = createListheader(Labels.getLabel("balance.type"));
		header.setWidth("150px");
		header.setParent(lh);
		for (BookValue bookValue : data.getBookValues()) {
			createListheader(Integer.toString(bookValue.getYear())).setParent(lh);
		}
		return lh;
	}

	private Listheader createListheader(String label) {
		Listheader lhr = new Listheader(label);
		lhr.setWidth("80px");
		return lhr;
	}

	private Label addLabelCell(Listitem listitem, String value) {
		Listcell lc = new Listcell();
		Label lbx = new Label(value);
		lbx.setParent(lc);
		lc.setParent(listitem);
		return lbx;
	}

	private Intbox addIntboxCell(Listitem listitem, Integer value) {
		Listcell lc = new Listcell();
		Intbox ibx = new Intbox();
		if (value != null) {
			ibx.setValue(value);
		}
		ibx.setParent(lc);
		ibx.setFormat("###,##0");
		ibx.setWidth("70px");
		lc.setParent(listitem);
		return ibx;
	}

	@SuppressWarnings("rawtypes")
	private EventListener getSaldoChangedListener(final Listbox listbox, final BookValue oldBookValue, final Listitem listitem) {
		return new EventListener() {
			public void onEvent(Event event) throws ParseException {
				InputEvent ievent = (InputEvent) event;
				BigInteger amount = AmountHelper.parse(ievent.getValue());
				BookValue newBookValue = new BookValue(oldBookValue.getBalanceType(), oldBookValue.getYear(), amount);
				newBookValue.setId(oldBookValue.getId());
				Events.postEvent(ModelDataChangeEvent.getModelDataChangeEvent(listbox, newBookValue,
						listitem.getIndex()
						));
			}
		};
	}

}