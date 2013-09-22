package com.correportuvida.activities;

import android.os.Bundle;
import android.view.Menu;

import com.correportuvida.R;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class RunningActivity extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_running);
		
		 MapView mapView = (MapView) findViewById(R.id.mapview);
		 mapView.setBuiltInZoomControls(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.running, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
