# Simulador Montecarlo para estimar π

Simulador gráfico del método Montecarlo para estimar el valor de π, con función de guardar los datos de cada
simulación en un archivo para poder comparar resultados de varias iteraciones.

## Requisitos

- Tener instalado **Java 8+** (Java Runtime Environment - JRE) para poder ejecutar el archivo `.jar`.  
  [Descargar Java](https://www.java.com/es/download/)

## Descarga

-

Descargar: [SimuladorMontecarloPI.zip](https://github.com/platiniromina/montecarlo-pi-2.0/releases/download/v1.0.0/SimuladorMontecarloPI.zip)

## Uso

1. Descomprimir la carpeta descargada
2. Ejecutar el archivo `montecarlo-mvc.jar` haciendo doble clic o desde la consola (dentro de la carpeta):

   ```bash
   java -jar montecarlo-mvc.jar

3. Para iniciar la simulación, hacer clic en el botón **Iniciar**.
   La simulación seguirá ejecutándose hasta que se presione **Detener**.
4. Al presionar **Detener**, se guardan los datos de esa iteración en el archivo `resultados.json`. Para verlos, abrir
   el archivo con un editor de texto. Se podrán observar datos como el tiempo de ejecución de la simulación, la cantidad
   de puntos dentro del círculo, la cantidad total de puntos lanzados y la estimación de π.
5. Para comenzar una nueva simulación, presionar el botón **Reiniciar**.
6. Para salir de la aplicación, cerrar la ventana usando el botón estándar de la ventana.
