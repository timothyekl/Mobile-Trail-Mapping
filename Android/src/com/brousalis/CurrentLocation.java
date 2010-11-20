package com.brousalis;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class CurrentLocation implements LocationListener{

	private MapView _mapView;
	private LocationMarker _currentLoc;
	public CurrentLocation(MapView mapView, Drawable d) {
		_mapView = mapView;
		_currentLoc = new LocationMarker(new GeoPoint(0, 0), d);
	}
	@Override
	public void onLocationChanged(Location location) {
		_mapView.getOverlays().remove(_currentLoc);
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
