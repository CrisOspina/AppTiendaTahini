package com.example.app_proyecto1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class Main2Activity_ingresar extends AppCompatActivity
{
    //Iniciar sesión.
    //Objetos
    EditText etUsuario, etClave, etFecha;
    Button   btnInicio;

    private FirebaseAuth mAuth;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_ingresar);

        //Referencio objetos
        etUsuario = findViewById(R.id.etUsuario);
        etClave   = findViewById(R.id.etClave);
        btnInicio = findViewById(R.id.btnInicio);
        etFecha = findViewById(R.id.etFecha);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Flecha atras
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Regresar");
        }


    }

    //Registro
    //Botón inicio para pasar de actividad.
    public void inicio(View view)
    {
        String usuario = etUsuario.getText().toString();
        String clave   = etClave.getText().toString();

        //Validaciones para campos de entrada
        /*if (usuario.isEmpty())
        {
            Toast.makeText(this,"Ingresa usuario", Toast.LENGTH_LONG).show();
        }*/
        /*if (clave.isEmpty())
        {
            Toast.makeText(this,"Ingresa contraseña", Toast.LENGTH_LONG).show();
        }*/

        if (!Patterns.EMAIL_ADDRESS.matcher(usuario).matches()) {
            etUsuario.setError("Debe ingresar un email valido");
            return;
        }
        if (clave.length() < 6)
        {
            etClave.setError("La contraseña debe tener minimo 6 caracteres");
        }
            //Toast.makeText(this, "No tienes autorización para entrar", Toast.LENGTH_LONG).show();

         //(usuario.equals("admin") && clave.equals("admin"))*/

            /*
            Toast.makeText(this,"Bienvenido administrador", Toast.LENGTH_SHORT).show();
            // Pasar de actividad
            Intent lo_intent = new Intent(Main2Activity_ingresar.this, Main4Activity_registros.class);
            startActivity(lo_intent);*/
            /*
            mAuth.createUserWithEmailAndPassword(usuario, clave)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Main2Activity_ingresar.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                                Intent lo_intent = new Intent(getApplicationContext(),Main4Activity_registros.class);
                                startActivity(lo_intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Main2Activity_ingresar.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
                                //Log.d("error", "createUserWithEmail:success");
                            }
                        }
                    });*/

            //Login usuarios existentes
        mAuth.signInWithEmailAndPassword(usuario, clave)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Main2Activity_ingresar.this, "Ingreso de usuario exitoso", Toast.LENGTH_SHORT).show();
                            Intent lo_intent = new Intent(getApplicationContext(),Main4Activity_registros.class);
                            startActivity(lo_intent);
                            finish();
                        } else {
                            Toast.makeText(Main2Activity_ingresar.this, "Correo no existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    //Botón regresar.
    public void regresar(View view)
    {
        Intent lo_intent = new Intent(Main2Activity_ingresar.this, MainActivity.class);
        startActivity(lo_intent);
    }

    public void datePicker(View view)
    {
        Calendar ob_calendar = Calendar.getInstance();
        int year = ob_calendar.get(Calendar.YEAR);
        int month = ob_calendar.get(Calendar.MONTH);
        final int day = ob_calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(
                Main2Activity_ingresar.this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                etFecha.setText(" Año " + year + " - " + " Mes " +  month + " - " +  " Dia " + day);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
}
