package mx.com.recicladf;

import java.util.ArrayList;

import mx.com.recicladf.model.Centros_SQLHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;

public class SplashScreenActivity extends Activity{

	public final static String EXTRA_CENTROS = "mx.com.recicladf.MESSAGE_CENTROS";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		Centros_SQLHelper helper = new Centros_SQLHelper(this, "DBCentros", null, 1);
		final SQLiteDatabase db = helper.getReadableDatabase();
        cargaDatos(db);
	}
	
	public void cargaDatos(SQLiteDatabase pasa){
		 //Abrimos la base de datos 'DBCentros' en modo lectura, si no existe la crea mandando
		 //a llamar el método onCreate de Centros_SQLHelper

		final SQLiteDatabase db = pasa;
		
		Thread hilo = new Thread(){
			public void run(){
				try {
					
					sleep(2000);
					
					if(db != null){
						
						ArrayList<String> centros = new ArrayList<String>();
						
						Cursor c = db.rawQuery("SELECT id, latitud, longitud FROM CentroReciclaje", null);
						
						if(!c.moveToFirst()){
							//Insercion de prueba
							db.execSQL("INSERT INTO CentroReciclaje (id, latitud, longitud) " +
				                    "VALUES (null, 19.485042, -99.208313)");
							db.execSQL("INSERT INTO CentroReciclaje (id, latitud, longitud) " +
				                    "VALUES (null, 19.347752, -99.019486)");
							db.execSQL("INSERT INTO CentroReciclaje (id, latitud, longitud) " +
				                    "VALUES (null, 19.443284, -99.131409)");
							db.execSQL("INSERT INTO CentroReciclaje (id, latitud, longitud) " +
				                    "VALUES (null, 19.501629, -99.147333)");
							db.execSQL("INSERT INTO CentroReciclaje (id, latitud, longitud) " +
				                    "VALUES (null, 19.320539, -99.240974)");
							c = db.rawQuery("SELECT id, latitud, longitud FROM CentroReciclaje", null);
						}
						
						if (c.moveToFirst()) {
							do{
								centros.add(c.getInt(0) + "," + c.getDouble(1) + "," + c.getDouble(2));
							}while(c.moveToNext());
						}
						db.close();
						
					    Intent menuPrincipal = new Intent(SplashScreenActivity.this, MapaActivity.class);
				        menuPrincipal.putStringArrayListExtra(EXTRA_CENTROS, centros);
				        startActivity(menuPrincipal);
				        finish();//Destruimos esta activity para prevenir que el usuario retorne aqui presionando el boton Atras.
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		hilo.start();
		
		//modo escritura
        //SQLiteDatabase db = helper.getWritableDatabase();
		
        //new CargaDatos().execute(db);
   	}

	/*public void onResultsSucceeded(ArrayList<String> result) {

		Toast toast1 = Toast.makeText(getApplicationContext(), "Latitud Splash: " + result.get(0), Toast.LENGTH_SHORT);
	    toast1.show();
		
		//Intent menuPrincipal = new Intent(SplashScreenActivity.this, MapaActivity.class);
		//Bundle bundle = new Bundle();
        //bundle.putSerializable("EXTRA_CENTROS", centros);
        //menuPrincipal.putExtras(bundle);
        /*menuPrincipal.putStringArrayListExtra("EXTRA_CENTROS", result);
        startActivity(menuPrincipal);
        finish();//Destruimos esta activity para prevenir que el usuario retorne aqui presionando el boton Atras.
		
	}*/
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}
	
	
	/*public class CargaDatos extends AsyncTask <SQLiteDatabase, Void, ArrayList<String>>{

		ResultsListener listener;

	    public void setOnResultsListener(ResultsListener listener) {
	        this.listener = listener;
	    }
		
		
		@Override
		protected ArrayList<String> doInBackground(SQLiteDatabase... params) {
			//Código de la tarea Asíncrona
			SQLiteDatabase base = params[0];
			ArrayList<String> res = new ArrayList<String>();
			if(base != null){
				
				//Insercion de prueba
				base.execSQL("INSERT INTO CentroReciclaje (id, latitud, longitud) " +
                        "VALUES (null, 1.00, 1.00)");
				base.execSQL("INSERT INTO CentroReciclaje (id, latitud, longitud) " +
                        "VALUES (null, 2.00, 2.00)");
				base.execSQL("INSERT INTO CentroReciclaje (id, latitud, longitud) " +
                        "VALUES (null, 3.00, 3.00)");
				base.execSQL("INSERT INTO CentroReciclaje (id, latitud, longitud) " +
                        "VALUES (null, 4.00, 4.00)");
				base.execSQL("INSERT INTO CentroReciclaje (id, latitud, longitud) " +
                        "VALUES (null, 5.00, 5.00)");
				
				Cursor c = base.rawQuery("SELECT id, latitud, longitud FROM CentroReciclaje", null);
				if (c.moveToFirst()) {
					do{
						res.add(c.getInt(0) + "," + c.getDouble(1) + "," + c.getDouble(2));
					}while(c.moveToNext());
				}
				base.close();
			}	
			return res;
		}
		
		@Override
		protected void onPostExecute(ArrayList<String> cent){
			Toast toast1 = Toast.makeText(getApplicationContext(), "Latitud Splash: " + cent.get(0), Toast.LENGTH_SHORT);
		    toast1.show();
	        
	        
	        Intent menuPrincipal = new Intent(SplashScreenActivity.this, MapaActivity.class);
	        //Bundle bundle = new Bundle();
	        //bundle.putSerializable("EXTRA_CENTROS", centros);
	        //menuPrincipal.putExtras(bundle);
	        menuPrincipal.putStringArrayListExtra("EXTRA_CENTROS", cent);
	        startActivity(menuPrincipal);
			
		}
		
	}*/
}
