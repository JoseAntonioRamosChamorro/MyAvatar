package com.example.myavatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Interfaz {
    Button btnCrear;
    DialogNombre dialogoNombre;
    EditText txtNombre;
    EditText txtSexo;
    EditText txtEspecie;
    EditText txtProfesion;
    EditText txtVida;
    EditText txtMagia;
    EditText txtFuerza;
    EditText txtVelocidad;
    ImageView imgAvatar;
    ImageView imgProfesion;
    String Nombre, Sexo, Especie, Profesion;
    Random aleatorio = new Random(System.currentTimeMillis());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Evita que gire la app(para que solo se pueda ver en horizontal
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCrear = findViewById(R.id.btnCrear);
        txtNombre = findViewById(R.id.txtNombre);
        txtSexo = findViewById(R.id.txtSexo);
        txtEspecie = findViewById(R.id.txtEspecie);
        txtProfesion = findViewById(R.id.txtProfesion);
        txtVida = findViewById(R.id.txtVida);
        txtMagia = findViewById(R.id.txtMagia);
        txtFuerza = findViewById(R.id.txtFuerza);
        txtVelocidad = findViewById(R.id.txtVelocidad);
        imgAvatar = findViewById(R.id.imgAvatar);
        imgProfesion = findViewById(R.id.imgProfesion);
        imgAvatar.setImageResource(R.drawable.avatar);
        imgProfesion.setImageResource(R.drawable.ic_profesion);

        //Agregar listener
        btnCrear.setOnClickListener((View.OnClickListener) this);

        //Evita que se pueda escribir en los campos
        txtNombre.setEnabled(false);
        txtSexo.setEnabled(false);
        txtEspecie.setEnabled(false);
        txtProfesion.setEnabled(false);
        txtVida.setEnabled(false);
        txtMagia.setEnabled(false);
        txtFuerza.setEnabled(false);
        txtVelocidad.setEnabled(false);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crear(btnCrear);
            }
        });
    }

    public void crear(View view) {
        dialogoNombre = new DialogNombre();

        //Evita que se cierren los toast si se pulsa fuera de ellos
        dialogoNombre.setCancelable(false);
        dialogoNombre.show(getSupportFragmentManager(), "Dialogo Nombre");
    }


    @Override
    public void onDlgGuardarListener() {
        Toast.makeText(this, R.string.dlg_guardado, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDlgCancelarListener() {
        Toast.makeText(this, R.string.dlg_cancelado, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataSetNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public void onDataSetSexo(String sexo) {
        Sexo = sexo;
    }


    @Override
    public void onDataSetEspecie(String especie) {
        Especie = especie;
    }

    @Override
    public void onDataSetProfesion(String profesion) {
        Profesion = profesion;

        //Rellenamos los datos
        txtNombre.setText(Nombre);
        txtSexo.setText(Sexo);
        txtEspecie.setText(Especie);
        txtProfesion.setText(Profesion);

        //Poner aleatorios a las estadisticas basicas
        txtVida.setText("" + aleatorio.nextInt(100 - 1));
        txtMagia.setText("" + aleatorio.nextInt(10 - 1));
        txtFuerza.setText("" + aleatorio.nextInt(20 - 1));
        txtVelocidad.setText("" + aleatorio.nextInt(5 - 1));
        imagenAvatar(Sexo, Especie);
        imagenProfesion(Profesion);
    }

    //Comprueba y la imagen del raza
    public void imagenAvatar(String Sexo, String Especie) {
        if ((Sexo.equals("Hombre")) || Sexo.equals("Men")) {
            if ((Especie.equals("Humano")) || (Especie.equals("Human"))) {
                imgAvatar.setImageResource(R.drawable.humano);
            } else if ((Especie.equals("Enano")) || (Especie.equals("Dwarf"))) {
                imgAvatar.setImageResource(R.drawable.enano);
            } else if ((Especie.equals("Elfo")) || (Especie.equals("Elf"))) {
                imgAvatar.setImageResource(R.drawable.elfo);
            } else if (Especie.equals("Hobbit")) {
                imgAvatar.setImageResource(R.drawable.hoobit);
            }
        } else if ((Sexo.equals("Mujer")) || Sexo.equals("Mujer")) {
            if ((Especie.equals("Humano")) || (Especie.equals("Humano"))) {
                imgAvatar.setImageResource(R.drawable.humana);
            } else if ((Especie.equals("Enano")) || (Especie.equals("Enano"))) {
                imgAvatar.setImageResource(R.drawable.enana);
            } else if ((Especie.equals("Elfo")) || (Especie.equals("Elfo"))) {
                imgAvatar.setImageResource(R.drawable.elfa);
            } else if (Especie.equals("Hobbit")) {
                imgAvatar.setImageResource(R.drawable.hobbitmujer   );
            } else {
                imgAvatar.setImageResource(R.drawable.avatar);
            }
        }
    }

    //Comprueba y la imagen de la clase
    public void imagenProfesion(String Profesion) {
        if ((Profesion.equals("Arquero")) || (Profesion.equals("Arquero"))) {
            imgProfesion.setImageResource(R.drawable.ic_arquero);
        } else if ((Profesion.equals("Guerrero")) || (Profesion.equals("Guerrero"))) {
            imgProfesion.setImageResource(R.drawable.ic_guerrero);
        } else if ((Profesion.equals("Mago")) || (Profesion.equals("Mago"))) {
            imgProfesion.setImageResource(R.drawable.ic_mago);
        } else if ((Profesion.equals("Herrero")) || (Profesion.equals("Herrero"))) {
            imgProfesion.setImageResource(R.drawable.ic_herrero);
        } else if ((Profesion.equals("Minero")) || (Profesion.equals("Minero"))) {
            imgProfesion.setImageResource(R.drawable.ic_herrero);
        } else {
            imgProfesion.setImageResource(R.drawable.ic_profesion);
        }
    }

}