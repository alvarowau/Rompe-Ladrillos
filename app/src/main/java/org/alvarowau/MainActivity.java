package org.alvarowau;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase principal de la aplicación que gestiona la pantalla de inicio y el inicio del juego.
 *
 * <p>Esta actividad mantiene la pantalla encendida y permite iniciar el juego al presionar un botón.</p>
 *
 * @author Álvaro Bajo
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Método llamado al crear la actividad.
     *
     * <p>Establece el diseño de la actividad y mantiene la pantalla encendida durante la ejecución.</p>
     *
     * @param savedInstanceState Estado de la instancia anterior de la actividad, si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * Inicia el juego al cambiar la vista a {@link VistaJuego}.
     *
     * @param view Vista que activó el evento (normalmente un botón).
     */
    public void startGame(View view) {
        VistaJuego vistaJuego = new VistaJuego(this);
        setContentView(vistaJuego);
    }
}
