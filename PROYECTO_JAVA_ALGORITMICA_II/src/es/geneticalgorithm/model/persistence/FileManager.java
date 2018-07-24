/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model.persistence;

import java.io.*;

/**
 * Clase estática que lee ficheros
 *
 *
 * @author Quini Roiz
 */
public class FileManager {

    /**
     * Método que leerá de fichero.
     *
     * @param filePath Ruta del fichero a leer.
     * @return String con el contenido del fichero leído.
     * @throws Exception En caso de error.
     */
    public static String readFile(String filePath) throws Exception {
        String content = "", line;
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            content += line + "\n";
        }
        br.close();
        fr.close();
        return content;
    }

    /**
     * Método que escribirá en fichero.
     *
     * @param filePath Ruta del fichero a escribir.
     * @param content Contenido a escribir.
     * @throws Exception En caso de error.
     */
    public static void writeFile(String filePath, String content) throws Exception {
        FileWriter fw = new FileWriter(filePath);
        PrintWriter pw = new PrintWriter(fw);
        pw.print(content);
        fw.close();
    }

    /**
     * Método que creará un fichero.
     *
     * @param filePath Ruta del fichero a crear.
     * @return boolean con el resultado de la operación.
     * @throws Exception En caso de error.
     */
    public static boolean createFile(String filePath) throws Exception {
        Boolean res;
        File f = new File(filePath);
        if (res = !f.exists()) {
            new BufferedWriter(new FileWriter(f)).write("");
        }
        return res;
    }
}
