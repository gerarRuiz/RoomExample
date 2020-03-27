package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.roomexample.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ToksWebServicesConnection {

    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    static AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        database = Room.databaseBuilder(MainActivity.this, AppDatabase.class, Constantes.BD_NAME)
                .allowMainThreadQueries()
                .addMigrations(database.MIGRATION_2_3)
                .build();


        binding.btnInsertarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WSTaskBines();

            }
        });

        binding.buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: NumeroDeRegistros" + String.valueOf(database.entidadBinesDao().count()));
            }
        });
    }

    public static class WebServiceTaskCaller {

        public static void CallWSConsumptionTask(SoapObject request, final String method) {

            ReadJSONFeedTask service = new ReadJSONFeedTask() {
                @Override
                public void onResponseReceived(String jsonResult) {


                    if (method.equals(CATALOGO_BINES)){

                        ResultadoBines(jsonResult);

                    }
                }


            };
            service.execute(request, method);
        }


    }


    private static void WSTaskBines(){

        final SoapObject catalogoBines = new SoapObject(NAMESPACE, CATALOGO_BINES);
        catalogoBines.addProperty("Llave", KEY);
        catalogoBines.addProperty("Cadena", STRING);

        WebServiceTaskCaller.CallWSConsumptionTask(catalogoBines, CATALOGO_BINES);

    }

    private static void ResultadoBines(String jsonResult){

        try{

            JSONArray jsonArray = new JSONArray(jsonResult);
            JSONObject jsonObject;

            for (int i = 0; i < jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                String codigo = jsonObject.getString("CODIGO");
                int tc_prefijo = jsonObject.getInt("TC_PREFIJO");
                int tipo_fpgo = jsonObject.getInt("TIPO_FPGO");
                int sa_idfpgo = jsonObject.getInt("SA_IDFPGO");
                String tr_idfpgo = jsonObject.getString("TR_IDFPGO");
                String tc_debito = jsonObject.getString("TC_DEBITO");
                int factivo = jsonObject.getInt("FACTIVO");
                String tc_tipocom = jsonObject.getString("TC_TIPOCOM");

                long resultado = database.entidadBinesDao().insert(new EntidadBines(codigo, tc_prefijo, tipo_fpgo, sa_idfpgo, tr_idfpgo,
                        tc_debito, factivo, tc_tipocom));

                Log.i(TAG, "ResultadoBines: resultado" + String.valueOf(resultado));

            }

        }catch (JSONException e){

            e.printStackTrace();
        }

    }
}
