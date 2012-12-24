package com.refreshactionprovider.fragment;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.refreshactionprovider.R;
import com.refreshactionprovider.widget.RefreshActionProvider;
import com.refreshactionprovider.widget.RefreshActionProvider.OnRefreshListener;

/**
 * @author Alexander Gherschon
 * 
 */
public abstract class RefreshListFragment extends SherlockListFragment {

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
		mRefreshActionProvider = (RefreshActionProvider) menuItem.getActionProvider();
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
