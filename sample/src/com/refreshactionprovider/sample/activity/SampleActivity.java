package com.refreshactionprovider.sample.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.refreshactionprovider.sample.fragment.SampleFragment;

public class SampleActivity extends SherlockFragmentActivity {

	private static final String SAMPLE_FRAGMENT_TAG = "SAMPLE_FRAGMENT_TAG";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
	    SampleFragment fragment = (SampleFragment) getSupportFragmentManager().findFragmentByTag(SAMPLE_FRAGMENT_TAG);
	    if (fragment == null) {

	    	fragment = SampleFragment.newInstance();
	        getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment, SAMPLE_FRAGMENT_TAG).commit();
	    } 
    }
}
