package org.alvarowau;

/**
 * La clase Velocidad representa un vector de velocidad en un espacio bidimensional.
 * Contiene dos componentes: velocidad en el eje X y velocidad en el eje Y.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public class Velocidad {

    private int x, y;

    /**
     * Constructor para crear un objeto Velocidad con componentes específicos en los ejes X e Y.
     *
     * @param x La componente de velocidad en el eje X.
     * @param y La componente de velocidad en el eje Y.
     */
    public Velocidad(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Devuelve la componente de velocidad en el eje X.
     *
     * @return La componente de velocidad en el eje X.
     */
    public int getX() {
        return x;
    }

    /**
     * Establece la componente de velocidad en el eje X.
     *
     * @param x La nueva componente de velocidad en el eje X.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Devuelve la componente de velocidad en el eje Y.
     *
     * @return La componente de velocidad en el eje Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Establece la componente de velocidad en el eje Y.
     *
     * @param y La nueva componente de velocidad en el eje Y.
     */
    public void setY(int y) {
        this.y = y;
    }
}