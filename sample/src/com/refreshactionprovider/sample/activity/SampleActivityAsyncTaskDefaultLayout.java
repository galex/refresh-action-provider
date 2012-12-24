package com.refreshactionprovider.sample.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Window;
import com.refreshactionprovider.sample.R;

public class SampleActivityAsyncTaskDefaultLayout extends SherlockFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.sample_activity_async_task_default_layout);
        
    }
}
