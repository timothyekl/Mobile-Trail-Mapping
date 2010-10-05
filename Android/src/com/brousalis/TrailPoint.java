package com.brousalis;

import java.util.Set;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;


public class TrailPoint extends InterestPoint {
	
	private Trail _trail;
	private Set<TrailPoint> _connections;
	
	
	public TrailPoint(int id, int latitude, int longitude, String category, String summary, String title, Set<TrailPoint> connections) {
		super(id, new GeoPoint(latitude, longitude), category, title, summary);
		_connections = connections;
	}
	
	public TrailPoint(int id, GeoPoint p, String category, String summary, String title, Set<TrailPoint> connections) {
		super(id, p, category, title, summary);
		_connections = connections;
	}
	public TrailPoint(int id, GeoPoint p, Set<TrailPoint> connections) {
		super(id, p, "", "", "");
		_connections = connections;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// TODO Auto-generated method stub
		super.draw(canvas, mapView, shadow);
		Point screenPts = new Point();
        mapView.getProjection().toPixels(getLocation(), screenPts);

		canvas.drawCircle(screenPts.x, screenPts.y, 5, getColor());
		
		for(TrailPoint p : _connections) {
			Point newPt = new Point();
			mapView.getProjection().toPixels(p.getLocation(), newPt);
			Paint newP = new Paint();
			newP.setARGB(255, 255, 0, 0);
			newP.setStrokeWidth(3);
			newP.setFlags(Paint.ANTI_ALIAS_FLAG);
			canvas.drawLine(screenPts.x, screenPts.y, newPt.x, newPt.y, newP);
		}
	}

	public void setTrail(Trail trail) {
		_trail = trail;
	}
	public Trail getTrail() {
		return _trail;
	}
	
	/**
	 * Set the connections set to a new set
	 * @param connections The new connection set
	 */
	public void setConnections(Set<TrailPoint> connections) {
		_connections = connections;
	}
	/**
	 * Gets the connections 
	 * @return The current Set of connections
	 */
	public Set<TrailPoint> getConnections() {
		return _connections;
	}
	/**
	 * Attempts to add a new connection to this point's connections
	 * @param connection The TrailPoint to add a connection to
	 * @return True if a connection was added, False if it already existed
	 */
	public boolean addConnection(TrailPoint connection) {
		return (connection == null) ? false :_connections.add(connection);
	}
	/**
	 * Attempts to remove a connection from this point's connections
	 * @param connection The ID of a connection to delete
	 * @return True if a connection was removed, False if it did not exist
	 */
	public boolean removeConnection(TrailPoint connection) {
		return (connection == null) ? false :_connections.remove(connection.getID());
	}
	
	/**
	 * Checks to see if this TrailPoint has a connection to the passed in TrailPoint
	 * @param connection The TrailPoint to check the Set against
	 * @return True if the connection's ID is in the Set, False if it is not
	 */
	public boolean hasConnection(TrailPoint connection) {
		return (connection == null) ? false :_connections.contains(connection.getID());
	}
//	private TrailPoint _recentPoint;
//	@Override
//	public boolean onTap(GeoPoint p, MapView mapView) {
//		TrailPoint added = new TrailPoint(0, this.getTrail(), p, new HashSet<TrailPoint>());
//		this.getTrail().addPoint(added, _recentPoint);
//		_recentPoint = added;
//		Log.w("MTM","MTM: Added Point!");
//		return super.onTap(p, mapView);
//	}
	
}
