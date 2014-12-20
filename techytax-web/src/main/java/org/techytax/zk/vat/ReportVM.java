package org.techytax.zk.vat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.techytax.report.helper.PdfReportHelper;
import org.zkoss.util.media.AMedia;
import org.zkoss.zkplus.spring.SpringUtil;

public class ReportVM {

	private AMedia report;

	public AMedia getReport() throws Exception {
		PdfReportHelper pdfReportHelper = (PdfReportHelper) SpringUtil.getBean("pdfReportHelper");
		final byte[] buf = pdfReportHelper.createVatReport();
		if (buf != null) {
			final InputStream mediais = new ByteArrayInputStream(buf);
			report = new AMedia("VatReport.pdf", "pdf", "application/pdf", mediais);
		}
		return report;
	}

}
