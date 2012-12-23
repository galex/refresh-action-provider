package com.refreshactionprovider.sample.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.refreshactionprovider.sample.R;

public class SampleActivityLoader extends SherlockFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_activity_loader);
    }
}
