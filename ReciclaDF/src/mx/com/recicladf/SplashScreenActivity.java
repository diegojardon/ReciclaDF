package mx.com.recicladf;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class SplashScreenActivity extends Activity {

	private long splashDelay = 5000; //6 segundos
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		TimerTask task = new TimerTask() {
		@Override
	    public void run() {
			Intent mainIntent = new Intent().setClass(SplashScreenActivity.this, MapaActivity.class);
	        startActivity(mainIntent);
	        finish();//Destruimos esta activity para prevenir que el usuario retorne aqui presionando el boton Atras.
	      }
	    };
	
	    Timer timer = new Timer();
	    timer.schedule(task, splashDelay);//Pasado los 5 segundos dispara la tarea
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

}
