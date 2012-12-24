package com.refreshactionprovider.sample.fragment;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.SherlockListFragment;
import com.refreshactionprovider.sample.util.HttpUtils;
/**
 * ListFragment, AsyncTask, Default Layout
 * @author Alexander Gherschon
 *
 */
public class SampleFragmentAsyncTaskDefaultLayout extends SherlockListFragment {
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getSherlockActivity(), android.R.layout.simple_list_item_1, new String [0]));
		new SongAsyncTask().execute();
	}
	
	private class SongAsyncTask extends AsyncTask<Void, Void, List<String>> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			SampleFragmentAsyncTaskDefaultLayout.this.getSherlockActivity().setSupportProgressBarIndeterminateVisibility(true);
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
			SampleFragmentAsyncTaskDefaultLayout.this.getSherlockActivity().setSupportProgressBarIndeterminateVisibility(false);
			setListAdapter(new ArrayAdapter<String>(getSherlockActivity(), android.R.layout.simple_list_item_1, result));
		}
	}
}