package com.brousalis.test;

import java.util.HashSet;
import java.util.LinkedList;

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
		TrailPoint p = new TrailPoint(0, new GeoPoint(0,0), "Bathroom", "New Bathroom", "A Bathroom", new HashSet<TrailPoint>());
		assertNotNull(p);
	}
	
	public void testModifyTrailPoint() {
		TrailPoint p = new TrailPoint(0, new GeoPoint(0,0), "Bathroom", "New Bathroom", "A Bathroom", new HashSet<TrailPoint>());
		assertNotNull(p);
		
		// Test the methods in the subclass
		// TODO: Make this test assert something better
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
		TrailPoint p1 = new TrailPoint(0, new GeoPoint(0,0), new HashSet<TrailPoint>());
		TrailPoint p2 = new TrailPoint(0, new GeoPoint(10,10), new HashSet<TrailPoint>());
		p1.addConnection(p2);
		p2.addConnection(p1);
		assertTrue("P1 is not connected to P2",p1.hasConnection(p2));
		assertTrue("P2 is not connected to P1",p2.hasConnection(p1));
	}
	public void testAddPointConnections() {
		TrailPoint p1 = new TrailPoint(0, new GeoPoint(0,0), new HashSet<TrailPoint>());
		TrailPoint p2 = new TrailPoint(0, new GeoPoint(5,5), new HashSet<TrailPoint>());
		TrailPoint p3 = new TrailPoint(0, new GeoPoint(10,10), new HashSet<TrailPoint>());
		TrailPoint p4 = new TrailPoint(0, new GeoPoint(15,15), new HashSet<TrailPoint>());
		p1.addConnection(p2);
		p2.addConnection(p1);
		
		p2.addConnection(p4);
		p4.addConnection(p2);
		
		p3.addConnection(p4);
		p4.addConnection(p3);
		
		assertTrue("P1 is not connected to P2",p1.hasConnection(p2));
		assertTrue("P2 is not connected to P1",p2.hasConnection(p1));
		
		assertTrue("P2 is not connected to P4",p2.hasConnection(p4));
		assertTrue("P4 is not connected to P2",p4.hasConnection(p2));
		
		assertTrue("P3 is not connected to P4",p3.hasConnection(p4));
		assertTrue("P4 is not connected to P3",p4.hasConnection(p3));
		
		assertTrue("P1 is connected to P3",p1.hasConnection(p3));
		assertTrue("P3 is connected to P1",p3.hasConnection(p1));
	}
	public void testAddLinkedPointsToTrail() {
		TrailPoint p1 = new TrailPoint(0, new GeoPoint(0,0), new HashSet<TrailPoint>());
		TrailPoint p2 = new TrailPoint(0, new GeoPoint(5,5), new HashSet<TrailPoint>());
		TrailPoint p3 = new TrailPoint(0, new GeoPoint(10,10), new HashSet<TrailPoint>());
		assertNotNull(p1);
		assertNotNull(p2);
		assertNotNull(p3);
		
		Trail t = new Trail("Herritage");
		LinkedList<TrailPoint> list = new LinkedList<TrailPoint>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		int size = list.size();
		t.addLinkedPoints(list);
		
		assertEquals(size, t.getNumberOfTrailPoints());
		// TODO: Verifiy Connections
		assertTrue("Trail did not contain P1",t.hasPoint(p1));
		assertTrue("Trail did not contain P2",t.hasPoint(p2));
		assertTrue("Trail did not contain P3",t.hasPoint(p3));
	}
	
	public void testAddPointToTrail() {
		int numOfTrailPoints = 0;
		TrailPoint pOld = new TrailPoint(0, new GeoPoint(0,0), new HashSet<TrailPoint>());
		TrailPoint pNew = new TrailPoint(1, new GeoPoint(5,5), new HashSet<TrailPoint>());
		Trail t = new Trail("Herritage");
		t.addPoint(pOld);
		
		assertTrue("Trail did not contain pOld",t.hasPoint(pOld));
		assertFalse("Trail did contain pNew",t.hasPoint(pNew));
		
		numOfTrailPoints++;
		assertEquals(numOfTrailPoints, t.getNumberOfTrailPoints());
		
		t.addPoint(pNew, pOld);
		numOfTrailPoints++;
		assertEquals(numOfTrailPoints, t.getNumberOfTrailPoints());
		
		assertTrue(t.getTrailPoint(pOld.getID()).getConnections().contains(pNew.getID()));
		assertFalse(t.getTrailPoint(pNew.getID()).getConnections().contains(pOld.getID()));
		
		assertTrue("Trail did not contain pOld",t.hasPoint(pOld));
		assertTrue("Trail did not contain pNew",t.hasPoint(pNew));
	}
	
	public void testAddPointToNullPointTrail() {
		int numOfTrailPoints = 0;
		TrailPoint pOld = null;
		TrailPoint pNew = new TrailPoint(1, new GeoPoint(5,5), new HashSet<TrailPoint>());
		Trail t = new Trail("Herritage");
		
		assertFalse("Trail did contain pNew",t.hasPoint(pNew));
		
		assertEquals(numOfTrailPoints, t.getNumberOfTrailPoints());
		
		assertTrue(t.addPoint(pNew, pOld));
		numOfTrailPoints++;
		assertEquals(numOfTrailPoints, t.getNumberOfTrailPoints());
		
		assertFalse(t.addPoint(pNew, pOld));
		
		assertFalse("Trail did contain pOld",t.hasPoint(pOld));
		assertTrue("Trail did not contain pNew",t.hasPoint(pNew));
	}
	
	public void testAddPointThatAlreadyExists() {
		TrailPoint P1 = new TrailPoint(0, new GeoPoint(5,5), new HashSet<TrailPoint>());
		TrailPoint P2 = new TrailPoint(0, new GeoPoint(5,5), new HashSet<TrailPoint>());
		TrailPoint P3 = new TrailPoint(0, new GeoPoint(5,5), new HashSet<TrailPoint>());
		
		Trail t = new Trail("Heritage");
		
		t.addPoint(P1);
		LinkedList<TrailPoint> list = new LinkedList<TrailPoint>();
		list.add(P2);
		list.add(P3);
		t.addLinkedPoints(list, P1);
		list.add(P1);
		assertTrue("List did not contain all the points", t.getTrailPoints().containsAll(list));
	}
	
	public void testRemovePointFromTrail() {
		TrailPoint p1 = new TrailPoint(0, new GeoPoint(0,0), new HashSet<TrailPoint>());
		TrailPoint p2 = new TrailPoint(0, new GeoPoint(5,5), new HashSet<TrailPoint>());
		TrailPoint p3 = new TrailPoint(0, new GeoPoint(10,10), new HashSet<TrailPoint>());
		
		Trail t = new Trail("Herritage");
		LinkedList<TrailPoint> list = new LinkedList<TrailPoint>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		int size = list.size();
		t.addLinkedPoints(list);
		assertEquals(size, t.getNumberOfTrailPoints());
		
		t.removePoint(p2);
		size--;
		assertEquals(size, t.getNumberOfTrailPoints());
	}
	
	public void testMilesRadiusFunction() {
		GeoPoint p = new GeoPoint((int)(39.4867*1E6),(int)(-87.3153*1E6));
		TrailPoint tp = new TrailPoint(0, p, null);
		
		GeoPoint p2 = new GeoPoint((int)(39.4866*1E6),(int)(-87.3142*1E6));
		TrailPoint tp2 = new TrailPoint(1, p2, null);
		
		assertTrue(tp.isPointNotThisFarAway(tp2, 20));
		assertFalse(tp.isPointNotThisFarAway(tp2, .01));
	}
	
	public void testMilesRadiusFunction2() {
		GeoPoint p = new GeoPoint((int)(39.4704*1E6),(int)(-87.3696*1E6));
		TrailPoint tp = new TrailPoint(0, p, null);
		
		GeoPoint p2 = new GeoPoint((int)(39.4700*1E6),(int)(-87.3525*1E6));
		TrailPoint tp2 = new TrailPoint(1, p2, null);
		
		assertTrue(tp.isPointNotThisFarAway(tp2, 1));
		assertFalse(tp.isPointNotThisFarAway(tp2, .5));
		assertFalse(tp.isPointNotThisFarAway(tp2, .75));
	}
}
