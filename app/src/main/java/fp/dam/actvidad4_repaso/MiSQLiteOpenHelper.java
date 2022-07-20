package fp.dam.actvidad4_repaso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//Ejemplo --- HACERLO
//Añadimos otra tabla más a la tabal, la direccion,

public class MiSQLiteOpenHelper extends SQLiteOpenHelper {

    private static MiSQLiteOpenHelper db;


    private MiSQLiteOpenHelper(Context context) {

        super(context, "clientesdb", null, 1);
    }

    private MiSQLiteOpenHelper(Context context, int version) {

        super(context, "clientesdb", null, version);
    }
    //Context -- de la aplicacion
    //Si existe no se ejecuta el metodo create, onCreate definicdo a continuacion
    //Cuando se crea se hace con version 1
    //si ponemos otra version se ejecuta el método onUpgrade --




    //Es un patron de diseño -- singleston o algo así
    //solo se instancia una vez
    public static MiSQLiteOpenHelper getInstance(Context context){
        if(db==null){
            db=new MiSQLiteOpenHelper(context.getApplicationContext());
        }
        return db;
    }

    public static MiSQLiteOpenHelper getInstance(Context context, int version){
        if(db==null){
            db=new MiSQLiteOpenHelper(context.getApplicationContext(), version);
        }
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //db es la base de datos
        db.execSQL("create table clientes(" +
                "nif char (9) primary key,"+
                "nombre varchar(50) not null,"+
                "fecha date not null,"+
                "estudiante char(1),"+
                "estudiante integer not null,"+
                "sexo char(6)"+
                ")");

        //Crear tabla auxiliar sino se puede usar el drop y pasar los datos y modificar la otra
    }

    //Ampliar la base de datos, cambio de vesion
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion ==1 && newVersion==2){
            ///Mirar porque no funciona el drop
            //db.execSQL("alter table clientes drop column estudiante");
            //db.execSQL(("alter table clientes add column estudiante integer"));

            //Hacerlo de otra forma
            db.execSQL("drop table clientes");
            db.execSQL("create table clientes(" +
                    "nif char (9) primary key,"+
                    "nombre varchar(50) not null,"+
                    "fecha date not null,"+
                    "estudiante integer,"+
                    "sexo char(6)"+
                    ")");
        }
        //Pasa info de una activity a otra ------------------------>>> Retornar datos
        if(oldVersion ==2 && newVersion==3){
            db.execSQL("create table tarjetas(" +
                    "nif char (9) ,"+
                    "numero char(16) not null,"+
                    "mes integer not null,"+
                    "mes integer not null,"+
                    "cvc integer,"+
                    "primary Key (nif, numero),"+
                    "foreign key (nif) references clientes (nif)"+
                    ")");

        }
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true); //permite onfigurar reinstricciones -- no dijo nada de como usarlo
    }

    public void guardar (String nombre, String nif, String fecha, boolean estudiante, String sexo, String numero, String mes, String anio, String cvc){
        //Una vez que creo las sentencias hacerlo enuna transaccion
        String sentencia1 = "insert into clientes values("+
                "'"+nif+"',"+
                "'"+nombre+"',"+
                "'"+fecha+"',"+
                //"'"+(estudiante ? 's':'n')+"',"+
                (estudiante ? "1" : "2")+","+
                "'" +sexo+"')";
        String sentencia2 = "insert into tarjetas values("+
                "'"+nif+"',"+
                "'"+nombre+"',"+
                "'"+fecha+"',"+
                //"'"+(estudiante ? 's':'n')+"',"+
                (estudiante ? "1" : "2")+","+
                "'" +sexo+"')";
        SQLiteDatabase db= getWritableDatabase();
        //Primero abro la transaccion y luego la cierro -------------------->>>> Hay que mirarlo como se hace
        db.beginTransaction();
        db.execSQL(sentencia1);
        db.execSQL(sentencia2);
        db.setTransactionSuccessful();
        db.endTransaction();

    }
}
