# MetaheuristicsFinalWork

## Descripción

Trabajo final para la asignatura de Algorítmica II del Grado en Ingeniería Informática en la Universidad Pablo de Olavide, impartida por el profesor David D. de Vega Rodríguez.

Este proyecto tiene el fin de servir a modo de pruebas para el proyecto final de la asignatura Algorítmica II, donde se han estudiado los algoritmos metaheurísticos Enfriamiento Simulado, Algoritmo Genético y Algoritmo Memético, haciendo uso de los dos primeros en el tercero para comprobar la forma en la que trabajan juntos ante el problema propuesto.

Se dispone de una documentación, así como una presentacion que explica el contenido, experimentaciones y resultados obtenidos, incluyendo las conclusiones y un apartado de investigación sobre las metaheurísticas paralelas.

## Estructura
--------------------
* [**Programa_Jar**](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/tree/master/Programa_Jar) → Carpeta con lo necesario para ejecutar la aplicación.
    * [**data**](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/tree/master/Programa_Jar/data) → Datos iniciales para usar en la aplicación.
        * [**img**](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/tree/master/Programa_Jar/data/img)
            * [chart.png](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/Programa_Jar/data/img/chart.png) → imagen cabecera de los pdf
        * [clientes_20.csv](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/Programa_Jar/data/clientes_20.csv)
        * [clientes_200.csv](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/Programa_Jar/data/clientes_200.csv)
        * [clientes_2000.csv](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/Programa_Jar/data/clientes_2000.csv)
        * [empleados_20.csv](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/Programa_Jar/data/empleados_20.csv)
        * [empleados_200.csv](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/Programa_Jar/data/empleados_200.csv)
        * [empleados_200.csv](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/Programa_Jar/data/empleados_2000.csv)
    * [**javadoc**](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/tree/master/Programa_Jar/javadoc) → Documentación para el código y cada una de las funciones.
    * [**lib**](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/tree/master/Programa_Jar/lib) → Las librerías externas que se usan.
    * [**reports**](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/tree/master/Programa_Jar/reports) → Lugar donde se almacenarán los informes.
        * [**pdf**](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/tree/master/Programa_Jar/reports/pdf) → Informes en PDF.
        * [**txt**](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/tree/master/Programa_Jar/reports/pdf) → Informes en TXT.
    * [Metaheuristics_EB_Final.jar](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/Programa_Jar/Metaheuristics_EB_Final.jar) → Fichero jar que ejecutará el programa.
    * [**PROYECTO_JAVA_ALGORITMICA_II**](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/tree/master/PROYECTO_JAVA_ALGORITMICA_II) → Proyecto en Java para importar directamente al IDE.
    * [Documentation_Metaheuristics.pdf](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/Documentation_Metaheuristics.pdf) → Documentación con la experimentación, investigación y conclusiones.
    * [Presentation_Metaheuristics.pdf](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/Presentation_Metaheuristics.pdf) → PDF con la presentación para explicar el contenido de la documentación.
## Ejecución
--------------------
1. Ejecutar Metaheuristics_EB_Final.jar
2. Elegir el tamaño de médicos y pacientes con los que trabajar (Recomendable igual tamaño).
3. Elegir el algoritmo con el que buscar la solución al problema. 
4. Ejecutar el algoritmo. Se verán las dos ventanas comportándose de la siguiente forma:

![](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/mainView.gif)


![](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/graphic.gif)

## ACTUALIZACIÓN 27/07/2018

Se han unificado las dos vistas anteriores y modernizado la interfaz, así como añadido un diagrama de dispersión que muestre el conjunto de puntos con los que se trabaja (posiciones de médicos y pacientes).

![](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/newView.png)

5. Una vez terminada la ejecución se abrirá el archivo PDF con el informe de la solución encontrada.
5. Para observar el array de soluciones acceder a reports/txt y abrir el adecuado con el block de notas.

## Author

* **Joaquín Roiz Pagador** 


## License
 * [MIT License](https://github.com/QuiniRoizPagador/MetaheuristicsFinalWork/blob/master/LICENSE)
