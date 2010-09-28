package com.brousalis;

import com.google.android.maps.*;

// Needed for A standard Android Activity
//import android.app.Activity;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ShowMap extends MapActivity {
	public static MapActivity currentlyVisible;
	/**
	 * The Standard Location manager for an Android Device
	 */
	private LocationManager locMgr;
	
	/**
	 * The Map controller that allows us to retrieve or send
	 * information to our mapView
	 */
	private MapController mapController;
	
	/**
	 * Mapview which will display trails to users
	 */
	private MapView mapView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileTrailsApp.ActivityRunning = true;
        setContentView(R.layout.main);
        mapView = (MapView) findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);

		mapController = mapView.getController();
		
		locMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
		

		// Get the GPS Location if it's available, otherwise use the network Location.
		// By doing this, we don't force users to open up the GPS on program start.
		initLocation();
		
		Log.w("MTM", "MTM: onCreate()");
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
      setContentView(R.layout.main);
    }

    @Override
    public void onPause() {
    	super.onPause();
    	Log.w("MTM", "MTM: onPause()");
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    	Log.w("MTM", "MTM: onStop()");
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	MobileTrailsApp.ActivityRunning = false;
    	Log.w("MTM", "MTM: onDestroy()");
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	Log.w("MTM", "MTM: onResume()");
    }
    
    @Override
    public void onRestart() {
    	super.onRestart();
    	Log.w("MTM", "MTM: onRestart()");
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	Log.w("MTM", "MTM: onStart()");
    }
    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.mainmenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.menu_quit:
			this.finish();
			break;
		}
		return true;
	}
	/**
	 * Initialize the Location.  This happens when the app starts from cold or if
	 * the user does not have save location on (not yet implemented)
	 */
	private void initLocation() {
		Location networkLoc = locMgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		Location gpsLoc = locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		Location recentLoc = (gpsLoc != null)?gpsLoc:networkLoc;
		
		if(recentLoc != null)
		{
			GeoPoint p = new GeoPoint((int) (recentLoc.getLatitude() * 1E6),
				(int) (recentLoc.getLongitude() * 1E6));
			mapController.animateTo(p);
			mapController.setZoom(17);
			mapView.setSatellite(true);
			mapView.invalidate();
		}
	}
}