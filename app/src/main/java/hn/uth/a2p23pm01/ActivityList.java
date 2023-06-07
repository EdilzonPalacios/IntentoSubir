package hn.uth.a2p23pm01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hn.uth.a2p23pm01.Configuracion.SQLiteConexion;
import hn.uth.a2p23pm01.Configuracion.Transacciones;
import hn.uth.a2p23pm01.Models.Personas;

public class ActivityList extends AppCompatActivity {
SQLiteConexion conexion;
//ListView lispersonas;
ListView listaPersona;
ArrayList<Personas> lista;
ArrayList<String> ArregloPersonas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        conexion=new SQLiteConexion(this, Transacciones.NameDataBase,null,1);
        listaPersona=(ListView) findViewById(R.id.lispersonas);

        ObtenerTabla();
        ArrayAdapter adp=new ArrayAdapter(this, android.R.layout.simple_list_item_1 , ArregloPersonas);
        listaPersona.setAdapter(adp);

        listaPersona.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el elemento seleccionado en la lista
                String selectedItem = (String) parent.getItemAtPosition(position);

                // Realizar alguna acción con el elemento seleccionado
                Toast.makeText(getApplicationContext(), "Seleccionaste: " + selectedItem, Toast.LENGTH_SHORT).show();
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
        ArregloPersonas=new ArrayList<String>();

        for(int i=0; i<lista.size();i++){
            ArregloPersonas.add(lista.get(i).getId()+"-"
                    +lista.get(i).getNombre()+"-"
                    +lista.get(i).getApellido());


        }
    }
}