package com.brousalis.test;

import java.util.ArrayList;
import java.util.HashSet;

import junit.framework.TestCase;

import com.brousalis.InterestPoint;
import com.brousalis.Trail;
import com.brousalis.TrailPoint;
import com.google.android.maps.GeoPoint;

public class ObjectTests extends TestCase {
	public void testItemIsTrue() {
		assertTrue(true);
	}
	public void testCreateInterestPoint() {
		InterestPoint p = new InterestPoint(0, new GeoPoint(0,0), "Bathroom", "New Bathroom", "A Bathroom");
		assertNotNull(p);
	}
	public void testModifyInterestPoint() {
		InterestPoint p = new InterestPoint(0, new GeoPoint(0,0), "Bathroom", "New Bathroom", "A Bathroom");
		assertNotNull(p);
		
		String category = p.getCategory();
		p.setCategory("Restroom");
		assertFalse("Category did not get changed", category.equals(p.getCategory()));
		
		GeoPoint point = p.getLocation();
		p.setLocation(5, 5);
		assertFalse("Location did not get changed", point.equals(p.getLocation()));
	}
	
	public void testCreateTrailPoint() {
		TrailPoint p = new TrailPoint(0, new GeoPoint(0,0), "Bathroom", "New Bathroom", "A Bathroom", "Heritage", new HashSet<Integer>());
		assertNotNull(p);
	}
	
	public void testModifyTrailPoint() {
		TrailPoint p = new TrailPoint(0, new GeoPoint(0,0), "Bathroom", "New Bathroom", "A Bathroom", "Heritage", new HashSet<Integer>());
		assertNotNull(p);
		
		// Test the methods in the subclass
		String trail = p.getTrail();
		p.setTrail("Sac and Fox");
		assertFalse("Trail did not get changed", trail.equals(p.getTrail()));
	}
	
	public void testCreateTrail() {
		Trail t = new Trail("Sac and Fox");
		assertNotNull(t);
	}
	
	public void testModifyTrail() {
		Trail t = new Trail("Sac and Fox");
		assertNotNull(t);
		String trailName = t.getName();
		t.setName("Heritage");
		assertFalse("Trail name did not get changed", trailName.equals(t.getName()));
	}
	public void testAddPointConnection() {
		TrailPoint p1 = new TrailPoint(0, new GeoPoint(0,0), new HashSet<Integer>());
		TrailPoint p2 = new TrailPoint(0, new GeoPoint(10,10), new HashSet<Integer>());
		p1.addConnection(p2);
		p2.addConnection(p1);
		assertTrue("P1 is not connected to P2",p1.hasConnection(p2));
		assertTrue("P2 is not connected to P1",p2.hasConnection(p1));
	}
	public void testAddLinkedPointsToTrail() {
		TrailPoint p1 = new TrailPoint(0, new GeoPoint(0,0), new HashSet<Integer>());
		TrailPoint p2 = new TrailPoint(0, new GeoPoint(5,5), new HashSet<Integer>());
		TrailPoint p3 = new TrailPoint(0, new GeoPoint(10,10), new HashSet<Integer>());
		assertNotNull(p1);
		
		Trail t = new Trail("Herritage");
		ArrayList<TrailPoint> list = new ArrayList<TrailPoint>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		t.addLinkedPoints(list);
		
		assertEquals(list.size(), t.getNumberOfTrailPoints());
	}
	public void testAddPointToTrail() {
		int numOfTrailPoints = 0;
		TrailPoint pOld = new TrailPoint(0, new GeoPoint(0,0), new HashSet<Integer>());
		TrailPoint pNew = new TrailPoint(0, new GeoPoint(5,5), new HashSet<Integer>());
		Trail t = new Trail("Herritage");
		t.addPoint(pOld);
		numOfTrailPoints++;
		assertEquals(numOfTrailPoints, t.getNumberOfTrailPoints());
		
		t.addPoint(pNew, pOld);
		numOfTrailPoints++;
		assertEquals(numOfTrailPoints, t.getNumberOfTrailPoints());
	}
	
	public void testRemovePointFromTrail() {
		TrailPoint p1 = new TrailPoint(0, new GeoPoint(0,0), new HashSet<Integer>());
		TrailPoint p2 = new TrailPoint(0, new GeoPoint(5,5), new HashSet<Integer>());
		TrailPoint p3 = new TrailPoint(0, new GeoPoint(10,10), new HashSet<Integer>());
		
		Trail t = new Trail("Herritage");
		ArrayList<TrailPoint> list = new ArrayList<TrailPoint>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		t.addLinkedPoints(list);
		
		assertEquals(list.size(), t.getNumberOfTrailPoints());
		
		t.removePoint(p2);
		list.remove(p2);
		assertEquals(list.size(), t.getNumberOfTrailPoints());
	}
}
