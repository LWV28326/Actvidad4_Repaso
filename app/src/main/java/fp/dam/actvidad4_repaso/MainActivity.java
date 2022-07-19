package fp.dam.actvidad4_repaso;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

//Conexion a la base de datos, guardar los datos de un cliente

// Unidad 2, 3, 4, 5, 6, 7 (VIP), 11
// Unidad 8 nada -- no incluido en el examen, 9 y 10 (tampoco) y 12

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener { //interface implemetnar para responder al evento de sellecionar fecha

    private EditText fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (fecha=findViewById(R.id.editTextFecha)).setOnClickListener(this::onClick);
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

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        fecha.setText(String.format("%04d/%02d/%02d",year, month, dayOfMonth)); //Cadenas de formato --- format string -- buscar info
    }


}