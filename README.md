# Simulador Montecarlo para estimar π

Simulador gráfico del método Montecarlo para estimar el valor de π.

## Requisitos

- Tener instalado **Java 8+** (Java Runtime Environment - JRE) para poder ejecutar el archivo `.jar`.  
  [Descargar Java](https://www.java.com/es/download/)

## Descarga

- Archivo ejecutable: [montecarlo-mvc.jar](out/artifacts/montecarlo_mvc_jar/montecarlo-mvc.jar)  
- Archivo con resultados guardados: [resultados.json](resultado.json) *(se crea automáticamente tras la primera ejecución)*.
  
  Se podrán observar datos como el tiempo de ejecución, la cantidad de puntos dentro del círculo, la cantidad total de puntos lanzados y la estimación de π.

## Uso

1. Ejecutar el archivo `.jar` haciendo doble clic o desde la consola:
    
   ```bash
   java -jar SimuladorMontecarlo.jar
   
3. Para iniciar la simulación, hacer clic en el botón **Iniciar**. 
   La simulación seguirá ejecutándose hasta que se presione **Detener**.
4. Al presionar **Detener**, se guardan los datos de esa iteración en el archivo `resultados.json`.
5. Para comenzar una nueva simulación, presionar el botón **Reiniciar**.
6. Para salir de la aplicación, cerrar la ventana usando el botón estándar de la ventana.
