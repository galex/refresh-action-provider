package com.refreshactionprovider.sample.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.SherlockListActivity;

public class MainActivity extends SherlockListActivity {

	private ArrayAdapter<String> mArrayAdapter;
	private List<ActivityInfo> activities;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		activities = new ArrayList<ActivityInfo>();

		PackageManager packageManager = getPackageManager();
		try {
			PackageInfo info = packageManager.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
			int count = info.activities.length;
			for (int i = 0; i < count; i++) {
				if (!info.activities[i].name.equals( MainActivity.class.getName())){
					mArrayAdapter.add(getString(info.activities[i].labelRes));
					activities.add(info.activities[i]);
				}
			}

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		setListAdapter(mArrayAdapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				ActivityInfo info = activities.get(position);
				Intent intent = new Intent();
				intent.setClassName(info.packageName, info.name);
				MainActivity.this.startActivity(intent);
			}
		});
	}
}
