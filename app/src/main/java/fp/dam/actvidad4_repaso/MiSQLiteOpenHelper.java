package fp.dam.actvidad4_repaso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class MiSQLiteOpenHelper extends SQLiteOpenHelper {


    public MiSQLiteOpenHelper(Context context) {
        super(context, "clientesdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //db es la base de datos
        db.execSQL("create table clientes(" +
                "nif char (9) primary key,"+
                "nombre varchar(50) not null,"+
                "fecha date not null,"+
                "estudiante char(1),"+
                "sexo char(6)"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void guardar (String nombre, String nif, String fecha, boolean estudiante, String sexo){
        String sentencia = "insert into clientes values("+
                "'"+nif+"',"+
                "'"+nombre+"',"+
                "'"+fecha+"',"+
                "'"+(estudiante ? 's':'n')+"',"+
                "'" +sexo+"')";
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL(sentencia);
    }
}
