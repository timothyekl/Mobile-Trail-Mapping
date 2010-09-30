package com.brousalis;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class TrailPrefs extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.options);
	}
}
