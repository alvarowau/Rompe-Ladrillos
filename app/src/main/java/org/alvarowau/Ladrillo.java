package org.alvarowau;

/**
 * La clase Ladrillo representa un ladrillo en un entorno gráfico.
 * Cada ladrillo tiene una posición (fila y columna), un tamaño (ancho y alto),
 * y un estado de visibilidad (visible o invisible).
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public class Ladrillo {
    private boolean esVisible;
    public int fila, columna, ancho, alto;

    /**
     * Constructor para crear un objeto Ladrillo con una posición y tamaño específicos.
     * El ladrillo se crea inicialmente como visible.
     *
     * @param fila La fila en la que se encuentra el ladrillo.
     * @param columna La columna en la que se encuentra el ladrillo.
     * @param ancho El ancho del ladrillo.
     * @param alto El alto del ladrillo.
     */
    public Ladrillo(int fila, int columna, int ancho, int alto) {
        esVisible = true;
        this.fila = fila;
        this.columna = columna;
        this.ancho = ancho;
        this.alto = alto;
    }

    /**
     * Hace que el ladrillo sea invisible.
     */
    public void hacerInvisible() {
        esVisible = false;
    }

    /**
     * Devuelve el estado de visibilidad del ladrillo.
     *
     * @return true si el ladrillo es visible, false si no lo es.
     */
    public boolean esVisible() {
        return esVisible;
    }

    /**
     * Establece la visibilidad del ladrillo.
     *
     * @param esVisible true para hacer el ladrillo visible, false para hacerlo invisible.
     */
    public void setVisible(boolean esVisible) {
        this.esVisible = esVisible;
    }
}