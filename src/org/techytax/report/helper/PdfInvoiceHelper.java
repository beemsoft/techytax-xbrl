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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class PdfInvoiceHelper {

	private static void addSpace(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setFixedHeight(15f);
		cell.setBorder(PdfPCell.NO_BORDER);
		table.addCell(cell);
	}

	public static String formatDecimal(BigDecimal b) {

		Locale loc = new Locale("nl", "NL", "EURO");
		NumberFormat n = NumberFormat.getCurrencyInstance(loc);
		double doublePayment = b.doubleValue();
		String s = n.format(doublePayment);
		return s;

	}

	public static void main(String[] args) {
		BigDecimal payment = new BigDecimal("1234523423.67");
		System.out.println(formatDecimal(payment));
	}

	public static byte[] createPdfInvoice(Invoice factuur, User user) {

		Document document = new Document();
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
			cell = new PdfPCell(new Paragraph(Integer.toString(factuur.getInvoiceNumber())));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Datum"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(DateHelper.getInvoiceDateString(new Date())));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Maand"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(factuur.getMonth()));
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

			cell = new PdfPCell(new Paragraph("Naam"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(user.getCompanyName()));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Adres"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(user.getCompanyAddress()));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("BTW-id-nr"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("NL " + user.getFiscalNumber()));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Rekening"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(user.getAccountNumber() + ", t.n.v. " + user.getFullName()));
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
			cell = new PdfPCell(new Paragraph(factuur.getConsumerName()));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Adres"));
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(factuur.getConsumerAddress()));
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
			cell = new PdfPCell(new Paragraph(factuur.getActivityDescription() + ", " + factuur.getMonth() + " " + factuur.getYear()));
			cell.setGrayFill(0.9f);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(Float.toString(factuur.getUnitsOfWork())));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(factuur.getRate().toString()));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(formatDecimal(factuur.getNetAmount())));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("btw (" + factuur.getVat() + "%)"));
			cell.setGrayFill(0.9f);
			subTable.addCell(cell);
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			subTable.addCell(cell);
			subTable.addCell(cell);
			cell = new PdfPCell(new Paragraph(formatDecimal(factuur.getVatAmount())));
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
			Paragraph chunkMoney = new Paragraph(formatDecimal(factuur.getTotalAmount()), totalAmountFont);
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
