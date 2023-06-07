package hn.uth.a2p23pm01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Nombre,Apellido;
    Button botonMostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      Nombre=(EditText)findViewById(R.id.Nombre);
      Apellido=(EditText)findViewById(R.id.Apellido);
      botonMostrar=(Button) findViewById(R.id.Mostrar);

      botonMostrar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(MainActivity.this, "Nombre:"+Nombre.getText().toString()+"Apellido:"+Apellido.getText().toString(), Toast.LENGTH_LONG).show();
          }
      });




    }
}