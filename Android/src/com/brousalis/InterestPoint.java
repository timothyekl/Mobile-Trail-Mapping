package com.brousalis;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Location;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;


public class InterestPoint extends Overlay implements Overlay.Snappable {
	private static final double METERS_PER_MILE = 1609.344;
	
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
	
	/**
	 * Returns True if the Points are within miles radius of each other.
	 * @param check Point to check against.
	 * @param miles Miles to check against.
	 * @return True if the point is within the circle, false if it is not.
	 */
	public boolean isPointNotThisFarAway(InterestPoint check, double miles) {
		return distanceTo(check) < miles;
	}
	
	/**
	 * Returns the distance to the specified point.
	 * @param check The point to check the distance to
	 * @return The distance from this point to check
	 */
	public double distanceTo(InterestPoint check) {
		Location start = new Location(convertGeoPoint(_location));
		Location end = new Location(convertGeoPoint(check.getLocation()));
		
		return start.distanceTo(end)/METERS_PER_MILE;
	}
	
	private Location convertGeoPoint(GeoPoint p) {
		Location dest = new Location("");
		dest.setLatitude(p.getLatitudeE6() / 1E6);
		dest.setLongitude(p.getLongitudeE6() / 1E6);
		return dest;
	}
	
	@Override
	public boolean onSnapToItem(int x, int y, Point snapPoint, MapView mapView) {
		return false;
	}
}
