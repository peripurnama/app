package id.co.iteacode.report;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import org.springframework.core.io.ClassPathResource;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import id.co.iteacode.util.LocalDateUtil;

public class StylePdfHeaderAndFooter  extends PdfPageEventHelper {

	private PdfTemplate t;
	private Image total;
	
	private LocalDateUtil localDateUtil;

	public void onOpenDocument(PdfWriter writer, Document document) {
		t = writer.getDirectContent().createTemplate(30, 16);
		try {
			total = Image.getInstance(t);
			total.setRole(PdfName.ARTIFACT);
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		addHeader(writer, document);
		addFooter(writer);
	}

	private void addHeader(PdfWriter writer, Document document) {
		
		localDateUtil = new LocalDateUtil();
		PdfPTable header = new PdfPTable(3);
		try {
			
			header.setHeaderRows(1);
			
			// set defaults
			header.setWidthPercentage(100.0f);
			header.setWidths(new float[] { 0.5f, 6.3f, 3.2f });
			header.setSpacingBefore(10);

			header.setTotalWidth(525);
			header.setLockedWidth(true);
			header.getDefaultCell().setFixedHeight(25);
			header.getDefaultCell().setBorder(Rectangle.BOTTOM);
			header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
			header.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);

			ClassPathResource resource = new ClassPathResource("classpath:images/logo.png");
			// add image
			Image logo = Image.getInstance(resource.getPath());
			header.addCell(logo);

			header.addCell("PT Intersolusi Teknologi Asia");

			String dateFormat = ("dd/MMMM/yyyy HH:mm a");
			header.addCell(localDateUtil.asLocalDateTime(new Date(), dateFormat));

			// write content
			header.writeSelectedRows(0, -1, 34, 823, writer.getDirectContent());

		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		} catch (MalformedURLException e) {
			throw new ExceptionConverter(e);
		} catch (IOException e) {
			throw new ExceptionConverter(e);
		}
	}

	private void addFooter(PdfWriter writer) {
		PdfPTable footer = new PdfPTable(1);
		try {

			footer.setWidths(new int[] { 100 });
			footer.setTotalWidth(527);
			footer.setLockedWidth(false);
			footer.getDefaultCell().setFixedHeight(25);
			footer.getDefaultCell().setBorder(Rectangle.TOP);
			footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

			// add current page count
			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			footer.addCell(new Phrase(String.format("Page %d ", writer.getPageNumber()),
					new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));

			// write page
			PdfContentByte canvas = writer.getDirectContent();
			canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
			footer.writeSelectedRows(0, -1, 34, 35, canvas);
			canvas.endMarkedContentSequence();
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	// public void onCloseDocument(PdfWriter writer, Document document) {
	// int totalLength = String.valueOf(writer.getPageNumber()).length();
	// int totalWidth = totalLength * 5;
	// ColumnText.showTextAligned(t, Element.ALIGN_LEFT,
	// new Phrase(String.valueOf(writer.getPageNumber()), new
	// Font(Font.FontFamily.HELVETICA, 12)),
	// totalWidth, 6, 0);
	// }

}