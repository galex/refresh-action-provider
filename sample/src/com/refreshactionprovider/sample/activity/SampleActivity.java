package com.refreshactionprovider.sample.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.refreshactionprovider.sample.fragment.SampleFragment;

public class SampleActivity extends SherlockFragmentActivity {

	private static final String FRAGMENT_TAG = "FRAGMENT_TAG";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
     //getSupportActionBar().setTitle(String.format(getString(R.string.categories)));
    	
	    SampleFragment fragment = (SampleFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
	    if (fragment == null) {

	    	fragment = SampleFragment.newInstance();
	        getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment, FRAGMENT_TAG).commit();
	    } 
    }
}
