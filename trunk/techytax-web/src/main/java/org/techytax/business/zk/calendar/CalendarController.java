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
package org.techytax.business.zk.calendar;

import static org.techytax.domain.CostConstants.INVOICE_SENT;
import static org.techytax.log.AuditType.SEND_INVOICE;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.techytax.dao.BusinessCalendarDao;
import org.techytax.domain.BusinessCalendarEvent;
import org.techytax.domain.Cost;
import org.techytax.domain.Customer;
import org.techytax.domain.FiscalPeriod;
import org.techytax.domain.Project;
import org.techytax.domain.User;
import org.techytax.helper.AmountHelper;
import org.techytax.jpa.dao.CostDao;
import org.techytax.jpa.dao.ProjectDao;
import org.techytax.log.AuditLogger;
import org.techytax.mail.MailHelper;
import org.techytax.report.helper.Invoice;
import org.techytax.report.helper.PdfInvoiceHelper;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.api.CalendarEvent;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkmax.ui.select.annotation.Subscribe;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class CalendarController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Window win;

	@Wire
	private Window invoiceWindow;

	@Wire
	private Iframe invoiceFrame;

	@Wire
	private Calendars calendars;
	@Wire
	private Textbox filter;

	@Wire
	private Textbox discount;

	private BusinessCalendarModel calendarModel;

	// the in editing calendar ui event
	private CalendarsEvent calendarsEvent = null;

	@WireVariable
	private ProjectDao projectDao;

	@WireVariable
	private BusinessCalendarDao businessCalendarDao;

	private CostDao costDao;

	private ListModel<Project> projectsModel;

	@Wire
	private Listbox projectListbox;

	private Project selectedProject;

	@Wire
	private Button invoiceButton;

	@Wire
	private Button sendInvoiceButton;

	private Invoice invoice;
	private byte[] invoiceBuf;

	@WireVariable
	private AuditLogger auditLogger;

	@WireVariable
	private CalendarService calendarService;

	private User getUser() {
		return UserCredentialManager.getUser();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		projectDao = (ProjectDao) SpringUtil.getBean("projectDao");
		businessCalendarDao = (BusinessCalendarDao) SpringUtil.getBean("businessCalendarDao");
		auditLogger = (AuditLogger) SpringUtil.getBean("auditLogger");
		costDao = (CostDao) SpringUtil.getBean("costDao");
		calendarService = (CalendarService) SpringUtil.getBean("calendarService");

		final User user = getUser();
		if (user != null) {
			List<BusinessCalendarEvent> calendarEvents = calendarService.getEvents();
			calendarModel = new BusinessCalendarModel(calendarEvents);
			calendars.setModel(this.calendarModel);
			invoiceButton.setDisabled(true);
			discount.setDisabled(true);

			sendInvoiceButton = (Button) Path.getComponent("/win/invoiceWindow/sendInvoiceButton");
			sendInvoiceButton.addEventListener("onClick", new EventListener() {
				public void onEvent(Event event) throws Exception {
					sendInvoice();
				}

				private void sendInvoice() throws Exception {
					MailHelper.sendInvoice(invoice, invoiceBuf, user);
					registerInvoice();
					auditLogger.log(SEND_INVOICE, user);
					alert(Labels.getLabel("invoice.sent"));
				}

				private void registerInvoice() throws Exception {
					Cost cost = new Cost();
					cost.setUser(user);
					cost.setDescription(Labels.getLabel("invoice") + " " + invoice.getInvoiceNumber());
					cost.setAmount(invoice.getNetAmount());
					cost.setVat(invoice.getVatAmount());
					cost.setDate(new Date());
					cost.setCostType(INVOICE_SENT);
					costDao.persistEntity(cost);
				}
			});
		}
	}

	// control the calendar position
	@Listen("onClick = #today")
	public void gotoToday() {
		TimeZone timeZone = calendars.getDefaultTimeZone();
		calendars.setCurrentDate(Calendar.getInstance(timeZone).getTime());
	}

	@Listen("onClick = #next")
	public void gotoNext() {
		calendars.nextPage();
	}

	@Listen("onClick = #prev")
	public void gotoPrev() {
		calendars.previousPage();
	}

	// control page display
	@Listen("onClick = #pageDay")
	public void changeToDay() {
		calendars.setMold("default");
		calendars.setDays(1);
	}

	@Listen("onClick = #pageWeek")
	public void changeToWeek() {
		calendars.setMold("default");
		calendars.setDays(7);
	}

	@Listen("onClick = #pageMonth")
	public void changeToMonth() {
		calendars.setMold("month");
		if (selectedProject != null) {
			invoiceButton.setDisabled(false);
			discount.setDisabled(false);
		}
	}

	// control the filter
	@Listen("onClick = #applyFilter")
	public void applyFilter() {
		calendarModel.setFilterText(filter.getValue());
		calendars.setModel(calendarModel);
	}

	@Listen("onClick = #resetFilter")
	public void resetFilter() {
		filter.setText("");
		calendarModel.setFilterText("");
		calendarModel.setFilterProject(null);
		selectedProject = null;
		projectListbox.setSelectedItem(null);
		calendars.setModel(calendarModel);
		invoiceButton.setDisabled(true);
		discount.setDisabled(true);
	}

	// listen to the calendar-create and edit of a event data
	@Listen("onEventCreate = #calendars; onEventEdit = #calendars")
	public void createEvent(CalendarsEvent event) {
		calendarsEvent = event;

		// to display a shadow when editing
		calendarsEvent.stopClearGhost();

		BusinessCalendarEvent data = (BusinessCalendarEvent) event.getCalendarEvent();

		if (data == null) {
			data = new BusinessCalendarEvent();
			data.setHeaderColor("#3366ff");
			data.setContentColor("#6699ff");
			data.setBeginDate(event.getBeginDate());
			data.setEndDate(event.getEndDate());
		} else {
			data = (BusinessCalendarEvent) event.getCalendarEvent();
		}
		// notify the editor
		QueueUtil.lookupQueue().publish(

		new QueueMessage(QueueMessage.Type.EDIT, data));
	}

	// listen to the calendar-update of event data, usually send when user drag
	// the event data
	@Listen("onEventUpdate = #calendars")
	public void updateEvent(CalendarsEvent event) throws Exception {
		BusinessCalendarEvent data = (BusinessCalendarEvent) event.getCalendarEvent();
		data.setBeginDate(event.getBeginDate());
		data.setEndDate(event.getEndDate());
		calendarModel.update(data);
		businessCalendarDao.updateEvent(data);
	}

	// listen to queue message from other controller
	@SuppressWarnings("incomplete-switch")
	@Subscribe(value = QueueUtil.QUEUE_NAME)
	@Transactional
	public void handleQueueMessage(QueueMessage message) throws Exception {
		BusinessCalendarEvent calendarEvent = (BusinessCalendarEvent) message.getData();
		switch (message.getType()) {
		case DELETE:
			calendarModel.remove((BusinessCalendarEvent) message.getData());
			calendarService.delete(calendarEvent);
			// clear the shadow of the event after editingMaak een PDF factuur.
			calendarsEvent.clearGhost();
			calendarsEvent = null;
			break;
		case OK:
			if (calendarModel.indexOf(calendarEvent) >= 0) {
				calendarModel.update(calendarEvent);
				businessCalendarDao.updateEvent(calendarEvent);
			} else {
				calendarModel.add(calendarEvent);
				calendarService.add(calendarEvent);
			}
		case CANCEL:
			// clear the shadow of the event after editing
			calendarsEvent.clearGhost();
			calendarsEvent = null;
			break;
		}
	}

	public ListModel<Project> getProjectsModel() throws IllegalAccessException {
		final User user = getUser();
		if (user != null) {
			projectDao = (ProjectDao) SpringUtil.getBean("projectDao");
			projectsModel = new ListModelList<>(projectDao.findAll(user));
		}
		return projectsModel;
	}

	@Listen("onSelect = #projectListbox")
	public void changeProduct() {
		Set<Project> selectedProjects = ((ListModelList<Project>) projectsModel).getSelection();
		selectedProject = selectedProjects.iterator().next();
		calendarModel.setFilterProject(selectedProject);
		calendars.setModel(calendarModel);

		if (calendars.getMold().equals("month")) {
			invoiceButton.setDisabled(false);
			discount.setDisabled(false);
		}
	}

	@Listen("onClick = #invoiceButton")
	public void createInvoice() throws IOException {
		InputStream is = null;
		try {

			Calendar cal = GregorianCalendar.getInstance();
			Date calendarDate = calendars.getCurrentDate();
			cal.setTime(calendarDate);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date endDate = cal.getTime();

			cal.set(Calendar.DAY_OF_MONTH, 1);
			Date beginDate = cal.getTime();
			List<CalendarEvent> eventList = calendarModel.get(beginDate, endDate, null);

			invoice = new Invoice();
			invoice.setUnitsOfWork(countUnitsOfWork(eventList));
			invoice.setMonth(DateHelper.getMaand(endDate));
			int jaar = cal.get(Calendar.YEAR);
			String factuurNummerString = Integer.toString(jaar);
			invoice.setYear(jaar);
			int maand = cal.get(Calendar.MONTH) + 1;
			if (maand < 10) {
				factuurNummerString += "0";
			}
			factuurNummerString += Integer.toString(maand);

			List<Cost> sentAndPaidInvoicesInPeriod = costDao.getInvoicesSentAndPaid(new FiscalPeriod(beginDate, new Date()));
			List<Cost> invoices = new ArrayList<>();

			for (Cost cost : sentAndPaidInvoicesInPeriod) {
				if (cost.getCostType().equals(INVOICE_SENT)) {
					if (cost.getDescription().contains(factuurNummerString)) {
						invoices.add(cost);
					}
				}
			}

			int factuurAantal = invoices.size() + 1;
			String factuurAantalString = Integer.toString(factuurAantal);
			if (factuurAantal < 10) {
				factuurAantalString = "0" + factuurAantalString;
			}
			factuurNummerString += factuurAantalString;
			invoice.setInvoiceNumber(Integer.parseInt(factuurNummerString));

			Customer customer = selectedProject.getCustomer();
			sendInvoiceButton.setLabel(Labels.getLabel("send.invoice.to") + ": " + customer.getEmailInvoice());

			invoice.setConsumerAddress(customer.getFullAddress());
			invoice.setConsumerName(customer.getName());
			invoice.setVat(selectedProject.getVatType().getValueAsInteger(new Date()));
			invoice.setActivityDescription(selectedProject.getActivityDescription());
			invoice.setRate(selectedProject.getRate());

			BigDecimal netAmount = new BigDecimal(invoice.getUnitsOfWork() * invoice.getRate().floatValue());
			netAmount = AmountHelper.round(netAmount);

			invoice.setNetAmount(netAmount);
			invoice.setEmail(customer.getEmailInvoice());
			BigDecimal netAmountAfterDiscount = netAmount;
			if (StringUtils.isNotEmpty(discount.getValue())) {
				int discountPercentage = Integer.parseInt(discount.getValue());
				invoice.setDiscountPercentage(discountPercentage);
				BigDecimal discount = new BigDecimal(netAmount.doubleValue() * discountPercentage / 100.0d);
				invoice.setDiscount(discount);
				netAmountAfterDiscount = netAmount.subtract(discount);
				invoice.setNetAmountAfterDiscount(netAmountAfterDiscount);
			}
			BigDecimal btwBedrag = new BigDecimal(netAmountAfterDiscount.doubleValue() * invoice.getVat() / 100.0d);
			btwBedrag = AmountHelper.round(btwBedrag);
			BigDecimal totaalBedrag = netAmountAfterDiscount.add(btwBedrag);
			totaalBedrag = AmountHelper.round(totaalBedrag);
			invoice.setVatAmount(btwBedrag);
			invoice.setTotalAmount(totaalBedrag);

			invoice.setNetAmountAfterDiscount(netAmountAfterDiscount);
			PdfInvoiceHelper pdfInvoiceHelper = new PdfInvoiceHelper();
			invoiceBuf = pdfInvoiceHelper.createPdfInvoice(invoice, getUser());

			// prepare the AMedia for iframe
			final InputStream mediais = new ByteArrayInputStream(invoiceBuf);
			final AMedia amedia = new AMedia("Invoice.pdf", "pdf", "application/pdf", mediais);

			// set iframe content
			invoiceFrame = (Iframe) Path.getComponent("/win/invoiceWindow/invoiceFrame");
			invoiceFrame.setContent(amedia);
			invoiceWindow.doPopup();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	private float countUnitsOfWork(List<CalendarEvent> eventList) {
		Iterator<CalendarEvent> iterator = eventList.iterator();
		float total = 0.0f;
		while (iterator.hasNext()) {
			BusinessCalendarEvent event = (BusinessCalendarEvent) iterator.next();
			total += event.getUnitsOfWork();
		}
		return total;
	}

}