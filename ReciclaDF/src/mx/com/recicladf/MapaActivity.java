package mx.com.recicladf;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends Activity {

	static final LatLng PLAZA_CARSO = new LatLng(19.44207,-99.203628);
	
	private GoogleMap map;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        // Check if we were successful in obtaining the map.
        if (map != null) {
        	//The Map is verified. It is now safe to manipulate the map.
        	Marker test = map.addMarker(new MarkerOptions()
        		.position(PLAZA_CARSO)
        		.title("Plaza Carso")
        		.snippet("Lugar de Trabajo")
        		.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mapa, menu);
        return true;
    }
    
}
