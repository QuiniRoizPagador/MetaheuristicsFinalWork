/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.util;

/**
 * Clase utilidad que contendrá los valores de la aplicación.
 *
 * @author Quini Roiz
 */
public class Utils {

    /**
     * Tamaño de la población
     */
    public static int population_size = 80;

    /**
     * Tamaño del individuo (tamaño de la mochila, ruta, asignaciones posibles,
     * etc)
     */
    //public static int individual_size = 20;
    /**
     * Tasa de mutación
     */
    public static final float MUTATION_RATE = 0.2F;

    /**
     * Porcentaje de padre a usar en cruce
     */
    public static final float PARENT_USE_PERCENT = 0.3F;

    /**
     * Porcentaje de la población a coger por elitismo
     */
    public static final float ELITE_PERCENT = 0.1F;

    /**
     * Porcentaje de ruleta a coger
     */
    public static final float ROULETE_PERCENT = 0.2F;

    /**
     * Porcentaje de torneo a coger
     */
    public static final float TOURNAMENT_PERCENT = 0.3F;

    /**
     * Tasa de clonación
     */
    public static final float CLONE_RATE = 0.1F;

    /**
     * Tasa de elitismo parental para torno
     */
    public static final float ELITE_PARENT_RATE = 0.3F;

    /**
     * Número e hilos a crear si se hace paralelizado el algoritmo principal
     */
    public static final int THREAD_NUMBER = 4;

    /**
     * Número máximo de iteraciones del algoritmo principal
     */
    //public static final float MAX_ITERATIONS = 10000;
    /**
     * Número máximo de repeticiones antes de converger
     */
    //public static final int MAX_REPETITIONS = 500;
    // TIPOS 
    public static final int SNAPSACK_TYPE = 0;

    public static final int TSP_TYPE = 1;

    public static final int QUADRATIC_ASSIGNMENT_TYPE = 2;

    public static final int DIVERSITY_ASSIGNMENT_TYPE = 3;

    public static final int PORTION_TYPE = 0;

    public static final int UNIFORM_TYPE = 1;

    public static final int GENETIC_ALGORITHM_TYPE = 0;

    public static final int SIMULATED_ANNEALING_TYPE = 1;

    public static final int TABU_SEARCH_TYPE = 2;

    public static final int ANT_COLONY_OPTIMIZATION = 3;

    public static final int MEMETIC_ALGORITHM_TYPE = 4;

    public static final int REPORT_FILE_TYPE = 0;

    public static final int REPORT_PDF_TYPE = 1;
    public static final int REPORT_HTML5PDF_TYPE = 2;

    /**
     * Tiempo máximo de espera (2 minutos) a los hilos, por si hay problema
     * computacional
     */
    public static final long MAXTIMEOUT = 100;

    /**
     * Peso máximo de la mochila
     */
    public static final int SNAPSACK_WEIGHT = 100;

    /**
     * Máximo valor de beneficio que se otorgará al generar beneficios
     */
    public static final int SNAPSACK_ADVANTAGE_MAX = 200;

    //public static double INITIAL_TEMP = 10000;
    //public static int MAX_TABU = 4;
    public static String REPORTPATH = "reports";

}
