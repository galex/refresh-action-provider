package com.refreshactionprovider.sample.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class HttpUtils {

	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final String TAG = "HttpUtils";

	public static List<String> getListOfSongs() throws IOException, JSONException {

		Log.d(TAG, "getListOfSongs");
		
		List<String> songs = new ArrayList<String>();
		
		JSONArray jsonArray = new JSONArray(get("http://garage.ubiquoid.com/rodrigoygabriela.php"));
		for(int i = 0; i < jsonArray.length() ; i++){
			songs.add(jsonArray.getString(i));
		}
		return songs;
	}
	
	public static String get(String address) throws IOException{
		
		String response;
		URL url = new URL(address);
		URLConnection urlConnection = url.openConnection();

		InputStream in = new BufferedInputStream(urlConnection.getInputStream());
		try {
			response = readStream(in);
		} finally {
			in.close();
		}
		return response;
	}

	public static String readStream(InputStream is) throws IOException {
		return convertStreamToString(is, DEFAULT_ENCODING);
	}

	public static String convertStreamToString(InputStream is, String encoding)
			throws IOException {
		InputStreamReader reader = new InputStreamReader(is, encoding);
		StringBuilder out = new StringBuilder();
		char[] buffer = new char[4096];
		int readChars;
		while ((readChars = reader.read(buffer)) != -1) {
			out.append(buffer, 0, readChars);
		}
		return out.toString();
	}
}
