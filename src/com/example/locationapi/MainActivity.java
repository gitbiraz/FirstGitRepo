package com.example.locationapi;

import java.util.List;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {

	
	private TextView txt;
	private LocationManager locM;
	private String provider;
	private String bestprovider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txt = (TextView)findViewById(R.id.tV1);
		locM = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		//provider = locM.NETWORK_PROVIDER;
		
		List <String> providers = locM.getAllProviders();
		for(String provider : providers){
			txt.append("\nprovider: " + provider);
		}
		
		Criteria cri = new Criteria();
		cri.setAccuracy(Criteria.ACCURACY_FINE);
		cri.setPowerRequirement(Criteria.POWER_MEDIUM);
		cri.setCostAllowed(true);
		
		bestprovider = locM.getBestProvider(cri, true);
		txt.append("\nBest : " + bestprovider);
		
		Location location = locM.getLastKnownLocation(bestprovider);
		if(location != null){
			txt.append("\nlocation not null");
		}
		else
		{
			
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		locM.requestLocationUpdates(bestprovider, 16, 7, this);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		locM.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "location changed "+location.getLatitude()+"---"+location.getLongitude(), Toast.LENGTH_LONG).show();
		txt.append("location changed "+location.getLatitude()+"---"+location.getLongitude());
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

}
