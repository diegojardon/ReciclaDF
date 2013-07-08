package mx.com.recicladf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapaActivity extends Activity implements OnMapClickListener, OnMarkerDragListener,
													  LocationListener, OnInfoWindowClickListener,
													  OnMarkerClickListener{

	//static final LatLng PLAZA_CARSO = new LatLng(19.44207,-99.203628);
	
	//definicion de constante para el Intent
	public final static String EXTRA_DIRECCION = "mx.com.recicladf.MESSAGE_DIRECCION";
	public final static String EXTRA_COLONIA = "mx.com.recicladf.MESSAGE_COLONIA";
	//public final static String EXTRA_MARCADOR = "mx.com.recicladf.MESSAGE_MARCADOR";
	
	private GoogleMap map;
	private Geocoder geoCoder;
	
	String id;
	String direccionText = "";
	String colonia = "";
	
	String seleccionado = "m0";
	LatLng posicionSeleccionado;
	LatLng miUbicacion;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        
        //map.setMapType(map.MAP_TYPE_NORMAL);
        
        //map.setMyLocationEnabled(true);
        map.setOnMapClickListener(this);
    	map.setOnMarkerDragListener(this);
    	map.setOnInfoWindowClickListener(this);
        
    	
        
        //Validamos si hubo problemas al obtener el mapa
        if (map != null) {

        	
        	geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
        	
        		
        	LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        	Criteria criteria = new Criteria();
        	String provider = service.getBestProvider(criteria, false);
        	Location location = service.getLastKnownLocation(provider);
        	
        	if(location!=null){
                onLocationChanged(location);
            }
        	//Solicitar actualización de la ubicación cada 6 horas y 0 metros
            service.requestLocationUpdates(provider,6*60*60*1000, 0, this);
        	
        	miUbicacion = new LatLng(location.getLatitude(),location.getLongitude());
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
       Marker ultimo = map.addMarker(new MarkerOptions()
       	.position(punto)
       	.title("Centro de Reciclaje")
        .snippet("Datos Básicos")
       	.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        .draggable(true));
       	seleccionado = ultimo.getId();
		posicionSeleccionado = ultimo.getPosition();
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
		//Toast toast1 = Toast.makeText(getApplicationContext(), "Detalle del centro de Reciclaje " + arg0.getId(), Toast.LENGTH_LONG);
		/*Toast toast1 = Toast.makeText(getApplicationContext(), "Direccion: " + direccionText, Toast.LENGTH_SHORT);
	    toast1.show();*/
		 
		// Elemento list que contendra la direccion
		  List<Address> direcciones = null;
		
		  // Funcion para obtener el nombre desde el geocoder
		  try {
		   direcciones = geoCoder.getFromLocation(arg0.getPosition().latitude, arg0.getPosition().longitude,1);
		  } catch (Exception e) {
			  
			  Toast toast1 = Toast.makeText(getApplicationContext(), "Error: " + e.toString(), Toast.LENGTH_SHORT);
			    toast1.show();
			  
		  }
		  
		  // Funcion que determina si se obtuvo resultado o no
		  if(direcciones != null && direcciones.size() > 0 ){
			  // Creamos el objeto address
			  Address direccion = direcciones.get(0);
			  // Creamos el string a partir del elemento direccion
			  
			  if(direccion.getMaxAddressLineIndex() > 0){
				  direccionText = direccion.getAddressLine(0);
				  colonia = direccion.getSubLocality();
			  }
		  }
		
		  Intent intent = new Intent(this, AgregarCentroActivity.class);
			intent.putExtra(EXTRA_DIRECCION, direccionText);
			intent.putExtra(EXTRA_COLONIA, colonia);
			startActivity(intent);
		 
		  
	}
	
	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		seleccionado = arg0.getId();
		posicionSeleccionado = arg0.getPosition();
		return true;
	}
	
	
	public void abrirFormulario(View view) {
	    //Hacer algo en respuesta al click del botón
		Intent intentComentarios = new Intent(this, ComentariosActivity.class);
		startActivity(intentComentarios);
	}
	
	public void trazaRuta(){
		//Método que traza la ruta de la ubicación actual al último marcador seleccionado
		PolylineOptions ruta=new PolylineOptions();
		if(!seleccionado.equals("m0")){
			/*Toast toast1 = Toast.makeText(getApplicationContext(),"http://maps.googleapis.com/maps/api/directions/json?origin=" 
					+miUbicacion.latitude 
					+ "," 
					+ miUbicacion.longitude
					+"&destination="
					+posicionSeleccionado.latitude
					+","
					+posicionSeleccionado.longitude
					+"&sensor=true", Toast.LENGTH_LONG);
		    toast1.show();*/
			
			try{
				String rutaCadena = new Ruta().execute("http://maps.googleapis.com/maps/api/directions/json?origin="
						+ miUbicacion.latitude 
						+ "," 
						+ miUbicacion.longitude
						+"&destination="
						+posicionSeleccionado.latitude
						+","
						+posicionSeleccionado.longitude
						+"&sensor=true").get();
				/*Toast toast2 = Toast.makeText(getApplicationContext(), "Cadena: "+rutaCadena, Toast.LENGTH_SHORT);
			    toast2.show();*/
				ArrayList<LatLng> puntos = decodePoly(rutaCadena);
				for(int i=0;i<puntos.size();i++){
					ruta.add(new LatLng(puntos.get(i).latitude, puntos.get(i).longitude));
				}
				ruta.color(Color.BLUE).width(7);
				ruta.zIndex(10f);
				map.addPolyline(ruta);
				
			}catch(ExecutionException ee){
			}catch(InterruptedException ie){}
		}else{
			Toast toast1 = Toast.makeText(getApplicationContext(), "Debes seleccionar un centro de reciclaje", Toast.LENGTH_SHORT);
		    toast1.show();
		}
		/*Toast toast1 = Toast.makeText(getApplicationContext(), "Ultimo: "+seleccionado, Toast.LENGTH_SHORT);
	    toast1.show();*/
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.calcularRuta:
	            trazaRuta();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private ArrayList<LatLng> decodePoly(String encoded) {

	    //Log.i("Location", "String received: "+encoded);
	    ArrayList<LatLng> puntos = new ArrayList<LatLng>();
	    int index = 0, len = encoded.length();
	    int lat = 0, lng = 0;

	    while (index < len) {
	        int b, shift = 0, result = 0;
	        do {
	            b = encoded.charAt(index++) - 63;
	            result |= (b & 0x1f) << shift;
	            shift += 5;
	        } while (b >= 0x20);
	        int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	        lat += dlat;

	        shift = 0;
	        result = 0;
	        do {
	            b = encoded.charAt(index++) - 63;
	            result |= (b & 0x1f) << shift;
	            shift += 5;
	        } while (b >= 0x20);
	        int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	        lng += dlng;

	        LatLng p = new LatLng((((double) lat / 1E5)),(((double) lng / 1E5)));
	        puntos.add(p);
	    }

	   for(int i=0;i<puntos.size();i++){
	       //Log.i("Location", "Point sent: Latitude: "+poly.get(i).latitude+" Longitude: "+poly.get(i).longitude);
	   }
	    return puntos;
	}
	
	
	
	public class Ruta extends AsyncTask<String, String, String> {

		//private final ProgressDialog dialog = new ProgressDialog(getBaseContext());
		String ruta = "";
		
		/*@Override
		protected void onPreExecute() {
		    super.onPreExecute();
		    dialog.setMessage("Calculando ruta...");
		    dialog.show();
		}*/
		
		
		@Override
		protected String doInBackground(String... params) {
			StringBuilder url = new StringBuilder(params[0]);
		    HttpGet geturl = new HttpGet(url.toString());
		    HttpClient client = new DefaultHttpClient();
		    HttpResponse response;
		    try {
		        response = client.execute(geturl);
		        int status = response.getStatusLine().getStatusCode();
		        if (status == 200) {
		            HttpEntity entity = response.getEntity();
		            String data = EntityUtils.toString(entity);
		            JSONObject item = new JSONObject(data);
		            ruta = parseJson(item);
		            return ruta;
		        }
		    // Catches any errors from the url or JSONObject
		    }catch (ClientProtocolException clientExcep){
		        clientExcep.printStackTrace();
		    }catch (IOException ioExcep){
		        ioExcep.printStackTrace();
		    }catch (JSONException jsonExcep){
		        jsonExcep.printStackTrace();
		    }
			return null;
		}
		
		public String parseJson(JSONObject item) throws JSONException {
			JSONArray jsonArray;
			String ruta = "";
		    if(item != null) {
		        jsonArray = item.getJSONArray("routes");
		        JSONObject polilinea = jsonArray.getJSONObject(0);
		        ruta= polilinea.getJSONObject("overview_polyline").getString("points");
		    }
		    return ruta;
		}
		
		/*protected void onPostExecute(List<String> posts) {
		    dialog.dismiss();
		}*/
	}
}
