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
	private int _locationReportsToGo = -1;
	private Context _context;
	public CurrentLocation(LocationMarker m, MapView mapView) {
		_currentLoc = m;
		_mapView = mapView;
		_mapView.getOverlays().remove(_currentLoc);
		Log.w("MTM", "MTM: Location Initialized");
		_mapView.getOverlays().add(_currentLoc);
		_mapView.invalidate();
	}
	public CurrentLocation(Context context, LocationMarker m, MapView mapView, int timesToReportLocation) {
		this(m, mapView);
		_context = context;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		if(_locationReportsToGo > 0 && _context != null) {
			_locationReportsToGo--;
			
		}
		if(_locationReportsToGo == 0) {
			Log.w("MTM","MTM: Done Reporting");
			((ShowMap)_context).turnOffLocationUpdates();
		}
		Log.w("MTM", "MTM: Location Changed");
		_mapView.getOverlays().remove(_currentLoc);
		_currentLoc.setLocation(location);
		_mapView.getOverlays().add(_currentLoc);
		_mapView.invalidate();
	}
	
	public void setLocationDot(GeoPoint p) {
		Log.w("MTM", "MTM: Location Set");
		_mapView.getOverlays().remove(_currentLoc);
		_currentLoc.setLocation(p);
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
