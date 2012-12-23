package com.refreshactionprovider.sample.loader;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import android.content.Context;

import com.refreshactionprovider.sample.util.HttpUtils;
/**
 * Simple Song Loader 
 * @author Alexander Gherschon
 *
 */
public class SongLoader extends AbstractAsyncTaskLoader<List<String>> {

	public SongLoader(Context context) {
		super(context);
	}

	@Override
	public List<String> loadInBackground() {
		
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
