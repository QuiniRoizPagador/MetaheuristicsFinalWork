/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.report.behaviour;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import es.geneticalgorithm.model.Cliente;
import es.geneticalgorithm.model.Empleado;
import es.geneticalgorithm.model.persistence.MemoryData;
import es.geneticalgorithm.model.report.ReportIndividual;
import es.geneticalgorithm.model.report.behaviour.helpers.HeaderFooterPageEvent;
import es.geneticalgorithm.util.Utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Implementación del comportamiento de impresión en PDF utilizando plantilla
 * HTML5
 *
 *
 * @author Quini Roiz
 */
public class PrintHTML5PDF implements IPrintBehaviour<ReportIndividual> {

    public static final String CSS = "table.table-style-two {"
                + "		font-family: verdana, arial, sans-serif;"
                + "         margin-top: 80px;"
                + "         margin-bottom: 80px;"
                + "         width: 700px;"
                + "		font-size: 11px;"
                + "		color: #333333;"
                + "		border-width: 1px;"
                + "		border-color: #3A3A3A;"
                + "		border-collapse: collapse;"
                + "	}"
                + "	table.table-style-two th {"
                + "		border-width: 1px;"
                + "		padding: 8px;"
                + "		border-style: solid;"
                + "		border-color: #517994;"
                + "		background-color: #B2CFD8;"
                + "	}"
                + "	table.table-style-two td {"
                + "		border-width: 1px;"
                + "		padding: 8px;"
                + "		border-style: solid;"
                + "		border-color: #517994;"
                + "		background-color: #ffffff;"
                + "	}";

    @Override
    public String print(int type, int problemType, ReportIndividual best, boolean async) throws Exception {

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
            case Utils.MEMETIC_ALGORITHM_TYPE:
                algorithm = "MEMETIC_";
                break;
        }
        String formato = (async ? "ASYNC" : "SYNC") + "_" + algorithm + problem + cal.get(Calendar.DAY_OF_MONTH) + "_" + cal.get(Calendar.MONTH) + "_" + cal.get(Calendar.YEAR) + "__" + cal.get(Calendar.HOUR_OF_DAY) + "_" + cal.get(Calendar.MINUTE) + "_" + cal.get(Calendar.SECOND);
        String filepath = Utils.REPORTPATH + "/pdf/" + formato + ".pdf";

        // step 1
        Document document = new Document(PageSize.A4);
        File f = new File(filepath);
        OutputStream file = new FileOutputStream(f);

        PdfWriter writer = PdfWriter.getInstance(document, file);
        writer.setPageEvent(new HeaderFooterPageEvent());
        document.setPageSize(PageSize.A4);
        document.setMargins(50, 100, 100, 70);
        document.open();

        // step 2
        String html = "<html><head><title>Website</title></head><body>";

        StringBuilder sb = new StringBuilder();
        sb.append(html);

        HashMap<Empleado, List<Cliente>> tabla = new HashMap<>();

        int[] count = new int[MemoryData.getInstance().getEmployees().size()];

