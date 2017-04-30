package mx.ipn.cecyt9.mobilesns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;

public class SNSLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_snslogin);
        }catch (Exception e)
        {
            String Error;
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            Error = sw.toString();
            Toast.makeText(getApplicationContext(), "Error: "+Error, Toast.LENGTH_LONG).show();
        }
    }

    public void Iniciar(View v){
        try {
            Intent envia = new Intent(this, InicioSns.class);
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
