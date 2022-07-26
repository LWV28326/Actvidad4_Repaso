package fp.dam.actvidad4_repaso;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Calendar;

//Conexion a la base de datos, guardar los datos de un cliente

// Unidad 2, 3, 4, 5, 6, 7 (VIP), 11
// Unidad 8 nada -- no incluido en el examen, 9 y 10 (tampoco) y 12

//------------------------------------------------------------------------------

//Quedaría abrir otra activitiy con los datos de la tarjeta de crédito
//se validen adecuadamente
//Al guardar se tendrá en cuenta si se han metido datos de la tarjeta de cédito
// Admitir valor null sino se introducen datos de la tarjeta

//------------------------------------------------------------------------------

//Hay en la carpeta recursos cosas para la validacion del nif

//-------------------------------------------------------------------

//Mirar cico vida aplicacion


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener { //interface implemetnar para responder al evento de sellecionar fecha

    private EditText fecha;
    private MiSQLiteOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //db= new MiSQLiteOpenHelper(this);
        db= MiSQLiteOpenHelper.getInstance(this,3);
        (fecha=findViewById(R.id.editTextFecha)).setOnClickListener(this::onClick);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close(); //CERRAR BASE DE DATOS
    }

    public void onClick (View v){
        switch (v.getId()){
            case R.id.editTextFecha:
                obtenerFecha();
                break;
            case R.id.imageButtonTarjeta:
                obtenerDatosTarjeta();
                break;
            case R.id.imageButtonDatos: //GUARDA LOS DATOS
                guardar();
                break;

        }

    }



    private void obtenerFecha(){
        Calendar c = Calendar.getInstance(); // Obtenemos la fecha actual, no se instancia
        DatePickerDialog dialog = new DatePickerDialog(this, this,c.get(Calendar.YEAR), c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void obtenerDatosTarjeta(){

    }

    private void guardar(){
        //Abrir la base de datos aquí
        boolean mujer= ((RadioButton)findViewById(R.id.radioButton1)).isSelected();
        db.guardar(
                ((EditText) findViewById(R.id.editTextNIF)).getText().toString(),
                ((EditText) findViewById(R.id.editTextNombre)).getText().toString(),
                ((EditText) findViewById(R.id.editTextFecha)).getText().toString(),
                ((CheckBox) findViewById(R.id.checkBox)).isChecked(),
                mujer ? "mujer" : "hombre"
        );
        //Puedo cerrarla aquí
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        fecha.setText(String.format("%04d/%02d/%02d",year, month, dayOfMonth)); //Cadenas de formato --- format string -- buscar info
    }


}