package com.refreshactionprovider.sample.activity;


import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.refreshactionprovider.sample.R;

public class ActivitiesListActivity extends SherlockFragmentActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activities_list);
	}
}
