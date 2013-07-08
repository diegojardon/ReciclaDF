package mx.com.recicladf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AgregarCentroActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar_centro);
		
		Intent intent = getIntent();
		
		EditText calle = (EditText)findViewById(R.id.calle);
		calle.setText(intent.getStringExtra(MapaActivity.EXTRA_DIRECCION));
		
		EditText colonia = (EditText)findViewById(R.id.colonia);
		colonia.setText(intent.getStringExtra(MapaActivity.EXTRA_COLONIA));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agregar_centro, menu);
		return true;
	}
	
	public void regresarMapa(View view){
		Intent intent = new Intent(this, MapaActivity.class);
		startActivity(intent);
	}

}
