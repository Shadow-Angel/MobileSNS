package mx.ipn.cecyt9.mobilesns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;

public class InicioSns extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sns);
    }

    public void Usuarios(View v){
        try
        {
            Intent envia = new Intent(this, UsuariosActivity.class);
            Bundle datos = new Bundle();
            envia.putExtras(datos);
            finish();
            startActivity(envia);
        }
        catch (Exception e)
        {
            String Error;
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            Error = sw.toString();
            Toast.makeText(getApplicationContext(), "Error: "+Error, Toast.LENGTH_LONG).show();
        }
    }


    public void Empleados(View v){
        try{
            Intent envia = new Intent(this, EmpleadosActivity.class);
            Bundle datos = new Bundle();
            datos.putString("NombreBuscado","");
            envia.putExtras(datos);
            finish();
            startActivity(envia);
        }
        catch (Exception e)
        {
            String Error;
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            Error = sw.toString();
            Toast.makeText(getApplicationContext(), "Error: "+Error, Toast.LENGTH_LONG).show();
        }
    }


    public void Reportes(View v){
        try {
            Intent envia = new Intent(this, ReportActivity.class);
            Bundle datos = new Bundle();
            envia.putExtras(datos);
            finish();
            startActivity(envia);
        }
        catch (Exception e)
        {
            String Error;
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            Error = sw.toString();
            Toast.makeText(getApplicationContext(), "Error: "+Error, Toast.LENGTH_LONG).show();
        }
    }


}

