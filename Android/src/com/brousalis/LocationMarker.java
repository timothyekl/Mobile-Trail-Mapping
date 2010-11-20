package com.brousalis;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class LocationMarker extends Overlay{
	
	private GeoPoint _point;
	private Drawable _icon;
	private Point _screenPts;
	
	public LocationMarker(GeoPoint p, Drawable d) {
		_point = p;
		_icon = d;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// TODO Auto-generated method stub
		super.draw(canvas, mapView, shadow);

        _screenPts = new Point();
        mapView.getProjection().toPixels(_point, _screenPts);

		//canvas.drawCircle(_screenPts.x, _screenPts.y, 5, color );
		//_icon.draw(canvas);
		Rect bounds = _icon.getBounds();
		
		bounds.set(_screenPts.x - bounds.width()/2, _screenPts.y - bounds.width()/2, _screenPts.x + bounds.width()/2, _screenPts.y + bounds.width()/2);
		//_icon.setBounds(bounds)
		_icon.draw(canvas);
	}

	public void setLocation(Location l) {
		_point = new GeoPoint((int) (l.getLatitude() * 1E6), (int) (l.getLongitude() * 1E6));
	}
}
