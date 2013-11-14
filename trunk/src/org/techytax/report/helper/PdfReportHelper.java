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
package org.techytax.report.helper;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.techytax.dao.BoekDao;
import org.techytax.digipoort.XbrlHelper;
import org.techytax.domain.Balans;
import org.techytax.domain.Cost;
import org.techytax.domain.Periode;
import org.techytax.domain.User;
import org.techytax.domain.VatDeclarationData;
import org.techytax.helper.AmountHelper;
import org.techytax.helper.BalanceCalculator;
import org.techytax.report.domain.VatJournal;
import org.techytax.report.domain.VatReportData;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.util.resource.Labels;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class PdfReportHelper {

	private static Font font = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);
	private static Font totalAmountFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
	private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
	private static int id = 1;

	private static void addSpace(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setFixedHeight(15f);
		cell.setBorder(PdfPCell.NO_BORDER);
		table.addCell(cell);
	}

	public static byte[] createVatReport() throws Exception {
		User user = UserCredentialManager.getUser();
		if (user != null) {
			Periode period = DateHelper.getLatestVatPeriod();
			BoekDao boekDao = new BoekDao();
			List<Cost> vatCosts = boekDao.getKostLijst(DateHelper.getDate(period.getBeginDatum()), DateHelper.getDate(period.getEindDatum()),
					"btwBalans", Long.toString(user.getId()));
			VatReportData vatReportData = VatReportHelper.createReportData(vatCosts);
			Balans balans = BalanceCalculator.calculateBtwBalance(vatCosts, false);
			VatDeclarationData vatDeclarationData = new VatDeclarationData();
			vatDeclarationData.setFiscalNumber(user.getFiscalNumber());
			vatDeclarationData.setName(user.getFullName());
			vatDeclarationData.setPhoneNumber(user.getPhoneNumber());
			vatDeclarationData.setStartDate(period.getBeginDatum());
			vatDeclarationData.setEndDate(period.getEindDatum());
			XbrlHelper.addBalanceData(vatDeclarationData, balans);
			vatReportData.setVatDeclarationData(vatDeclarationData);
			PdfReportHelper pdfHelper = new PdfReportHelper();
			return pdfHelper.createVatReportBytes(vatReportData);
		} else {
			return null;
		}
	}

	public byte[] createVatReportBytes(VatReportData vatReportData) {

		Document document = new Document();
		document.setMargins(0, 0, 40, 40);
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, byteOutputStream);
			document.open();
			PdfPTable subTable = new PdfPTable(1);
			PdfPCell cell = new PdfPCell(new Paragraph(""));
			subTable.addCell(cell);
			PdfPTable table = new PdfPTable(1);
			addSpace(table);
			Paragraph chunk = new Paragraph("btw declaratie aangever gegevens", headerFont);
			cell = new PdfPCell(chunk);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			subTable = new PdfPTable(2);
			addVatDeclarationData(subTable, vatReportData.getVatDeclarationData());
			cell = new PdfPCell(subTable);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			addSpace(table);
			addVatData(table, vatReportData.getVatDeclarationData());
			addSpace(table);
			addTotalsIn(vatReportData, table);
			addSpace(table);
			addTotalsOut(vatReportData, table);
			addSpace(table);
			addSpace(table);
			addJournalsIn(vatReportData, table);
			addJournalsOut(vatReportData, table);
			document.add(table);
			LineSeparator horizontalLine = new LineSeparator();
			BaseColor lineColor = new BaseColor(141, 141, 141);
			horizontalLine.setLineWidth(1f);
			horizontalLine.setLineColor(lineColor);
			document.add(horizontalLine);
		} catch (Exception de) {
			de.printStackTrace();
		}
		document.close();
		return byteOutputStream.toByteArray();
	}

	private void addJournalsOut(VatReportData vatReportData, PdfPTable table) throws DocumentException {
		PdfPCell cell;
		Paragraph chunk;
		PdfPTable subTable;
		PdfPCell tableCell;
		Paragraph chunkMoney;
		for (VatJournal vatJournal : vatReportData.getVatJournalsOut().getVatJournals()) {
			subTable = new PdfPTable(4);
			chunk = new Paragraph("btw uit post: " + Labels.getLabel(vatJournal.getCostType()), headerFont);
			cell = new PdfPCell(chunk);
			cell.setColspan(4);
			subTable.addCell(cell);

			subTable.setWidths(new int[] { 1, 1, 8, 1 });
			createTableHeader(headerFont, subTable);
			for (Cost cost : vatJournal.getJournalCosts()) {
				createTableRow(cost, font, subTable);
			}

			cell = new PdfPCell(new Paragraph("Totaal", headerFont));
			subTable.addCell(cell);
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			chunkMoney = new Paragraph(AmountHelper.formatDecimal(vatJournal.getTotalAmount()), totalAmountFont);
			cell = new PdfPCell(chunkMoney);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorder(PdfPCell.NO_BORDER);
			cell.setColspan(2);
			subTable.addCell(cell);

			tableCell = new PdfPCell(subTable);
			table.addCell(tableCell);

			addSpace(table);
		}
	}

	private void addJournalsIn(VatReportData vatReportData, PdfPTable table) throws DocumentException {
		PdfPCell cell;
		Paragraph chunk;
		PdfPTable subTable;
		PdfPCell tableCell;
		Paragraph chunkMoney;
		for (VatJournal vatJournal : vatReportData.getVatJournalsIn().getVatJournals()) {
			subTable = new PdfPTable(4);
			chunk = new Paragraph("btw in post: " + Labels.getLabel(vatJournal.getCostType()), headerFont);
			cell = new PdfPCell(chunk);
			cell.setColspan(4);
			subTable.addCell(cell);

			subTable.setWidths(new int[] { 1, 1, 8, 1 });
			createTableHeader(headerFont, subTable);
			for (Cost cost : vatJournal.getJournalCosts()) {
				createTableRow(cost, font, subTable);
			}

			cell = new PdfPCell(new Paragraph("Totaal", headerFont));
			subTable.addCell(cell);
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			chunkMoney = new Paragraph(AmountHelper.formatDecimal(vatJournal.getTotalAmount()), totalAmountFont);
			cell = new PdfPCell(chunkMoney);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorder(PdfPCell.NO_BORDER);
			cell.setColspan(2);
			subTable.addCell(cell);

			tableCell = new PdfPCell(subTable);
			table.addCell(tableCell);

			addSpace(table);

		}
	}

	private void addVatData(PdfPTable table, VatDeclarationData vatDeclarationData) {
		PdfPTable subTable;
		PdfPCell cell;
		Paragraph chunk;
		chunk = new Paragraph("btw declaratie", headerFont);
		subTable = new PdfPTable(2);
		addCell(subTable, "btw in");
		addAmountCell(subTable, AmountHelper.formatDecimal(vatDeclarationData.getValueAddedTaxSuppliesServicesGeneralTariff()));
		addCell(subTable, "btw uit");
		addAmountCell(subTable, AmountHelper.formatDecimal(vatDeclarationData.getValueAddedTaxOnInput()));
		addCell(subTable, "btw saldo");
		addAmountCell(subTable, AmountHelper.formatDecimal(vatDeclarationData.getValueAddedTaxOwed()));

		cell = new PdfPCell(chunk);
		cell.setBorder(PdfPCell.NO_BORDER);
		table.addCell(cell);
		cell = new PdfPCell(subTable);
		cell.setBorder(PdfPCell.NO_BORDER);
		table.addCell(cell);
	}

	private void addVatDeclarationData(PdfPTable subTable, VatDeclarationData vatDeclarationData) {
		addCell(subTable, "Naam");
		addCell(subTable, vatDeclarationData.getName());
		addCell(subTable, "Fiscaal nummer");
		addCell(subTable, vatDeclarationData.getFiscalNumber());
		addCell(subTable, "Telefoonnummer");
		addCell(subTable, vatDeclarationData.getPhoneNumber());
		addCell(subTable, "Begindatum");
		addCell(subTable, DateHelper.getDate(vatDeclarationData.getStartDate()));
		addCell(subTable, "Einddatum");
		addCell(subTable, DateHelper.getDate(vatDeclarationData.getEndDate()));
	}

	private void addCell(PdfPTable subTable, String value) {
		PdfPCell cell;
		cell = new PdfPCell(new Paragraph(value));
		cell.setBorder(PdfPCell.NO_BORDER);
		subTable.addCell(cell);
	}

	private void addAmountCell(PdfPTable subTable, String value) {
		PdfPCell cell;
		cell = new PdfPCell(new Paragraph(value));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		subTable.addCell(cell);
	}

	private void addTotalsIn(VatReportData vatReportData, PdfPTable table) throws DocumentException {
		PdfPCell cell;

		PdfPTable subTable = new PdfPTable(2);
		subTable.setWidths(new int[] { 8, 2 });

		Paragraph chunk = new Paragraph("btw in overzicht", headerFont);
		cell = new PdfPCell(chunk);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setColspan(2);
		subTable.addCell(cell);
		Paragraph headerChunk = new Paragraph("Omschrijving", headerFont);
		cell = new PdfPCell(headerChunk);
		subTable.addCell(cell);
		headerChunk = new Paragraph("Subtotaal", headerFont);
		cell = new PdfPCell(headerChunk);
		subTable.addCell(cell);
		PdfPCell tableCell = new PdfPCell(subTable);
		for (VatJournal vatJournal : vatReportData.getVatJournalsIn().getVatJournals()) {
			cell = new PdfPCell(new Paragraph(Labels.getLabel(vatJournal.getCostType()), font));
			subTable.addCell(cell);
			Paragraph chunkMoney = new Paragraph(AmountHelper.formatDecimal(vatJournal.getTotalAmount()), font);
			cell = new PdfPCell(chunkMoney);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			subTable.addCell(cell);
		}

		cell = new PdfPCell(new Paragraph("Totaal in", headerFont));
		subTable.addCell(cell);
		Paragraph chunkMoney = new Paragraph(AmountHelper.formatDecimal(vatReportData.getVatDeclarationData()
				.getValueAddedTaxSuppliesServicesGeneralTariff()), totalAmountFont);
		cell = new PdfPCell(chunkMoney);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		cell.setBorder(PdfPCell.NO_BORDER);
		subTable.addCell(cell);
		tableCell = new PdfPCell(subTable);
		table.addCell(tableCell);
	}

	private void addTotalsOut(VatReportData vatReportData, PdfPTable table) throws DocumentException {
		PdfPCell cell;
		Paragraph chunk;

		PdfPTable subTable = new PdfPTable(2);
		subTable.setWidths(new int[] { 8, 2 });
		chunk = new Paragraph("btw uit overzicht", headerFont);
		cell = new PdfPCell(chunk);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setColspan(2);
		subTable.addCell(cell);
		Paragraph headerChunk = new Paragraph("Omschrijving", headerFont);
		cell = new PdfPCell(headerChunk);
		subTable.addCell(cell);
		headerChunk = new Paragraph("Subtotaal", headerFont);
		cell = new PdfPCell(headerChunk);
		subTable.addCell(cell);
		PdfPCell tableCell = new PdfPCell(subTable);
		for (VatJournal vatJournal : vatReportData.getVatJournalsOut().getVatJournals()) {
			cell = new PdfPCell(new Paragraph(Labels.getLabel(vatJournal.getCostType()), font));
			subTable.addCell(cell);
			Paragraph chunkMoney = new Paragraph(AmountHelper.formatDecimal(vatJournal.getTotalAmount()), font);
			cell = new PdfPCell(chunkMoney);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			subTable.addCell(cell);
		}

		cell = new PdfPCell(new Paragraph("Totaal uit", headerFont));
		subTable.addCell(cell);
		Paragraph chunkMoney = new Paragraph(AmountHelper.formatDecimal(vatReportData.getVatDeclarationData().getValueAddedTaxOnInput()),
				totalAmountFont);
		cell = new PdfPCell(chunkMoney);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		cell.setBorder(PdfPCell.NO_BORDER);
		subTable.addCell(cell);
		tableCell = new PdfPCell(subTable);
		table.addCell(tableCell);
	}

	private static void createTableHeader(Font headerFont, PdfPTable subTable) {
		Paragraph headerChunk = new Paragraph("Id", headerFont);
		PdfPCell cell = new PdfPCell(headerChunk);
		subTable.addCell(cell);
		headerChunk = new Paragraph("Datum", headerFont);
		cell = new PdfPCell(headerChunk);
		subTable.addCell(cell);
		headerChunk = new Paragraph("Omschrijving", headerFont);
		cell = new PdfPCell(headerChunk);
		subTable.addCell(cell);

		headerChunk = new Paragraph("btw", headerFont);
		cell = new PdfPCell(headerChunk);
		subTable.addCell(cell);
		subTable.setHeaderRows(2);
	}

	private static void createTableRow(Cost cost, Font font, PdfPTable subTable) {
		PdfPCell cell = new PdfPCell(new Paragraph(Long.toString(id++), font));
		subTable.addCell(cell);
		cell = new PdfPCell(new Paragraph(DateHelper.getDate(cost.getDate()), font));
		subTable.addCell(cell);
		String description = getEditedDescription(cost);
		cell = new PdfPCell(new Paragraph(description, font));
		subTable.addCell(cell);
		cell = new PdfPCell(new Paragraph(AmountHelper.formatDecimal(cost.getVat()), font));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		subTable.addCell(cell);
	}

	private static String getEditedDescription(Cost cost) {
		String description = cost.getDescription();
		description = description.replace("ING BANK NV", "");
		description = description.replace("PASTRANSACTIES", "");
		return description;
	}
}
