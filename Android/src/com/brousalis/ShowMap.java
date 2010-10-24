package com.brousalis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class ShowMap extends MapActivity {
	
	// SharedPreference Strings
	public static final String SAVED_MAP_STATE = "SavedMapState";
	public static final String SAVED_MAP_LAT = "SavedMapLat";
	public static final String SAVED_MAP_LONG = "SavedMapLong";
	public static final String SAVED_MAP_ZOOM = "SavedMapZoom";
	public static final String SAVED_DEFAULT_ZOOM = "DefaultZoom";
	public static final String SAVED_ZOOM_ON_CENTER = "ZoomOnCenter";
	
	// Default Values
	// DEFAULT_MAP_ZOOM moved to prefs - 9/29/10 estokes
	// It is still needed here, see CSN01 (CSN = Code Side Note)
	public static final int DEFAULT_MAP_ZOOM = 17;
	// TODO: Figure out some good value to init these to
	public static final int DEFAULT_MAP_LAT = 0;
	public static final int DEFAULT_MAP_LONG = 0;
	
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
	 * MapView which will display trails to users
	 */
	private MapView mapView;
	
	/**
	 * The settings object where both custom settings are stored
	 * as well as settings set in the options dialog
	 */
	private SharedPreferences _settings;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        MobileTrailsApp.ActivityRunning = true;
        setContentView(R.layout.main);
        mapView = (MapView) findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);

		mapController = mapView.getController();
		
		locMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
		
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
    	// Always save the location, regardless if the user wants to go here
		SharedPreferences.Editor editor = _settings.edit();
		editor.putInt(SAVED_MAP_LAT, mapView.getMapCenter().getLatitudeE6());
		editor.putInt(SAVED_MAP_LONG, mapView.getMapCenter().getLongitudeE6());
		editor.putInt(SAVED_MAP_ZOOM, mapView.getZoomLevel());
		editor.commit();
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
    	// Get the GPS Location if it's available, otherwise use the network Location.
		// By doing this, we don't force users to open up the GPS on program start.
    	// We'll also load here rather than oncreate to ensure settings are loaded.
    	initLocation();
    	Log.w("MTM", "MTM: onStart()");
    }
    
    /**
     * This method MUST be here.  Google's TOS requires it.
     * Still not sure if we have to turn this to true if we're
     * displaying custom trail routes...
     */
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
		case R.id.menu_settings:
			Intent settings = new Intent(this, TrailPrefs.class);
			startActivity(settings);
			break;
		case R.id.menu_center:
			centerMapOnCurrentLocation(_settings.getBoolean(SAVED_ZOOM_ON_CENTER, true));
			break;
		}
		return true;
	}
	/**
	 * Initialize the Location.  This happens when the app starts from cold or if
	 * the user does not have save location
	 */
	private void initLocation() {
		GeoPoint p;
		_settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		if(_settings.getBoolean(SAVED_MAP_STATE, false)) {
			p = new GeoPoint(_settings.getInt(SAVED_MAP_LAT, DEFAULT_MAP_LAT), _settings.getInt(SAVED_MAP_LONG, DEFAULT_MAP_LONG));
			mapController.animateTo(p);
			// Zoom to the Saved Map zoom.  If none is present, get the Saved Default zoom from the prefs.  
			// If that's not there, Use the DefaultMapZoom.  That last one should NEVER happen
			mapController.setZoom(_settings.getInt(SAVED_MAP_ZOOM, Integer.parseInt(_settings.getString(SAVED_DEFAULT_ZOOM, DEFAULT_MAP_ZOOM + ""))));
			mapView.setSatellite(true);
			mapView.invalidate();
		}
		else {
			centerMapOnCurrentLocation(false);
		}
	}
	/**
	 * Centers the map on the current location (GPS, Cell or WiFi, whichever is more accurate and present)
	 * @param zoomOnCenter Should the zoom level be changed to what is defined in prefs
	 */
	private void centerMapOnCurrentLocation(boolean zoomOnCenter) {
		GeoPoint p;
		Location networkLoc = locMgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		Location gpsLoc = locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		Location recentLoc = (gpsLoc != null)?gpsLoc:networkLoc;
		
		// CSN01
		// We can't just pull this from prefs, as this function is shared
		// It's sometimes called by the initializer, not the "center me" button.
		if(zoomOnCenter) {
			mapController.setZoom(Integer.parseInt(_settings.getString(SAVED_DEFAULT_ZOOM, DEFAULT_MAP_ZOOM + "")));
		}
		
		if(recentLoc != null) {
			p = new GeoPoint((int) (recentLoc.getLatitude() * 1E6), (int) (recentLoc.getLongitude() * 1E6));
			mapController.animateTo(p);
			mapView.setSatellite(true);
			mapView.invalidate();
		}
		else {
			
		}
	}
}