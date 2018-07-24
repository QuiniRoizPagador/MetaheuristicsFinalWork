/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.report.behaviour.helpers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.IOException;

/**
 * Clase que creará las cabeceras y pies de página de los PDF
 *
 *
 * @author Quini Roiz
 */
public class HeaderFooterPageEvent extends PdfPageEventHelper {

    private PdfTemplate t;
    private Image total;

    @Override
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
        addHeader(writer);
        addFooter(writer);
    }

    private void addHeader(PdfWriter writer) {
        PdfPTable header = new PdfPTable(2);
        try {
            // set defaults
            header.setWidths(new int[]{2, 24});
            header.setTotalWidth(527);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(42);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add image
            Image image = Image
                        .getInstance("data/img/chart.png");
            header.addCell(image);

            // add text
            PdfPCell text = new PdfPCell();
            text.setPaddingBottom(15);
            text.setPaddingLeft(10);
            text.setBorder(Rectangle.BOTTOM);
            text.setBorderColor(BaseColor.LIGHT_GRAY);
            text.addElement(new Phrase("Joaquín Roiz Pagador", new Font(Font.FontFamily.HELVETICA, 12)));
            text.addElement(new Phrase("Grado en Ingeniería Informática en Sistemas de Información", new Font(Font.FontFamily.HELVETICA, 8)));
            header.addCell(text);

            // write content
            header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
        } catch (DocumentException | IOException de) {
            throw new ExceptionConverter(de);
        }
    }

    private void addFooter(PdfWriter writer) {
        PdfPTable footer = new PdfPTable(3);
        try {
            // set defaults
            footer.setWidths(new int[]{24, 2, 1});
            footer.setTotalWidth(527);
            footer.setLockedWidth(true);
            footer.getDefaultCell().setFixedHeight(40);
            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add copyright
            footer.addCell(new Phrase("\u00A9 https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));

            // add current page count
            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            footer.addCell(new Phrase(String.format("%d /", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));

            // add placeholder for total page count
            PdfPCell totalPageCount = new PdfPCell(total);
            totalPageCount.setBorder(Rectangle.TOP);
            totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
            footer.addCell(totalPageCount);

            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 50, canvas);
            canvas.endMarkedContentSequence();
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        int totalLength = String.valueOf(writer.getPageNumber()).length();
        int totalWidth = totalLength * 5;
        ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
                    new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)),
                    totalWidth, 6, 0);
    }
}
