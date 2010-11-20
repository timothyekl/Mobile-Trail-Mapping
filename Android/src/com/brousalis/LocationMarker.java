package com.brousalis;

import android.content.Context;
import android.graphics.*;
import android.location.Location;

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
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// TODO Auto-generated method stub
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
}
