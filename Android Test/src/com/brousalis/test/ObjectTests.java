package com.brousalis.test;

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
}
