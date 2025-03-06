package org.alvarowau;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * La clase FinDelJuego representa la actividad que se muestra cuando el juego termina.
 * Muestra la puntuación final y permite al jugador reiniciar el juego o salir.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public class FinDelJuego extends AppCompatActivity {

    TextView tvPoints;
    ImageButton imgBtn1, imgBtn2;
    ImageView ivNewHighest, ivLooser;
    Animation animacion6;

    /**
     * Método que se ejecuta al crear la actividad.
     * Inicializa los componentes de la interfaz de usuario y muestra la puntuación final.
     *
     * @param savedInstanceState El estado guardado de la actividad.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        imgBtn1 = findViewById(R.id.imgBtn1);
        imgBtn2 = findViewById(R.id.imgBtn2);
        ivNewHighest = findViewById(R.id.ivNewHeighest);
        ivLooser = findViewById(R.id.ivLooser);
        tvPoints = findViewById(R.id.tvPoints);

        animacion6 = AnimationUtils.loadAnimation(FinDelJuego.this, R.anim.animation6);

        int points = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("puntos")) {
                points = extras.getInt("puntos");
            }
        }

        if (points == 240) {
            ivNewHighest.setVisibility(View.VISIBLE);
            ivNewHighest.startAnimation(animacion6);
        } else {
            ivLooser.setVisibility(View.VISIBLE);
            ivLooser.startAnimation(animacion6);
        }
        tvPoints.setText("" + points);
    }

    /**
     * Método que se ejecuta cuando el jugador decide reiniciar el juego.
     * Inicia la actividad principal del juego.
     *
     * @param view La vista que desencadenó el evento.
     */
    public void restart(View view) {
        Animation imageanim = AnimationUtils.loadAnimation(FinDelJuego.this, R.anim.animation5);
        imgBtn1.startAnimation(imageanim);
        Intent intent = new Intent(FinDelJuego.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Método que se ejecuta cuando el jugador decide salir del juego.
     * Cierra la actividad actual.
     *
     * @param view La vista que desencadenó el evento.
     */
    public void exit(View view) {
        Animation imageanime = AnimationUtils.loadAnimation(FinDelJuego.this, R.anim.animation5);
        imgBtn2.startAnimation(imageanime);
        finish();
    }
}