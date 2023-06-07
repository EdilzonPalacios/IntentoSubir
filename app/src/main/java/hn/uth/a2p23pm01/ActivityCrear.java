package hn.uth.a2p23pm01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hn.uth.a2p23pm01.Configuracion.SQLiteConexion;
import hn.uth.a2p23pm01.Configuracion.Transacciones;

public class ActivityCrear extends AppCompatActivity {

    EditText nombres,apellidos,edad,correo;
    Button btnagregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        nombres=findViewById(R.id.nombre);
        apellidos=findViewById(R.id.apelldio);
        edad=findViewById(R.id.edad);
        correo=findViewById(R.id.correo);
        btnagregar=(Button) findViewById(R.id.btnagregar);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPerson();
            }
        });


    }

    private void AddPerson() {
        SQLiteConexion conexion= new SQLiteConexion(this, Transacciones.NameDataBase,null,1);
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues valores=new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());

        long result = db.insert(Transacciones.TablaPersonas,Transacciones.id,valores);
        Toast.makeText(this, "Registro Agregado", Toast.LENGTH_LONG).show();
        db.close();
        CleanScreen();
    }

    private void CleanScreen() {
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
    }
}