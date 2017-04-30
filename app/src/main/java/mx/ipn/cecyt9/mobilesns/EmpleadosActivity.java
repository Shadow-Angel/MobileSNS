package mx.ipn.cecyt9.mobilesns;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class EmpleadosActivity extends AppCompatActivity {

    String NombreBuscado;
    SoapPrimitive resultadoimpre;
    String error;
    Tabla tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);

        Bundle datos = new Bundle();
        datos = getIntent().getExtras();
        NombreBuscado = datos.getString("NombreBuscado");
        tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
    }

    public void Inico(View v){
        try
        {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class llamaws extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            String NAMESPACE = "http://Android.AGC.com/";
            String URL = "http://192.168.1.69:8080/CalculadoraService/Calculadora?WSDL";
            String METHOD_NAME = "CalculadoraBasica";
            String SOAP_ACTION = "http://Android.AGC.com/CalculadoraBasica";

            try {

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("NombreBuscado",NombreBuscado);


                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = false;
                envelope.setOutputSoapObject(request);


                HttpTransportSE ht = new HttpTransportSE(URL);

                ht.call(SOAP_ACTION, envelope);
                SoapObject result = (SoapObject) envelope.bodyIn;
                int Total = result.getPropertyCount();
                ArrayList<String[]> Lista = new ArrayList<String[]>();
                for (int i = 0; i < Total; i++)
                {
                    String[] agregar = (String[]) result.getAttribute(i);
                    Lista.add(agregar);
                }


            }
            catch (Exception e)
            {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                error = sw.toString();
                Toast.makeText(getApplicationContext(), "El resultado es: "+error, Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if(success == false){
                Toast.makeText(getApplicationContext(), "Error " + error, Toast.LENGTH_LONG).show();
            }
            else{
                tabla.agregarCabecera(R.array.cabecera_tabla);
                for(int i = 0; i < 15; i++)
                {
                    ArrayList<String> elementos = new ArrayList<String>();
                    elementos.add(Integer.toString(i));
                    elementos.add("Casilla [" + i + ", 0]");
                    elementos.add("Casilla [" + i + ", 1]");
                    elementos.add("Casilla [" + i + ", 2]");
                    elementos.add("Casilla [" + i + ", 3]");
                    tabla.agregarFilaTabla(elementos);
                }
            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
    }

}
