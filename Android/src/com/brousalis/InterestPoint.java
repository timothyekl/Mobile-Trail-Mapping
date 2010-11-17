package com.brousalis;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Location;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class InterestPoint extends Overlay {
	
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
		//super(p, title, summary);
		this._ID = id;
		this._location = p;
		this._category = category;
		this._summary = summary;
		this._title = title;
		this._color = new Paint();
		this._color.setARGB(255, 0, 0, 255);
		this._color.setAntiAlias(true);
	}
	
	/**
	 * Draws this InterestPoint on the screen.
	 */
	
    public void draw(Canvas canvas, MapView mapView, boolean shadow) 
    {
        super.draw(canvas, mapView, shadow);                
        Point screenPts = new Point();
        mapView.getProjection().toPixels(this._location, screenPts);
		canvas.drawCircle(screenPts.x, screenPts.y, 5, _color );
    }
	
	public void setID(int id) {
		this._ID = id;
	}
	public int getID() {
		return this._ID;
	}
	
	/**
	 * Sets this InterestPoint's Location to a specialized GeoPoint.
	 * @param latitude The latitude of this InterestPoint.
	 * @param longitude The longitude of this InterestPoint.
	 */
	public void setLocation(int latitude, int longitude) {
		this._location = new GeoPoint(latitude, longitude);
	}
	
	/**
	 * Returns the location as a GeoPoint.
	 * @return A GeoPoint of the Location.
	 */
	public GeoPoint getLocation() {
		return this._location;
	}
	
	public void setCategory(String category) {
		this._category = category;
	}
	public String getCategory() {
		return this._category;
	}
	
	public void setSummary(String summary) {
		this._summary = summary;
	}
	public String getSummary() {
		return this._summary;
	}

	public void setTitle(String title) {
		this._title = title;
	}
	public String getTitle() {
		return this._title;
	}
	
	public void setColor(int A, int R, int G, int B) {
		this._color.setARGB(A, R, G, B);
	}
	public Paint getColor() {
		return this._color;
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
	 * @param check The point to check the distance to.
	 * @return The distance from this point to check.
	 */
	public double distanceTo(InterestPoint check) {
		Location start = new Location(this.convertGeoPointToLocation(this._location));
		Location end = new Location(this.convertGeoPointToLocation(check.getLocation()));
		
		return start.distanceTo(end)/METERS_PER_MILE;
	}
	
	/**
	 * Converts a GeoPoint to a Location
	 * @param p GeoPoint to convert
	 * @return A Location with the same coordinates as the GeoPoint p
	 */
	private Location convertGeoPointToLocation(GeoPoint p) {
		Location dest = new Location("");
		dest.setLatitude(p.getLatitudeE6() / 1E6);
		dest.setLongitude(p.getLongitudeE6() / 1E6);
		return dest;
	}
}
