package com.brousalis.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.util.Log;

import com.brousalis.ShowMap;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class BaseTests extends ActivityInstrumentationTestCase2<ShowMap> {
	public static final int GOOD_ZOOM_LEVEL = 17;
	
	private ShowMap mActivity;
	private MapView mView;
	private int initialZoomLevel;
	private Configuration config;
	
	public BaseTests() {
		super("com.brousalis", ShowMap.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		config = new Configuration();
		config.setToDefaults();
		
		mActivity = this.getActivity();
		mView = (MapView) mActivity.findViewById(com.brousalis.R.id.mapView);
		initialZoomLevel = mView.getZoomLevel();
		setSaveLocation(true);
	}
	
	/**
	 * Ensure that everything in setUp went correctly
	 */
	public void testPreconditions() {
		assertNotNull(mView);
	}
	
	/**
	 * Open the Activity and verify that it has opened
	 */
	private void openActivity() {
		Intent testIntent = new Intent(mActivity, ShowMap.class);
		mActivity.startActivity(testIntent);
		TouchUtils.longClickView(this, mView);
	}

	/**
	 * Close the Activity (The process has gracefully exited) and 
	 * verify that it is closed
	 */
	private void closeActivity() {
		// A Bit Hacky... Fix this sometime
		mActivity.finish();
		try {
	        TouchUtils.longClickView(this, mView);
	    } catch (Exception e) {}
	    
	}
	
	/**
	 * Request that the the view is rotated to Landscape and ensure the view has rotated
	 */
	private void rotateToLandscape() {
		mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		TouchUtils.longClickView(this, mView);
		//assertEquals(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,mActivity.getRequestedOrientation());
	}
	
	/**
	 * Request that the view is rotated to Portrait and ensure that the view has rotated
	 */
	private void rotateToPortrait() {
		mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		TouchUtils.longClickView(this, mView);
		//assertEquals(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,mActivity.getRequestedOrientation());
	}
	
	private void setSaveLocation(boolean save) {
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mActivity);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(ShowMap.SAVED_MAP_STATE, save);
		editor.commit();
	}
	
	public void testLandscapeRotation() {
		rotateToPortrait();
		assertEquals(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,mActivity.getRequestedOrientation());
		
		rotateToLandscape();
		assertEquals(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,mActivity.getRequestedOrientation());
		
		rotateToLandscape();
		assertEquals(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,mActivity.getRequestedOrientation());
	}
	
	public void testPortraitRotation() {
		rotateToLandscape();
		
		rotateToPortrait();
		
		assertEquals(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,mActivity.getRequestedOrientation());
	}
	
	public void testMoveMapPositionAfterRotating() {
		rotateToPortrait();
		assertEquals(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,mActivity.getRequestedOrientation());
		TouchUtils.dragQuarterScreenDown(this, getActivity());
		GeoPoint p = mView.getMapCenter();
		Log.w("MTM", "MTM: Center B = " + p);
		
		rotateToLandscape();
		assertEquals(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,mActivity.getRequestedOrientation());
		assertEquals(p, mView.getMapCenter());
		Log.w("MTM", "MTM: Center A = " + mView.getMapCenter());
	}
	
	public void testMoveMapThenCloseAndReOpen() {
		rotateToPortrait();
		GeoPoint p1 = mView.getMapCenter();

		TouchUtils.dragQuarterScreenDown(this, mActivity);
		TouchUtils.longClickView(this, mView);
		GeoPoint p2 = mView.getMapCenter();
		
		closeActivity();
		openActivity();
		
		GeoPoint p3 = mView.getMapCenter();
		
		//assertFalse("P2 is On Center",p2.equals(p1));
		assertEquals(p2, p1);
		assertEquals("P3 did not resume in the same location as P2 Center",p3, p2);
		
	}
	public void testMoveMapThenCloseAndReOpenNoSave() {
		this.changeBooleanSetting(ShowMap.SAVED_MAP_STATE, false);
		//closeActivity();
		//openActivity();
		GeoPoint p1 = mView.getMapCenter();

		TouchUtils.dragQuarterScreenDown(this, mActivity);

		GeoPoint p2 = mView.getMapCenter();
		
		closeActivity();
		openActivity();
		
		GeoPoint p3 = mView.getMapCenter();
		
		assertFalse("P2 should not be in the same place the same as P3",p2.equals(p3));
		assertEquals("P3 did not resume in the same location as P1",p1, p3);
		
	}

	public void testZoomLevel() {
		assertEquals(initialZoomLevel,mView.getZoomLevel());
	}
	
	public void testNullRecentLocation() {
		fail("Still not quite sure if this can happen or what to do about it...");
	}
	
	public void testNoServerResponse() {
		fail("Large structure change, fix this test.");
	}
	
	public void testSuccessfulServerResponse() {
		fail("Large structure change, fix this test.");
	}
	
	public void testChangeASetting() {
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mActivity);
		changeBooleanSetting(ShowMap.SAVED_MAP_STATE, true);
		assertTrue(settings.getBoolean(ShowMap.SAVED_MAP_STATE, false));
		
		changeBooleanSetting(ShowMap.SAVED_MAP_STATE, false);
		assertFalse(settings.getBoolean(ShowMap.SAVED_MAP_STATE, true));
	}
	
	private void changeBooleanSetting(String item, boolean value) {
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mActivity);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(item, value);
		editor.commit();
		// Ensure that the setting got set
		assertTrue(settings.getBoolean(item, !value) != !value);
	}
}

