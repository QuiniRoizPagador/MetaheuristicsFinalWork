/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.report.behaviour;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import es.geneticalgorithm.model.Cliente;
import es.geneticalgorithm.model.Empleado;
import es.geneticalgorithm.model.persistence.MemoryData;
import es.geneticalgorithm.model.report.ReportIndividual;
import es.geneticalgorithm.util.Utils;

/**
 * Implementación del comportamiento de exportar en PDF estándar.
 *
 *
 * @author Quini Roiz
 */
public class PrintPDF implements IPrintBehaviour<List<ReportIndividual>> {

    public static final String FONT = "fonts/FreeSans.ttf";

    @Override
    public String print(int type, int problemType, List<ReportIndividual> o, boolean async) throws Exception { // print a report on file

        Calendar cal = Calendar.getInstance();
        String algorithm = "";
        String problem = "";
        switch (type) {
            case Utils.GENETIC_ALGORITHM_TYPE:
                algorithm = "GA_";
                break;
            case Utils.SIMULATED_ANNEALING_TYPE:
                algorithm = "SA_";
                break;
            case Utils.TABU_SEARCH_TYPE:
                algorithm = "TS_";
                break;
            case Utils.ANT_COLONY_OPTIMIZATION:
                algorithm = "ACO_";
                break;
        }
        String formato = algorithm + problem + cal.get(Calendar.HOUR_OF_DAY) + "_" + cal.get(Calendar.MINUTE) + "_" + cal.get(Calendar.SECOND) + "__" + cal.get(Calendar.DAY_OF_MONTH) + "_" + cal.get(Calendar.MONTH) + "_" + cal.get(Calendar.YEAR);
        String filepath = Utils.REPORTPATH + "/pdf/" + formato + ".pdf";

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(filepath));
        document.open();
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font boldfont14 = new Font(bf, 12, Font.BOLD);
        document.add(new Paragraph("ALGORITMO: " + algorithm.replace("_", ""), new Font(Font.FontFamily.COURIER, 25)));
        document.add(new Paragraph("SOLUCIONES ENCONTRADAS: " + o.size(), new Font(Font.FontFamily.COURIER, 25)));
        document.add(new Paragraph("TAMAÑO: " + MemoryData.getInstance().getEmployees().size()
                    + " Médicos, " + MemoryData.getInstance().getClients().size() + " Pacientes", new Font(Font.FontFamily.COURIER, 25)));
        document.add(Chunk.NEWLINE);
        Image image = Image
                    .getInstance("image_aux.png");
        image.setAlignment(Image.ALIGN_CENTER);
        image.scaleAbsolute(500f, 300f);
        document.add(image);

        document.add(Chunk.NEWLINE);
        ReportIndividual best = o.get(o.size() - 1);
        document.add(new Paragraph("Fitness: " + best.getInd().getFitness(), new Font(Font.FontFamily.COURIER, 25)));
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss:S");
        document.add(new Paragraph("Tiempo necesitado(HH:mm:ss:ms): " + format.format(best.getTime().getTime()), new Font(Font.FontFamily.COURIER, 25)));
        HashMap<Empleado, List<Cliente>> tabla = new HashMap<>();

        int[] count = new int[MemoryData.getInstance().getEmployees().size()];

        for (int i = 0; i < MemoryData.getInstance().getClients().size(); i++) {
            count[best.getInd().getGenotype().get(i)]++;
        }
        int i = 0;
        for (Empleado e : MemoryData.getInstance().getEmployees()) {
            if (count[i] > 0) {
                tabla.put(e, new ArrayList<>());
            }
            i++;
        }
        int e;
        i = 0;

        for (Cliente client : MemoryData.getInstance().getClients()) {
            e = best.getInd().getGenotype().get(i);
            tabla.get(MemoryData.getInstance().getEmployees().get(e)).add(client);
            i++;
        }

        for (Map.Entry<Empleado, List<Cliente>> entry : tabla.entrySet()) {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            Empleado key = entry.getKey();
            List<Cliente> value = entry.getValue();

            PdfPCell cab1 = new PdfPCell();
            cab1.addElement(new Phrase("MÉDICO", boldfont14));
            table.addCell(cab1);

            PdfPCell cab2 = new PdfPCell();
            cab2.addElement(new Phrase("PACIENTE", boldfont14));
            table.addCell(cab2);

            Font font14 = new Font(bf, 10);
            String n = "";

            // contenido
            table.addCell(key.toString() + "\n\n NÚMERO DE PACIENTES ASIGNADOS: " + value.size());
            for (Cliente c : value) {
                n += "\n" + c + "\n";
            }
            table.addCell(n);
            document.add(table);
        }

        document.add(Chunk.NEWLINE);

        document.add(Chunk.NEWLINE);
        document.close();
        return filepath;
    }
}
