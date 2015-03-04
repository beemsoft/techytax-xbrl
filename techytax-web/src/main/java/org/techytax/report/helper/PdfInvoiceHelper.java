package org.techytax.report.helper;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import org.techytax.domain.User;
import org.techytax.util.DateHelper;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class PdfInvoiceHelper {

	private void addSpace(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setFixedHeight(15f);
		cell.setBorder(PdfPCell.NO_BORDER);
		table.addCell(cell);
	}

	public String formatDecimal(BigDecimal b) {

		Locale loc = new Locale("nl", "NL", "EURO");
		NumberFormat n = NumberFormat.getCurrencyInstance(loc);
		double doublePayment = b.doubleValue();
		String s = n.format(doublePayment);
		return s;

	}

	public byte[] createPdfInvoice(Invoice invoice, User user) {

		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, byteOutputStream);

			document.open();

			Font titleFont = new Font(Font.FontFamily.COURIER, 25, Font.BOLD);
			Font font = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);

			Font totalAmountFont = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
			Font headerFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);

			PdfPTable subTable = new PdfPTable(1);
			PdfPCell cell = new PdfPCell(new Paragraph(""));
			cell.setFixedHeight(50f);
			subTable.addCell(cell);

			subTable = new PdfPTable(2);
			cell = new PdfPCell(new Paragraph("Nummer"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(Integer.toString(invoice.getInvoiceNumber())));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Datum"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(invoice.getInvoiceDate()));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Maand"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(invoice.getMonth()));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Betalingstermijn"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(invoice.getNofDays() + " dagen"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Vervaldatum"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(invoice.getExpiryDate()));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);			

			PdfPTable table = new PdfPTable(1);
			table.setWidthPercentage(75);

			Paragraph title = new Paragraph(user.getCompanyName(), titleFont);
			cell = new PdfPCell(title);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			addSpace(table);
			addSpace(table);
			addSpace(table);

			Paragraph chunk = new Paragraph("Factuur", font);
			cell = new PdfPCell(chunk);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			cell = new PdfPCell(subTable);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);

			addSpace(table);

			chunk = new Paragraph("Leverancier", font);
			subTable = new PdfPTable(2);

			cell = new PdfPCell(new Paragraph("Bedrijfsnaam"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(user.getCompanyName()));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Naam"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(user.getFullName()));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);			
			cell = new PdfPCell(new Paragraph("Adres"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(user.getCompanyAddress()));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("btw-nr"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("NL " + user.getFiscalNumber()));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("KvK-nr"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(Long.toString(user.getChamberOfCommerceNumber())));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);			
			cell = new PdfPCell(new Paragraph("Rekening"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			if (user.isBusinessAccount()) {
				cell = new PdfPCell(new Paragraph(user.getAccountNumber()));
			} else {
				cell = new PdfPCell(new Paragraph(user.getAccountNumber() + ", t.n.v. " + user.getFullName()));
			}
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(chunk);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(subTable);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);

			addSpace(table);

			chunk = new Paragraph("Afnemer", font);
			subTable = new PdfPTable(2);
			cell = new PdfPCell(new Paragraph("Naam"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(invoice.getConsumerName()));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Adres"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(invoice.getConsumerAddress()));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);

			cell = new PdfPCell(chunk);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(subTable);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);

			addSpace(table);
			addSpace(table);

			chunk = new Paragraph("Rekening", font);

			subTable = new PdfPTable(4);
			subTable.setWidths(new int[] { 3, 1, 1, 2 });
			Paragraph headerChunk = new Paragraph("Omschrijving", headerFont);
			cell = new PdfPCell(headerChunk);
			BaseColor color = new BaseColor(225, 245, 245);
			cell.setBackgroundColor(color);
			subTable.addCell(cell);
			headerChunk = new Paragraph("Aantal uren", headerFont);
			cell = new PdfPCell(headerChunk);
			cell.setBackgroundColor(color);
			subTable.addCell(cell);
			headerChunk = new Paragraph("Tarief", headerFont);
			cell = new PdfPCell(headerChunk);
			cell.setBackgroundColor(color);
			subTable.addCell(cell);
			headerChunk = new Paragraph("Totaal", headerFont);
			cell = new PdfPCell(headerChunk);
			cell.setBackgroundColor(color);
			subTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(invoice.getActivityDescription() + ", " + invoice.getMonth() + " " + invoice.getYear()));
			cell.setGrayFill(0.9f);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(Float.toString(invoice.getUnitsOfWork())));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(invoice.getRate().toString()));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(formatDecimal(invoice.getNetAmount())));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			
			if (invoice.getDiscountPercentage() > 0) {
				cell = new PdfPCell(new Paragraph("Korting: "+invoice.getDiscountPercentage()+"% (Versneld betalen)"));
				cell.setGrayFill(0.9f);
				subTable.addCell(cell);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				subTable.addCell(cell);
				subTable.addCell(cell);
				Paragraph chunkMoney = new Paragraph("- "+formatDecimal(invoice.getDiscount()));
				cell = new PdfPCell(chunkMoney);
				cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
				cell.setBorder(PdfPCell.NO_BORDER);
				subTable.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("Subtotaal"));
				cell.setGrayFill(0.9f);
				subTable.addCell(cell);
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				subTable.addCell(cell);
				subTable.addCell(cell);
				chunkMoney = new Paragraph(formatDecimal(invoice.getNetAmountAfterDiscount()));
				cell = new PdfPCell(chunkMoney);
				cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
				cell.setBorder(PdfPCell.NO_BORDER);
				subTable.addCell(cell);			
				
			}			
			
			cell = new PdfPCell(new Paragraph("btw (" + invoice.getVat() + "%)"));
			cell.setGrayFill(0.9f);
			subTable.addCell(cell);
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(formatDecimal(invoice.getVatAmount())));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("Totaal"));
			cell.setGrayFill(0.9f);
			subTable.addCell(cell);
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			subTable.addCell(cell);
			Paragraph chunkMoney = new Paragraph(formatDecimal(invoice.getTotalAmount()), totalAmountFont);
			cell = new PdfPCell(chunkMoney);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			
			cell = new PdfPCell(chunk);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);

			addSpace(table);

			cell = new PdfPCell(subTable);
			table.addCell(cell);

			addSpace(table);

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

}
