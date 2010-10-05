package com.brousalis;

import java.util.LinkedList;
import java.util.Set;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;


public class TrailPoint extends InterestPoint {
	
	private Trail _trail;
	private Set<TrailPoint> _connections;
	private LinkedList<Integer> _unresolvableLinks;
	private boolean _hasUnresolvedLinks = false;
	
	@Override
	public String toString() {
		return "TrailPoint " + this.getID() + ": (x=" + this.getLocation().getLatitudeE6()/1E6 + ", y=" + this.getLocation().getLongitudeE6()/1E6 + ", " + this.getConnections().size() + " Cons)";
	}
	
	public TrailPoint(int id, int latitude, int longitude, String category, String summary, String title, Set<TrailPoint> connections) {
		super(id, new GeoPoint(latitude, longitude), category, title, summary);
		_connections = connections;
		_unresolvableLinks = new LinkedList<Integer>();
	}
	
	public TrailPoint(int id, GeoPoint p, String category, String summary, String title, Set<TrailPoint> connections) {
		super(id, p, category, title, summary);
		_connections = connections;
		_unresolvableLinks = new LinkedList<Integer>();
	}
	public TrailPoint(int id, GeoPoint p, Set<TrailPoint> connections) {
		super(id, p, "", "", "");
		_connections = connections;
		_unresolvableLinks = new LinkedList<Integer>();
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
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

	public void addConnectionByID(int _currentConnection) {
		_unresolvableLinks.add(_currentConnection);
		_hasUnresolvedLinks	= true;
	}
	
	/**
	 * Returns a list of all connections that this point knows about, but has not
	 * actually made yet
	 * @return A list of TrailPoint IDs in Integer form
	 */
	public LinkedList<Integer> getUnresolvedLinks() {
		_hasUnresolvedLinks = false;
		LinkedList<Integer> returnList = new LinkedList<Integer>(_unresolvableLinks);
		_unresolvableLinks.clear();
		return returnList;
	}
	
	
	public boolean hasUnresolvedLinks() {
		return _hasUnresolvedLinks;
	}
}
