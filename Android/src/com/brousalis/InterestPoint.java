package com.brousalis;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;


public class InterestPoint extends Overlay implements Overlay.Snappable {
	private int _ID;
	private GeoPoint _location;
	private String _category;
	private String _title;
	private String _summary;
	private Paint _color;
	
	/**
	 * Creates a new Interest point with the required params.
	 * @param id A unique identifier of the point.
	 * @param p A GeoPoint where the point is located at
	 * @param category The unique category this point belongs to.
	 * @param title The title of the point.  Will be clickable if this is not "".
	 * @param summary A brief summary or description of the point.  This can be "".
	 */
	public InterestPoint(int id, GeoPoint p, String category, String title, String summary) {
		_ID = id;
		_location = p;
		_category = category;
		_summary = summary;
		_title = title;
		_color = new Paint();
		_color.setARGB(255, 0, 0, 255);
		_color.setAntiAlias(true);
	}
	
	@Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) 
    {
        super.draw(canvas, mapView, shadow);                   

        Point screenPts = new Point();
        mapView.getProjection().toPixels(_location, screenPts);

		canvas.drawCircle(screenPts.x, screenPts.y, 5, _color );
    }
	
	
	
	@Override
	public boolean onTap(GeoPoint p, MapView mapView) {
		Log.w("MTM", "MTM: Tap On Overlay: " + this._title);
		return super.onTap(p, mapView);
	}
	
	public void setID(int id) {
		_ID = id;
	}
	public int getID() {
		return _ID;
	}
	
	public void setLocation(int latitude, int longitude) {
		_location = new GeoPoint(latitude, longitude);
	}
	public GeoPoint getLocation() {
		return _location;
	}
	
	public void setCategory(String category) {
		_category = category;
	}
	public String getCategory() {
		return _category;
	}
	
	public void setSummary(String summary) {
		_summary = summary;
	}
	public String getSummary() {
		return _summary;
	}

	public void setTitle(String title) {
		_title = title;
	}
	public String getTitle() {
		return _title;
	}
	
	public void setColor(int A, int R, int G, int B) {
		_color.setARGB(A, R, G, B);
	}
	public Paint getColor() {
		return _color;
	}

	@Override
	public boolean onSnapToItem(int x, int y, Point snapPoint, MapView mapView) {
		return false;
	}
}
