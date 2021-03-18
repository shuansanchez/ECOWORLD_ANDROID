package com.example.ecoworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class TeachersActivity extends AppCompatActivity {

    public static final String JUGADOR = "Jugador";
    public static final int MAIN_ACTIVITY = 1;
    public static final int USER_ACTIVITY = 2;
    public static final int SETTINGS_ACTIVITY = 3;
    public static final int INTRO_ACTIVITY = 4;
    public static final int MAP_ACTIVITY = 5;
    public static final int QUESTIONS_ACTIVITY = 6;
    public static final int WIN_ACTIVITY = 7;
    public static final int ACTIVISTAS_ACTIVITY = 8;
    public static final int RANKING_ACTIVITY = 9;

    Jugador jugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        Intent obtenerIntentJugador = getIntent();
        jugador = (Jugador) obtenerIntentJugador.getExtras().getSerializable(JUGADOR);

        final TextView chronoTeachers = findViewById(R.id.chronoTeachers);
        final TextView txtTeachers = findViewById(R.id.txtTeachers);

        chronoTeachers.setVisibility(View.GONE);

        cambiarIdioma(txtTeachers);

        new CountDownTimer(4000, 1000) {
            //onTick: Callback fired on regular interval.
            public void onTick(long millisUntilFinished) {
                chronoTeachers.setText(String.format(Locale.getDefault(),
                        "%d", millisUntilFinished / 1000L));
            }
            //onFinish: Callback fired when the time is up.
            public void onFinish() {
                Intent DevelopersActivity =
                        new Intent(TeachersActivity.this, DevelopersActivity.class);
                DevelopersActivity.putExtra(JUGADOR, jugador);
                startActivity(DevelopersActivity);
            }
        }.start();
    }

    //SETTEAMOS EL TEXTO DE LOS BOTONES SEGUN EL IDIOMA DEL JUGADOR.
    public void cambiarIdioma(TextView txtTeachers) {

        if (jugador.getIdioma() == 0) {  //CAT
            txtTeachers.setText(R.string.professorsProjecte);
        } else if (jugador.getIdioma() == 1) { //ESP
            txtTeachers.setText(R.string.profesoresProyecto);
        } else { //ENG
            txtTeachers.setText(R.string.projectTeachers);
        }
    }

    //CON ESTO ANULAMOS EL BOTON DE VOLVER.
    @Override
    public void onBackPressed() {
    }
}