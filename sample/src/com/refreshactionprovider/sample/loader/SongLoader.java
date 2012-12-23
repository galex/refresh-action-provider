package com.refreshactionprovider.sample.loader;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import com.refreshactionprovider.sample.util.HttpUtils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
/**
 * Simple Song Loader 
 * @author Alexander Gherschon
 *
 */
public class SongLoader extends AbstractAsyncTaskLoader<List<String>> {

	private static final String TAG = "SongLoader";
	
	public SongLoader(Context context) {
		super(context);
	}

	@Override
	public List<String> loadInBackground() {
		
		Log.d(TAG, "SongLoader");
		
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

	
}
