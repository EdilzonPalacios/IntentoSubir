package hn.uth.a2p23pm01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import hn.uth.a2p23pm01.Configuracion.SQLiteConexion;
import hn.uth.a2p23pm01.Configuracion.Transacciones;
import hn.uth.a2p23pm01.Models.Personas;

public class ActivityCombo extends AppCompatActivity {
    SQLiteConexion conexion;
    Spinner combopersonas;
    EditText nombres,apellidos,correo;

    ArrayList<String> listapersona;
    ArrayList<Personas> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);
    conexion = new SQLiteConexion(this, Transacciones.NameDataBase,null,1);
    combopersonas=(Spinner) findViewById(R.id.spinner);
    nombres=(EditText) findViewById(R.id.cbnombre);
    apellidos=(EditText) findViewById(R.id.cbapellido);
    correo=(EditText) findViewById(R.id.cbcorreo);
    ObtenerTabla();

    ArrayAdapter<CharSequence> adp= new ArrayAdapter(this, android.R.layout.simple_spinner_item,listapersona);
    combopersonas.setAdapter(adp);
    combopersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
            try {
                nombres.setText(lista.get(i).getNombre());
                apellidos.setText(lista.get(i).getApellido());
                correo.setText(lista.get(i).getCorreo());


            }
            catch (Exception ex){
                ex.toString();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });




    }
    private void ObtenerTabla() {
        SQLiteDatabase db=conexion.getReadableDatabase();
        Personas person=null;
        lista=new ArrayList<Personas>();

        Cursor cursor=db.rawQuery(Transacciones.SelectTablePersona,null);
        while (cursor.moveToNext()){
            person=new Personas();
            person.setId(cursor.getInt(0));
            person.setNombre(cursor.getString(1));
            person.setApellido(cursor.getString(2));
            person.setEdad(cursor.getInt(3));
            person.setCorreo(cursor.getString(4));

            lista.add(person);
        }
        cursor.close();
        filllist();

    }

    private void filllist() {
        listapersona=new ArrayList<String>();

        for(int i=0; i<lista.size();i++){
            listapersona.add(lista.get(i).getId()+"-"
                    +lista.get(i).getNombre()+"-"
                    +lista.get(i).getApellido());


        }
    }

}