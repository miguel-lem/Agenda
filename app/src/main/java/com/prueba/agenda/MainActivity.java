package com.prueba.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        private EditText editname, editdireccion, editcorreo, editPhone;
        String grupo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editname=(EditText)findViewById(R.id.editName);
        editdireccion=(EditText)findViewById(R.id.editdireccion);
        editcorreo=(EditText)findViewById(R.id.editCorreo);
        editPhone=(EditText)findViewById(R.id.editPhone);
        Toast.makeText(this, "iniciando contactos", Toast.LENGTH_LONG).show();
    }

    public void guardar(View view){
        //el sharepreferences me permite tomar datos y almacenarlos
        //yo creo un objeto con el nombre preferencia y un archivo que llevara nombre cotactos
        //el mode private me dice que solo este archivo puede tener acceso
        //en caso de que se formatea la aplicacion se borrara los datos
        SharedPreferences preferencia=getSharedPreferences("contactos", Context.MODE_PRIVATE);
        //creo una instancia de editar con el nombre de editor
        SharedPreferences.Editor editor= preferencia.edit();
        //editor.putString("napellido", editname.getText().toString());
        //editor.putString("ndireccion", editdireccion.getText().toString());
        //editor.putString("ncorreo", editcorreo.getText().toString());
        //editor.putString("ntelefono", editPhone.getText().toString());
        //con el commit lo que hago es cerrar esta parte de tomar los datos desde la interfaz
        grupo=editdireccion.getText().toString()+";"+editcorreo.getText().toString()+";"+editPhone.getText().toString();
        editor.putString(editname.getText().toString(),grupo);
        editor.commit();
        Toast.makeText(this,"Contacto guardado exitosamente",Toast.LENGTH_SHORT).show();
        //con el finish me aseguro que no quede por alli algo abierto
        finish();
    }

    public void buscar(View view){
        SharedPreferences preferencia=getSharedPreferences("contactos", Context.MODE_PRIVATE);
        String nombr = editname.getText().toString();
        String datos = preferencia.getString(nombr,"");

        //String nombre=preferencia.getString("napellido","");
        //String direccion=preferencia.getString("ndireccion","");
        //String correo=preferencia.getString("ncorreo","");
        //String telefono=preferencia.getString("ntelefono","");
        /*if(nombre.compareToIgnoreCase(editname.getText().toString())==0){
            Toast.makeText(this,"contacto encontrado",Toast.LENGTH_LONG).show();
            editname.setText(nombre);
            editdireccion.setText(direccion);
            editcorreo.setText(correo);
            editPhone.setText(telefono);
        }else{
            Toast.makeText(this,"contacto no encontrado",Toast.LENGTH_LONG).show();
            editname.setText(nombre+"no encontrado");
        }*/
        if(datos.length()!=0){
            //Declaro el vector para poder extraer los datos y luego desplazarlos en pantalla
            String[] vect = datos.split(";");
            editdireccion.setText(vect[0]);
            editcorreo.setText(vect[1]);
            editPhone.setText(vect[2]);
            Toast.makeText(this,"contacto encontrado",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"no hay contacto buscado",Toast.LENGTH_LONG).show();
        }

    }
}