        for (int i = 0; i < MemoryData.getInstance().getClients().size(); i++) {
            count[best.getInd().getGenotype().get(i)]++;
        }
        int i = 0;
        double cost = 0, dist = 0;
        for (Empleado e : MemoryData.getInstance().getEmployees()) {
            if (count[i] > 0) {
                cost += e.getCoste();
                tabla.put(e, new ArrayList<>());
            }
            i++;
        }
        int e;
        i = 0;
        Empleado emp;
        for (Cliente client : MemoryData.getInstance().getClients()) {
            e = best.getInd().getGenotype().get(i);
            emp = MemoryData.getInstance().getEmployees().get(e);
            dist += MemoryData.getInstance().getDistances()[i][e];
            tabla.get(emp).add(client);
            i++;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        sb.append(async ? "ASYNC" : "SYNC").append("_ALGORITMO: ").append(algorithm.replace("_", "")).append("<br/>");
        sb.append("TAMAÑO: ")
                    .append(MemoryData.getInstance().getEmployees().size())
                    .append(" Médicos, ")
                    .append(MemoryData.getInstance().getClients().size())
                    .append(" Pacientes.").append("<br/><br/>");
        sb.append("<img src=").append("image_aux.png")
                    .append("/>").append("<br/>");
        sb.append("Fitness: ").append(best.getInd().getFitness()).append("<br/>");
        sb.append("Número de iterationes para el éxito: ").append((int) best.getIteration()).append("<br/>");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss:S");
        sb.append("Tiempo necesitado(HH:mm:ss:ms): ").append(format.format(best.getTime().getTime())).append("<br/>");
        sb.append("Número de Médicos contratados: ").append(tabla.size()).append("<br/>");
        sb.append("Coste Total: ").append(cost).append("€").append("<br/>");
        sb.append("Distancia Total: ").append(dist / 1000).append("km").append("<br/>");
        sb.append("<table class=\"table-style-two\">");
        sb.append("<tr>");
        sb.append("<th><b>Empleado</b></th>");
        sb.append("<th><b>Clientes Asignados</b></th>");
        sb.append("</tr>");

        for (Map.Entry<Empleado, List<Cliente>> entry : tabla.entrySet()) {
            Empleado key = entry.getKey();
            List<Cliente> value = entry.getValue();
            e = MemoryData.getInstance().getEmployees().indexOf(key);
            sb.append("<tr>");
            sb.append("<td valign=\"top\">");
            sb.append("<b>Nombre: </b>");
            sb.append(key.getNombre());
            sb.append("<br/>");

            sb.append("<b>Apellidos: </b>");
            sb.append(key.getApellido1()).append(" ").append(key.getApellidos2());
            sb.append("<br/>");

            sb.append("<b>Sexo:</b>");
            sb.append(key.getSexo());
            sb.append("<br/>");

            sb.append("<b>Dni: </b>");
            sb.append(key.getDni());
            sb.append("<br/>");

            sb.append("<b>Dirección: </b>");
            sb.append(key.getDireccion());
            sb.append("<br/>");

            sb.append("<b>Municipio:</b>");
            sb.append(key.getMunicipio());
            sb.append("<br/>");

            sb.append("<b>Provincia: </b>");
            sb.append(key.getProvincia());
            sb.append("<br/>");

            sb.append("<b>CP: </b>");
            sb.append(key.getCp());
            sb.append("<br/>");

            sb.append("<b>Móvil: </b>");
            sb.append(key.getMovil());
            sb.append("<br/>");

            sb.append("<b>Teléfono: </b>");
            sb.append(key.getTelefono());
            sb.append("<br/>");

            sb.append("<b>Correo: </b>");
            sb.append(key.getCorreo());
            sb.append("<br/>");

            sb.append("<b>Nacimiento: </b>");
            sb.append(sdf.format(key.getNacimiento().getTime()));
            sb.append("<br/>");

            sb.append("<b>Max. Asignables: </b>");
            sb.append(key.getMax_clientes());
            sb.append("<br/>");

            sb.append("<b>Asignados: </b>");
            sb.append(value.size());
            sb.append("<br/>");

            sb.append("</td>");
            sb.append("<td>");
            i = 0;
            for (Cliente c : value) {

                sb.append("<b>Nombre: </b>");
                sb.append(c.getNombre());
                sb.append("<br/>");

                sb.append("<b>Apellidos: </b>");
                sb.append(c.getApellido1()).append(" ").append(c.getApellidos2());
                sb.append("<br/>");

                sb.append("<b>Sexo: </b>");
                sb.append(c.getSexo());
                sb.append("<br/>");

                sb.append("<b>Dni: </b>");
                sb.append(c.getDni());
                sb.append("<br/>");

                sb.append("<b>Dirección: </b>");
                sb.append(c.getDireccion());
                sb.append("<br/>");

                sb.append("<b>Municipio: </b>");
                sb.append(c.getMunicipio());
                sb.append("<br/>");

                sb.append("<b>Provincia: </b>");
                sb.append(c.getProvincia());
                sb.append("<br/>");

                sb.append("<b>CP: </b>");
                sb.append(c.getCp());
                sb.append("<br/>");

                sb.append("<b>Móvil: </b>");
                sb.append(c.getMovil());
                sb.append("<br/>");

                sb.append("<b>Teléfono: </b>");
                sb.append(c.getTelefono());
                sb.append("<br/>");

                sb.append("<b>Correo: </b>");
                sb.append(c.getCorreo());
                sb.append("<br/>");

                sb.append("<b>Nacimiento: </b>");
                sb.append(sdf.format(c.getNacimiento().getTime()));
                sb.append("<br/>");

                sb.append("<b>Distancia: </b>");
                sb.append(MemoryData.getInstance().getDistances()[i][e] / 100).append(" km");
                sb.append("<br/>");

                if (i < value.size() - 1) {
                    sb.append("<hr/>");
                }
                i++;
            }
            sb.append("</td>");
            sb.append("</tr>");

        }
        sb.append("</table>");
        // cuerpo
        sb.append("</body>");
        sb.append("</html>");

        CSSResolver cssResolver = new StyleAttrCSSResolver();
        CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(CSS.getBytes()));
        cssResolver.addCss(cssFile);
        // HTML
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

        // Pipelines
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline htmlpipe = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline csspipe = new CssResolverPipeline(cssResolver, htmlpipe);
        // XML Worker
        XMLWorker worker = new XMLWorker(csspipe, true);
        XMLParser p = new XMLParser(worker);
        p.parse(new ByteArrayInputStream(sb.toString().getBytes()));

        // step 5
        document.close();
        return f.getAbsolutePath();
    }

}
