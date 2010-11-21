package com.brousalis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Location;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class LocationMarker extends Overlay{
	
	private GeoPoint _point;
	private Bitmap _icon;
	private Point _screenPts;
	
	public LocationMarker(GeoPoint p, int id, Context context) {
		_point = p;
		BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        _icon = BitmapFactory.decodeResource(context.getResources(), id);
        Log.w("MTM", "MTM: Location Marker Initialized");
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// TODO Auto-generated method stub
		Log.w("MTM", "MTM: Drawing Dot...");
		super.draw(canvas, mapView, shadow);

        _screenPts = new Point();
        mapView.getProjection().toPixels(_point, _screenPts);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
		//canvas.drawCircle(_screenPts.x, _screenPts.y, 5, p);
		canvas.drawBitmap(_icon, _screenPts.x, _screenPts.y, null);
	}

	public void setLocation(Location l) {
		_point = new GeoPoint((int) (l.getLatitude() * 1E6), (int) (l.getLongitude() * 1E6));
	}
	
	public void setLocation(GeoPoint p) {
		_point = p;
	}
}
