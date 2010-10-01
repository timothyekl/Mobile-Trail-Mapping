package com.brousalis;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.android.maps.Overlay;

import android.graphics.Paint;

public class Trail {
	
	private Paint _linePaint;
	private String _name;
	private HashSet<TrailPoint> _trailPoints;
	
	public Trail(String name) {
		_linePaint = new Paint();
		_linePaint.setAntiAlias(true);
		_linePaint.setARGB(255, 0, 255, 0);
		_name = name;
		_trailPoints = new HashSet<TrailPoint>();
	}
	
	public void setName(String name) {
		_name = name;
	}
	public String getName() {
		return _name;
	}

	public void addLinkedPoints(LinkedList<TrailPoint> trailPoints) {
		while(!trailPoints.isEmpty()) {
			TrailPoint p = trailPoints.poll();
			_trailPoints.add(p);
			p.addConnection(trailPoints.peek());
		}
	}
	
	/**
	 * Returns the number of points in this trail
	 * @return The number of points in this trail
	 */
	public int getNumberOfTrailPoints() {
		return _trailPoints.size();
	}

	/**
	 * Adds a new TrailPoint to this Trail and
	 * Adds a new connection from pOld to pNew
	 * @param pNew The new TrailPoint to add
	 * @param pOld The existing TrailPoint to form the connection from
	 */
	public void addPoint(TrailPoint pNew, TrailPoint pOld) {
		_trailPoints.add(pNew);
		pOld.addConnection(pNew);
	}

	/**
	 * Adds a new TrailPoint to this Trail
	 * @param point The new TrailPoint to add
	 */
	public void addPoint(TrailPoint point) {
		_trailPoints.add(point);
	}

	/**
	 * Removes a TrailPoint if it is in the current trail
	 * @param point The TrailPoint to remove
	 */
	public void removePoint(TrailPoint point) {
		_trailPoints.remove(point);
	}
	
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

	public Collection<? extends Overlay> getTrailPoints() {
		return _trailPoints;
	}
}
