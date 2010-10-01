package com.brousalis;

import java.util.HashSet;
import java.util.LinkedList;

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

	public int getNumberOfTrailPoints() {
		return _trailPoints.size();
	}

	public void addPoint(TrailPoint pNew, TrailPoint pOld) {
		// TODO Auto-generated method stub
		
	}

	public void addPoint(TrailPoint pOld) {
		// TODO Auto-generated method stub
		
	}

	public void removePoint(TrailPoint p2) {
		// TODO Auto-generated method stub
		
	}
}
