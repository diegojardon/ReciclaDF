package mx.com.recicladf;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends Activity implements OnMapClickListener, OnMarkerDragListener,
													  LocationListener, OnInfoWindowClickListener{

	//static final LatLng PLAZA_CARSO = new LatLng(19.44207,-99.203628);
	
	private GoogleMap map;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        
        map.setMyLocationEnabled(true);
        map.setOnMapClickListener(this);
    	map.setOnMarkerDragListener(this);
    	map.setOnInfoWindowClickListener(this);
        
        
        //Validamos si hubo problemas al obtener el mapa
        if (map != null) {

        		
        	LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        	Criteria criteria = new Criteria();
        	String provider = service.getBestProvider(criteria, false);
        	Location location = service.getLastKnownLocation(provider);
        	
        	if(location!=null){
                onLocationChanged(location);
            }
        	//Solicitar actualización de la ubicación cada 6 horas y 0 metros
            service.requestLocationUpdates(provider,6*60*60*1000, 0, this);
        	
        	LatLng miUbicacion = new LatLng(location.getLatitude(),location.getLongitude());
        	//if (map.getMyLocation() != null){        	
        		map.animateCamera(CameraUpdateFactory.newLatLngZoom(
        				miUbicacion, 15));
        	
        		map.addMarker(new MarkerOptions()
        		.position(miUbicacion)
        		.title("Ubicación")
        		.snippet("Aqui estoy")
        		.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        		
        	/*}else{
        		Toast toast1 = Toast.makeText(getApplicationContext(), "No se pudo activar el servicio de localización", Toast.LENGTH_SHORT);
       	        toast1.show();
        	}*/
        	
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mapa, menu);
        return true;
    }
    
    
    @Override
    public void onMapClick(LatLng punto) {
       map.addMarker(new MarkerOptions().position(punto).
          icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
          .draggable(true));
    }
    
    @Override
    public void onMarkerDrag(Marker marker) {
     
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
     
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
     
     
    }
   
    @Override
    public void onLocationChanged(Location location) {

        //Obtener latitud
        double latitude = location.getLatitude();

        //Obtener longitud
        double longitude = location.getLongitude();

        //Creando un objeto LatLng para la ubicación actual
        LatLng nuevaUbicacion = new LatLng(latitude, longitude);

        //Mostrando la ubicación en Google Maps
        map.moveCamera(CameraUpdateFactory.newLatLng(nuevaUbicacion));

        //Zoom
        map.animateCamera(CameraUpdateFactory.zoomTo(15));

        map.addMarker(new MarkerOptions()
		.position(nuevaUbicacion)
		.title("Ubicación")
		.snippet("Aqui estoy")
		.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        
    }
    
    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }


	@Override
	public void onInfoWindowClick(Marker arg0) {
		//Mandar a llamar Web Service para mostrar detalle del centro
		Toast toast1 = Toast.makeText(getApplicationContext(), "Detalle del centro de Reciclaje", Toast.LENGTH_SHORT);
	    toast1.show();
	}
    
}
