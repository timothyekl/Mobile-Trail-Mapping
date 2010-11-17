package com.brousalis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class Trail extends ItemizedOverlay{
	
	private Paint _linePaint;
	private String _name;
	private ArrayList<TrailPoint> _trailPoints;
	//private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	
	public Trail(String name, Drawable defaultIcon) {
		super(boundCenter(defaultIcon));
		this._linePaint = new Paint();
		this._linePaint.setAntiAlias(true);
		this._linePaint.setARGB(255, 0, 255, 0);
		this._name = name;
		this._trailPoints = new ArrayList<TrailPoint>();
	}
	
	public void setName(String name) {
		this._name = name;
	}
	public String getName() {
		return this._name;
	}
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// TODO Auto-generated method stub
		super.draw(canvas, mapView, false);
	}
	
	/**
	 * This method is DANGEROUS, it assumes each point is connected to the next.
	 * 
	 * Adds a List of TrailPoints to this trail.
	 * It should be noted that the first point of 
	 * this trail is NOT linked to any other point 
	 * on this trail
	 * 
	 * @param trailPoints
	 */
	public void addLinkedPoints(LinkedList<TrailPoint> trailPoints) {
		while(!trailPoints.isEmpty()) {
			TrailPoint p = trailPoints.poll();
			this._trailPoints.add(p);
			populate();
			p.addConnection(trailPoints.peek());
		}
	}
	
	/**
	 * Adds a List of TrailPoints to this trail.
	 * It should be noted that the first point of 
	 * this trail is NOT linked to any other point 
	 * on this trail
	 * @param trailPoints
	 */
	public void addLinkedPoints(LinkedList<TrailPoint> trailPoints, TrailPoint pOld) {
		while(!trailPoints.isEmpty()) {
			TrailPoint p = trailPoints.poll();
			this._trailPoints.add(p);
			populate();
			p.addConnection(trailPoints.peek());
		}
	}
	
	/**
	 * Returns the number of points in this trail
	 * @return The number of points in this trail
	 */
	public int getNumberOfTrailPoints() {
		return this._trailPoints.size();
	}

	/**
	 * Adds a new TrailPoint to this Trail and
	 * Adds a new connection from pOld to pNew
	 * @param pNew The new TrailPoint to add
	 * @param pOld The existing TrailPoint to form the connection from
	 */
	public boolean addPoint(TrailPoint pNew, TrailPoint pOld) {
		if(this.hasPoint(pNew))
			return false;
		this._trailPoints.add(pNew);
		if(this.hasPoint(pOld))
			pOld.addConnection(pNew);
		populate();
		return true;
	}

	/**
	 * Adds a new TrailPoint to this Trail
	 * @param point The new TrailPoint to add
	 */
	public void addPoint(TrailPoint point) {
		this._trailPoints.add(point);
		populate();
	}

	/**
	 * Removes a TrailPoint if it is in the current trail
	 * @param point The TrailPoint to remove
	 */
	public void removePoint(TrailPoint point) {
		this._trailPoints.remove(point);
	}
	
	/**
	 * Gets a specific TrailPoint based on an ID.
	 * After Testing, this method will be made private
	 * @param id The ID of the TrailPoint
	 * @return A TrailPoint if it exists, or null
	 */
	public TrailPoint getTrailPoint(int id) {
		Iterator<TrailPoint> iter = _trailPoints.iterator();
		while(iter.hasNext()) {
			TrailPoint current = iter.next();
			if (current.getID() == id) {
				return current;
			}
		}
		return null;
	}

	/**
	 * Returns the Set of all TrailPoints
	 * @return The Set of all TrailPoints
	 */
	public Collection<? extends OverlayItem> getTrailPoints() {
		return this._trailPoints;
	}

	/**
	 * Determines if this Trail does or does not contain a specific point
	 * @param point The TrailPoint to check the list against
	 * @return True if the Point is contained within the list
	 */
	public boolean hasPoint(TrailPoint point) {
		if(point == null)
			return false;
		return this._trailPoints.contains(point);
	}
	
	/**
	 * Corrects any unresolved Links.  Unresolved links can occur when
	 * importing data from XML.  This function takes the list of Integers
	 * and parses them properly.  That list is then cleared out.
	 */
	public void resolveConnections() {
		Log.w("MTM", "MTM: Pre  " + this.toStringList());
		for(TrailPoint p : this._trailPoints) {
			if(p.hasUnresolvedLinks()) {
				for(Integer i : p.getUnresolvedLinks()) {
					p.addConnection(this.getTrailPoint(i));
				}
			}
		}
		Log.w("MTM", "MTM: Post " + this.toStringList());
	}
	
	@Override
	public String toString() {
		return "Trail: " + this._name + " (" + this._trailPoints.size() + " TrailPoints)";
	}
	
	public String toStringList() {
		String trailList =  "Trail: " + this._name + " (";
		for(TrailPoint p : this._trailPoints) {
			trailList += p.getID() + ", ";
		}
		return trailList;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return _trailPoints.get(i);
	}

	@Override
	public int size() {
		return _trailPoints.size();
	}
}
