package com.brousalis;

import android.graphics.Paint;

public class Trail {
	
	private Paint _linePaint;
	private String _name;
	
	public Trail(String name) {
		_linePaint = new Paint();
		_linePaint.setAntiAlias(true);
		_linePaint.setARGB(255, 0, 255, 0);
		_name = name;
	}
	
	public void setName(String name) {
		_name = name;
	}
	public String getName() {
		return _name;
	}
}
