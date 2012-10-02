package com.refreshactionprovider.lib.fragment;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.refreshactionprovider.R;
import com.refreshactionprovider.lib.widget.RefreshActionProvider;
import com.refreshactionprovider.lib.widget.RefreshActionProvider.OnRefreshListener;

public abstract class RefreshListFragment extends SherlockListFragment {

	private RefreshActionProvider refreshActionProvider;
	private boolean isLoading;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		inflater.inflate(R.menu.refresh_menu, menu);

		refreshActionProvider = (RefreshActionProvider) menu.findItem(R.id.refresh_action_item).getActionProvider();
		refreshActionProvider.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefreshListener() {
				forceLoad();
			}
		});

		if (isLoading)
			refreshActionProvider.showProgressBar();

		super.onCreateOptionsMenu(menu, inflater);
	}

	protected void manageRefreshOnLoaderCreated() {

		isLoading = true;
		if (refreshActionProvider != null)
			refreshActionProvider.showProgressBar();
	}

	protected void manageRefreshOnLoaderFinished() {

		isLoading = false;

		if (refreshActionProvider != null)
			refreshActionProvider.showButton();
	}
	

	protected abstract void forceLoad();
	protected abstract void load();
}
