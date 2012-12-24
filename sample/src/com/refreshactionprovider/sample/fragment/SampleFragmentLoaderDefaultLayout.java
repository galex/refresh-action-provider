package com.refreshactionprovider.sample.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.SherlockListFragment;
import com.refreshactionprovider.sample.loader.SongLoader;
/**
 * ListFragment, Loader, Default Layout
 * @author Alexander Gherschon
 *
 */
public class SampleFragmentLoaderDefaultLayout extends SherlockListFragment implements LoaderCallbacks<List<String>>{

	private static final int LOADER_ID = 1;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		getSherlockActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);	
	}

	@Override
	public Loader<List<String>> onCreateLoader(int id, Bundle bundle) {
		
		setListAdapter(null);
		return new SongLoader(getSherlockActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<String>> loader, List<String> data) {

		setListAdapter(new ArrayAdapter<String>(getSherlockActivity(), android.R.layout.simple_list_item_1, data));
	}

	@Override
	public void onLoaderReset(Loader<List<String>> loader) {
		
		setListAdapter(null);
	}
}