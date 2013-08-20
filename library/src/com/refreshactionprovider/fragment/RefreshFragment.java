package com.refreshactionprovider.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.refreshactionprovider.R;
import com.refreshactionprovider.widget.RefreshActionProvider;
import com.refreshactionprovider.widget.RefreshActionProvider.OnRefreshListener;

/**
 * @author Alexander Gherschon
 * 
 */
public abstract class RefreshFragment extends Fragment {

	private RefreshActionProvider mRefreshActionProvider;
	private boolean mIsLoading;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		inflater.inflate(R.menu.refresh_menu, menu);

		MenuItem menuItem = menu.findItem(R.id.refresh_action_item);
		mRefreshActionProvider = (RefreshActionProvider) MenuItemCompat.getActionProvider(menuItem);
		mRefreshActionProvider.setTitle(menuItem.getTitle()); // doesn't seems to be possible to get that value from inside the ActionProvider
		mRefreshActionProvider.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefreshListener() {
				onRefresh();
			}
		});

		if (mIsLoading) mRefreshActionProvider.showProgressBar();
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	public void setRefreshing(boolean isRefreshing){
		
		mIsLoading = isRefreshing;
		if(mRefreshActionProvider != null){
			if(mIsLoading) mRefreshActionProvider.showProgressBar();
			else mRefreshActionProvider.showButton();
		}
	}

	protected abstract void onRefresh();
}
