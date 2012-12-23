package com.refreshactionprovider.sample.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.SherlockListFragment;
import com.refreshactionprovider.fragment.RefreshListFragment;
import com.refreshactionprovider.sample.loader.SongLoader;

public class SampleFragment extends RefreshListFragment implements LoaderCallbacks<List<String>>{

	private static final int LOADER_ID = 1;
	private static final String TAG = "SampleFragment";
	private ArrayAdapter<String> mAdapter;
	
	public static SampleFragment newInstance() {

		return new SampleFragment();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		mAdapter = new ArrayAdapter<String>(getSherlockActivity(), android.R.layout.simple_list_item_1);
		setListAdapter(mAdapter);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getSherlockActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
	}

	@Override
	public Loader<List<String>> onCreateLoader(int id, Bundle bundle) {
		
		startRefreshing();
		mAdapter.clear();
		Log.d(TAG, "onCreateLoader");
		return new SongLoader(getSherlockActivity());
		
	}

	@Override
	public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
		
		stopRefreshing();
		mAdapter.addAll(data);
	}

	@Override
	public void onLoaderReset(Loader<List<String>> loader) {
		
		Log.d(TAG, "onLoaderReset");
		mAdapter.clear();
		loader.reset();
	}

	@Override
	public void onRefresh() {
		
		getSherlockActivity().getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
	}
}