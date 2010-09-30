package com.brousalis;

import java.util.ArrayList;

import com.google.android.maps.GeoPoint;


public class TrailPoint extends InterestPoint {
	
	private String _trail;
	private ArrayList<Integer> _connections;
	
	
	public TrailPoint(int id, int latitude, int longitude, String category, String summary, String title, String trail, ArrayList<Integer> connections) {
		super(id, new GeoPoint(latitude, longitude), category, title, summary);
		_connections = connections;
		_trail = trail;
	}
	
	public TrailPoint(int id, GeoPoint p, String category, String summary, String title, String trail, ArrayList<Integer> connections) {
		super(id, p, category, title, summary);
		_connections = connections;
		_trail = trail;
	}
	
	public void setTrail(String trail) {
		_trail = trail;
	}
	public String getTrail() {
		// TODO Auto-generated method stub
		return null;
	}
}
