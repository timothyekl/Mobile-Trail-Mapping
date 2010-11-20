package com.brousalis;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class CurrentLocation implements LocationListener{

	private MapView _mapView;
	private LocationMarker _currentLoc;
	public CurrentLocation(MapView mapView, int id, Context context) {
		_mapView = mapView;
		_currentLoc = new LocationMarker(new GeoPoint(0, 0), id, context);
	}
	@Override
	public void onLocationChanged(Location location) {
		//_mapView.getOverlays().remove(_currentLoc);
		Log.w("MTM", "MTM: Location Changed");
		_currentLoc.setLocation(location);
		_mapView.getOverlays().add(_currentLoc);
		_mapView.invalidate();
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
