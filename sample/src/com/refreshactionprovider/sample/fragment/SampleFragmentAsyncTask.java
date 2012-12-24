package com.refreshactionprovider.sample.fragment;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.refreshactionprovider.fragment.RefreshListFragment;
import com.refreshactionprovider.sample.R;
import com.refreshactionprovider.sample.util.HttpUtils;
/**
 * RefreshableListFragment, AsyncTask, Custom Layout
 * @author Alexander Gherschon
 *
 */
public class SampleFragmentAsyncTask extends RefreshListFragment {

	private ArrayAdapter<String> mAdapter;
	private View mProgressView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		mProgressView = getView().findViewById(R.id.progress);
		mAdapter = new ArrayAdapter<String>(getSherlockActivity(), android.R.layout.simple_list_item_1);
		setListAdapter(mAdapter);
		new SongAsyncTask().execute();
	}
	
	@Override
	public void onRefresh() {
		
		new SongAsyncTask().execute();
	}
	
	private class SongAsyncTask extends AsyncTask<Void, Void, List<String>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			setRefreshing(true);
			mAdapter.clear();
			mProgressView.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected List<String> doInBackground(Void... arg0) {
			
			List<String> songs = null;
			try {
				songs = HttpUtils.getListOfSongs();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return songs;
		}
	
		@Override
		protected void onPostExecute(List<String> result) {
		
			super.onPostExecute(result);
			setRefreshing(false);
			mProgressView.setVisibility(View.GONE);
			mAdapter = new ArrayAdapter<String>(getSherlockActivity(), android.R.layout.simple_list_item_1, result);
			setListAdapter(mAdapter);
		}
	}
}