package mx.com.recicladf.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Centros_SQLHelper extends SQLiteOpenHelper {

	//Sentencia SQL para crear la tabla de Usuarios
	
	//PRODUCTIVA
    /*String sqlCreate = "CREATE TABLE CentroReciclaje (id INTEGER PRIMARY KEY, " +
    												 "nombre TEXT," +
    												 "delegacion TEXT," +
    												 "horario TEXT,"+
    												 "telefono TEXT,"+
    												 "correo TEXT,"+
    												 "papel INTEGER,"+
    												 "plastico INTEGER,"+
    												 "bateria INTEGER,"+
    												 "metal INTEGER,"+
    												 "vidrio INTEGER,"+
    												 "latitud REAL,"+
    												 "longitud REAL)";*/
	
	//PRUEBAS
	String sqlCreate = "CREATE TABLE CentroReciclaje (id INTEGER PRIMARY KEY, " +
			 "latitud REAL,"+
			 "longitud REAL)";
	
    public Centros_SQLHelper(Context contexto, String nombre, CursorFactory factory, int version) {
    	super(contexto, nombre, factory, version);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creaci�n de la tabla
        db.execSQL(sqlCreate);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //Por simplicidad del ejemplo aqu� utilizamos directamente la opci�n de
        //eliminar la tabla anterior y crearla de nuevo vac�a con el nuevo formato.
        //Sin embargo lo normal ser� que haya que migrar datos de la tabla antigua
        //a la nueva, por lo que este m�todo deber�a ser m�s elaborado.
 
        //Se elimina la versi�n anterior de la tabla
        //db.execSQL("DROP TABLE IF EXISTS Usuarios");
 
        //Se crea la nueva versi�n de la tabla
        //db.execSQL(sqlCreate);
    }
}
