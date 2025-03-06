package org.alvarowau;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * La clase VistaJuego representa la vista y la lógica del juego.
 * Controla la interacción del usuario, la física de la bola, la colisión con los ladrillos
 * y la raqueta, y la actualización de la pantalla.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public class VistaJuego extends View {
    Context contexto;
    float bolaX, bolaY;
    Velocidad velocidad = new Velocidad(25, 32);
    Handler manejador;
    final long ACTUALIZAR_MILLIS = 30;
    Runnable runnable;
    Paint textoPintura = new Paint();
    Paint vidaPinturaVerde = new Paint();
    Paint vidaPinturaAmarilla = new Paint();
    Paint vidaPinturaRoja = new Paint();
    Paint vidaPinturaActual;
    Paint ladrilloPintura = new Paint();
    float TAMANO_TEXTO = 120;
    float raquetaX, raquetaY;
    float viejoX, viejoRaquetaX;
    int puntos = 0;
    int vidas = 3;
    Bitmap bola, raqueta;
    int anchoPantalla, altoPantalla;
    int anchoBola, altoBola;
    MediaPlayer sonidoGolpe, sonidoFallo, sonidoRoto;
    Random aleatorio;
    Ladrillo[] ladrillos = new Ladrillo[30];
    int numLadrillos = 0;
    int ladrillosRotos = 0;
    boolean juegoTerminado = false;

    /**
     * Constructor de la clase VistaJuego.
     * Inicializa los recursos gráficos, sonidos y configura el juego.
     *
     * @param contexto El contexto de la aplicación.
     */
    public VistaJuego(Context contexto) {
        super(contexto);
        this.contexto = contexto;
        bola = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        raqueta = BitmapFactory.decodeResource(getResources(), R.drawable.paddle);
        manejador = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        sonidoGolpe = MediaPlayer.create(contexto, R.raw.hit);
        sonidoFallo = MediaPlayer.create(contexto, R.raw.miss);
        sonidoRoto = MediaPlayer.create(contexto, R.raw.breaking);
        textoPintura.setColor(Color.BLACK);
        textoPintura.setTextSize(TAMANO_TEXTO);
        textoPintura.setTextAlign(Paint.Align.LEFT);
        vidaPinturaVerde.setColor(Color.GREEN);
        vidaPinturaAmarilla.setColor(Color.YELLOW);
        vidaPinturaRoja.setColor(Color.RED);
        vidaPinturaActual = vidaPinturaVerde;
        ladrilloPintura.setColor(Color.argb(255, 249, 129, 0));
        Display pantalla = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point tamaño = new Point();
        pantalla.getSize(tamaño);
        anchoPantalla = tamaño.x;
        altoPantalla = tamaño.y;
        aleatorio = new Random();
        bolaX = aleatorio.nextInt(anchoPantalla - 50);
        bolaY = altoPantalla / 3;
        raquetaY = (altoPantalla * 4) / 5;
        raquetaX = anchoPantalla / 2 - raqueta.getWidth() / 2;
        anchoBola = bola.getWidth();
        altoBola = bola.getHeight();
        crearLadrillos();
    }

    /**
     * Crea los ladrillos en la pantalla.
     */
    private void crearLadrillos() {
        int anchoLadrillo = anchoPantalla / 8;
        int altoLadrillo = altoPantalla / 16;
        for (int columna = 0; columna < 8; columna++) {
            for (int fila = 0; fila < 3; fila++) {
                ladrillos[numLadrillos] = new Ladrillo(fila, columna, anchoLadrillo, altoLadrillo);
                numLadrillos++;
            }
        }
    }

    /**
     * Actualiza el color de la barra de vidas según el número de vidas restantes.
     */
    private void actualizarPinturaVidas() {
        if (vidas == 3) {
            vidaPinturaActual = vidaPinturaVerde;
        } else if (vidas == 2) {
            vidaPinturaActual = vidaPinturaAmarilla;
        } else if (vidas == 1) {
            vidaPinturaActual = vidaPinturaRoja;
        } else {
            vidaPinturaActual = vidaPinturaRoja;
        }
    }

    /**
     * Método que dibuja la pantalla del juego.
     *
     * @param lienzo El lienzo sobre el que se dibuja.
     */
    @Override
    protected void onDraw(Canvas lienzo) {
        super.onDraw(lienzo);
        lienzo.drawColor(Color.WHITE);
        bolaX += velocidad.getX();
        bolaY += velocidad.getY();
        if ((bolaX >= anchoPantalla - bola.getWidth()) || bolaX <= 0) {
            velocidad.setX(velocidad.getX() * -1);
        }
        if (bolaY <= 0) {
            velocidad.setY(velocidad.getY() * -1);
        }
        if (bolaY > raquetaY + raqueta.getHeight()) {
            bolaX = 1 + aleatorio.nextInt(anchoPantalla - bola.getWidth() - 1);
            bolaY = altoPantalla / 3;
            if (sonidoFallo != null) {
                sonidoFallo.start();
            }
            velocidad.setX(velocidadX());
            velocidad.setY(32);
            vidas--;
            actualizarPinturaVidas();
            if (vidas == 0) {
                juegoTerminado = true;
                lanzarGameOver();
            }
        }
        if (((bolaX + bola.getWidth()) >= raquetaX)
                && (bolaX <= raquetaX + raqueta.getWidth())
                && (bolaY + bola.getHeight() >= raquetaY)
                && (bolaY + bola.getHeight() <= raquetaY + raqueta.getHeight())) {
            if (sonidoGolpe != null) {
                sonidoGolpe.start();
            }
            velocidad.setX(velocidad.getX() + 1);
            velocidad.setY((velocidad.getY() + 1) * -1);
        }
        lienzo.drawBitmap(bola, bolaX, bolaY, null);
        lienzo.drawBitmap(raqueta, raquetaX, raquetaY, null);
        for (int i = 0; i < numLadrillos; i++) {
            if (ladrillos[i].esVisible()) {
                lienzo.drawRect(ladrillos[i].columna * ladrillos[i].ancho + 1, ladrillos[i].fila * ladrillos[i].alto + 1,
                        ladrillos[i].columna * ladrillos[i].ancho + ladrillos[i].ancho - 1,
                        ladrillos[i].fila * ladrillos[i].alto + ladrillos[i].alto - 1, ladrilloPintura);
            }
        }
        lienzo.drawText("" + puntos, 20, TAMANO_TEXTO, textoPintura);
        lienzo.drawRect(anchoPantalla - 200, 30, anchoPantalla - 200 + 60 * vidas, 80, vidaPinturaActual);
        for (int i = 0; i < numLadrillos; i++) {
            if (ladrillos[i].esVisible()) {
                if (bolaX + anchoBola >= ladrillos[i].columna * ladrillos[i].ancho
                        && bolaX <= ladrillos[i].columna * ladrillos[i].ancho + ladrillos[i].ancho
                        && bolaY <= ladrillos[i].fila * ladrillos[i].alto + ladrillos[i].alto
                        && bolaY >= ladrillos[i].fila * ladrillos[i].alto) {
                    if (sonidoRoto != null) {
                        sonidoRoto.start();
                    }
                    velocidad.setY((velocidad.getY() + 1) * -1);
                    ladrillos[i].hacerInvisible();
                    puntos += 10;
                    ladrillosRotos++;
                    if (ladrillosRotos == 24) {
                        lanzarGameOver();
                    }
                }
            }
        }
        if (ladrillosRotos == numLadrillos) {
            juegoTerminado = true;
        }
        if (!juegoTerminado) {
            manejador.postDelayed(runnable, ACTUALIZAR_MILLIS);
        }
    }

    /**
     * Maneja los eventos táctiles para mover la raqueta.
     *
     * @param evento El evento táctil.
     * @return true si el evento fue manejado, false en caso contrario.
     */
    @Override
    public boolean onTouchEvent(MotionEvent evento) {
        float toqueX = evento.getX();
        float toqueY = evento.getY();
        if (toqueY >= raquetaY) {
            int accion = evento.getAction();
            if (accion == MotionEvent.ACTION_DOWN) {
                viejoX = evento.getX();
                viejoRaquetaX = raquetaX;
            }
            if (accion == MotionEvent.ACTION_MOVE) {
                float desplazamiento = viejoX - toqueX;
                float nuevaRaquetaX = viejoRaquetaX - desplazamiento;
                if (nuevaRaquetaX <= 0)
                    raquetaX = 0;
                else if (nuevaRaquetaX >= anchoPantalla - raqueta.getWidth())
                    raquetaX = anchoPantalla - raqueta.getWidth();
                else
                    raquetaX = nuevaRaquetaX;
            }
        }
        return true;
    }

    /**
     * Lanza la actividad de fin de juego cuando el jugador pierde.
     */
    private void lanzarGameOver() {
        manejador.removeCallbacksAndMessages(null);
        Intent intent = new Intent(contexto, FinDelJuego.class);
        intent.putExtra("puntos", puntos);
        contexto.startActivity(intent);
        ((Activity) contexto).finish();
    }

    /**
     * Libera los recursos de sonido cuando la vista se desvincula de la ventana.
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        liberarRecursosSonido();
    }

    /**
     * Libera los recursos de sonido y recicla los bitmaps.
     */
    private void liberarRecursosSonido() {
        if (sonidoGolpe != null) {
            sonidoGolpe.release();
            sonidoGolpe = null;
        }
        if (sonidoFallo != null) {
            sonidoFallo.release();
            sonidoFallo = null;
        }
        if (sonidoRoto != null) {
            sonidoRoto.release();
            sonidoRoto = null;
        }
        if (bola != null) {
            bola.recycle();
            bola = null;
        }
        if (raqueta != null) {
            raqueta.recycle();
            raqueta = null;
        }
    }

    /**
     * Genera una velocidad aleatoria en el eje X.
     *
     * @return Una velocidad aleatoria en el eje X.
     */
    private int velocidadX() {
        int[] valores = {-35, -30, -25, 25, 30, 35};
        return valores[aleatorio.nextInt(6)];
    }
}